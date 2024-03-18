package com.example.weatherappcompose.data.network.weather.model.air.response

data class CurrentDto(
    val carbon_monoxide: Double,
    val european_aqi: Int,
    val interval: Int,
    val nitrogen_dioxide: Double,
    val ozone: Double,
    val pm10: Double,
    val pm2_5: Double,
    val sulphur_dioxide: Double,
    val time: String,
    val us_aqi: Int
)