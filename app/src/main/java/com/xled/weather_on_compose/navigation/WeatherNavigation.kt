package com.xled.weather_on_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xled.weather_on_compose.ui.Weather
import com.xled.weather_on_compose.viewmodels.WeatherViewModel


@Composable
fun WeatherNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Screen.MainPage.route
    ) {
        composable(Screen.MainPage.route){
            val weatherViewModel: WeatherViewModel = hiltViewModel()
            Weather(modifier, weatherViewModel, navController)
        }

        composable(Screen.SecondPage.route){

        }

    }
}