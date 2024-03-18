package com.example.weatherappcompose.data.network.weather.model.air.response

data class CurrentUnitsDto(
    val carbon_monoxide: String,
    val european_aqi: String,
    val interval: String,
    val nitrogen_dioxide: String,
    val ozone: String,
    val pm10: String,
    val pm2_5: String,
    val sulphur_dioxide: String,
    val time: String,
    val us_aqi: String
)