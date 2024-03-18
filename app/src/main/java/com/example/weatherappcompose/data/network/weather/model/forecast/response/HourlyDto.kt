package com.example.weatherappcompose.data.network.weather.model.forecast.response

data class HourlyDto(
    val dew_point_2m: List<Double>,
    val is_day: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val uv_index: List<Double>,
    val weather_code: List<Int>
)