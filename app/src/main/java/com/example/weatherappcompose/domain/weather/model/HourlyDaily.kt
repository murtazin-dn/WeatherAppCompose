package com.example.weatherappcompose.domain.weather.model

data class HourlyDaily(
    val time: String,
    val icon: Int,
    val temp: Int
)
