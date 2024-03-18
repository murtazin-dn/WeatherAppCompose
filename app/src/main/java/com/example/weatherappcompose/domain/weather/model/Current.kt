package com.example.weatherappcompose.domain.weather.model

data class Current(
    val temp: Int,
    val tempMin: Int,
    val tempMax: Int,
    val weatherDescription: Int,
    val airQuality: AirQuality,
    val uvIndex: Int,
    val sun: Sun,
    val wind: Wind,
    val precipitation: Precipitation,
    val humidity: Int,
    val dewPoint: Int,
    val feelsLike: Int
)
