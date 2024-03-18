package com.example.weatherappcompose.data.network.geocodingweather.model

data class GeocodingWeatherDto(
    val generationtime_ms: Double,
    val results: List<ResultDto>?
)