package com.xled.weather_on_compose.logic

import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt


fun colorDinamic(temperature: Double): Color {
    var red = 33
    var green = 33
    var blue = 33

    if (temperature < 20){
        blue += (0.2575 * temperature).roundToInt()
        green += (3.883 * temperature).roundToInt()
    } else{
        red += (11.65 * temperature).roundToInt()
        green += (0.0858 * temperature).roundToInt()
    }
    if (red > 255){
        red = 255
        blue = 33
        green = 33
    } else if (blue > 255){
        red = 33
        blue = 255
        green = 33
    } else if (green > 255){
        red = 33
        blue = 33
        green = 255
    }
    return Color(red, blue, green)
}