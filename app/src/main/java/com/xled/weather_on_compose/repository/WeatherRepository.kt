package com.xled.weather_on_compose.repository

import com.xled.weather_on_compose.models.Prediction
import com.xled.weather_on_compose.network.iApi

class WeatherRepository(private val api: iApi) {
    suspend fun getPrediction(): Result<Prediction> = try {
        Result.success(api.getPrediction())
    } catch (e: Exception){
        Result.failure(e)
    }
}