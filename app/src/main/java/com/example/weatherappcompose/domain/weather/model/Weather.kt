package com.example.weatherappcompose.domain.weather.model

data class Weather(
    val city: String,
    val current: Current,
    val hourly: List<HourlyDaily>,
    val daily: List<HourlyDaily>
)
