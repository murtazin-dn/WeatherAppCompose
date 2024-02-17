package com.example.weatherappcompose.data.network.weather.model.response.base

data class Astro(
    val moon_illumination: Double,
    val moon_phase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)