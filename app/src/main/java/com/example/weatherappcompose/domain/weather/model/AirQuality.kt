package com.example.weatherappcompose.domain.weather.model

data class AirQuality(
    val aqi: Int,
    val pm10: Double,
    val pm2_5: Double,
    val co: Double,
    val no2: Double,
    val so2: Double,
    val o3: Double
)
