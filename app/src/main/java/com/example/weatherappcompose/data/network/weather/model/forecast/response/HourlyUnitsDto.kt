package com.example.weatherappcompose.data.network.weather.model.forecast.response

data class HourlyUnitsDto(
    val dew_point_2m: String,
    val is_day: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String
)