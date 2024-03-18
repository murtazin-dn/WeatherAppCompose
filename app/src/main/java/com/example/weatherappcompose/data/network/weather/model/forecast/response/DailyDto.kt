package com.example.weatherappcompose.data.network.weather.model.forecast.response

data class DailyDto(
    val precipitation_sum: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val uv_index_max: List<Double>,
    val weather_code: List<Int>
)