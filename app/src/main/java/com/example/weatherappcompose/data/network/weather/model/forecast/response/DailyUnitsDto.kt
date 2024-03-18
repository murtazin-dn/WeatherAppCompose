package com.example.weatherappcompose.data.network.weather.model.forecast.response

data class DailyUnitsDto(
    val precipitation_sum: String,
    val sunrise: String,
    val sunset: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val uv_index_max: String,
    val weather_code: String
)