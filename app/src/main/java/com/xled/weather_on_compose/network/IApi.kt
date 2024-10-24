package com.xled.weather_on_compose.network

import com.xled.weather_on_compose.models.Prediction
import retrofit2.http.GET

interface iApi {
    @GET("forecast?latitude=47.2313&longitude=39.7233&current=temperature_2m,precipitation,wind_speed_10m&hourly=temperature_2m,apparent_temperature,precipitation_probability,precipitation,visibility,wind_speed_10m&timezone=Europe%2FMoscow&forecast_days=1")
    suspend fun getPrediction(): Prediction
}