package com.xled.weather_on_compose

data class Prediction(
    val latitude: Double,
    val longitude: Double,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val elevation: Int,
    val current_units: CurrentUnits,
    val current: Current,
    val hourly_units: HourlyUnits,
    val hourly: Hourly
)

data class CurrentUnits(
    val time: String,
    val interval: String,
    val temperature_2m: String,
    val precipitation: String,
    val wind_speed_10m: String
)

data class Current(
    val time: String,
    val interval: Int,
    val temperature_2m: Double,
    val precipitation: Double,
    val wind_speed_10m: Double
)

data class HourlyUnits(
    val time: String,
    val temperature_2m: String,
    val apparent_temperature: String,
    val precipitation_probability: String,
    val precipitation: String,
    val visibility: String,
    val wind_speed_10m: String
)

data class Hourly(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val apparent_temperature: List<Double>,
    val precipitation_probability: List<Double>,
    val precipitation: List<Double>,
    val visibility: List<Double>,
    val wind_speed_10m: List<Double>
)
