package com.xled.weather_on_compose.logic

import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt


fun interpolateColor(startColor: Color, endColor: Color, fraction: Double): Color {
    val red = (startColor.red + (endColor.red - startColor.red) * fraction * 255).roundToInt()
    val green = (startColor.green + (endColor.green - startColor.green) * fraction * 255).roundToInt()
    val blue = (startColor.blue + (endColor.blue - startColor.blue) * fraction * 255).roundToInt()

    return Color(red, green, blue)
}