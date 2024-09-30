package com.xled.weather_on_compose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xled.weather_on_compose.ui.theme.Weather_on_composeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Weather_on_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var is_loading by remember { mutableStateOf(true) }


    var current_temperature by remember { mutableStateOf("Загрузка...") }
    var current_location by remember { mutableStateOf("Загрузка...") }
    var current_precipitation by remember { mutableStateOf("Загрузка...") }
    var current_wind by remember { mutableStateOf("Загрузка...") }

    var hourly_temperature_2m by remember { mutableStateOf<List<Double>>(emptyList()) }
    var hourly_precipitation_probability by remember { mutableStateOf<List<Double>>(emptyList()) }

    var color_temperature by remember { mutableStateOf(Color(200, 200, 200)) }


    CoroutineScope(Dispatchers.IO).launch {
        val preds = RetrofitInstance.api.getPrediction()
        Log.d("Json", preds.toString())

        withContext(Dispatchers.Main) {
            current_temperature = preds.current.temperature_2m.toString() + "°C"
            current_location = "${preds.latitude}   ${preds.longitude}"
            current_precipitation = "Осадки: ${preds.current.precipitation} mm"
            current_wind = "Ветер: ${preds.current.wind_speed_10m} м/c"

            val y = 1 / (1 + kotlin.math.exp(-(preds.current.temperature_2m) / 10))

            color_temperature = interpolateColor(Color(0xFF89CFF0), Color(0xFFFF5711), y) // Color(0xFF89CFF0), Color(0xFFFF5711)

            hourly_temperature_2m = preds.hourly.temperature_2m
            hourly_precipitation_probability = preds.hourly.precipitation_probability

            is_loading = false
        }
    }
    Column (modifier = modifier) {
        Box(
            modifier = Modifier//.fillMaxHeight(0.2f)
                .fillMaxWidth()
                .height(200.dp)
                .background(color = color_temperature)
        ) {
            Text(
                text = current_location,
                modifier = Modifier.align(Alignment.TopCenter),
                    //.background(color = Color.Black),
                color = Color.Black
                )
            Text(
                text = current_temperature,
                fontSize= 70.sp,
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )}


        AnimatedVisibility(visible = !is_loading) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .background(color = Color.Black)
                    .fillMaxWidth()
                //.height(100.dp)
            ) {
                Text(
                    text = current_precipitation,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    text = current_wind,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        AnimatedVisibility(visible = !is_loading) {
            if (is_loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier.clip(CircleShape),
                        painter = painterResource(id = R.drawable.grah),
                        contentDescription = "Pfuheprf"
                    )
                    Text(text = "Загрузка...")
                }
            } else {
                LazyColumn (
                    modifier = Modifier.fillMaxWidth(),
                    ) {
                    items(24) { hour ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = hourly_temperature_2m[hour].toString() + "°C",
                                    fontSize= 30.sp)
                                Text(text = "$hour:00",
                                    )
                                Text(text = "Вероятность осадков: ${hourly_precipitation_probability[hour]} %",
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Weather_on_composeTheme {
        Greeting()
    }
}