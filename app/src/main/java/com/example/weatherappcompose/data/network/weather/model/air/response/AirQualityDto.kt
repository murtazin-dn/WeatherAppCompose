package com.example.weatherappcompose.data.network.weather.model.air.response

data class AirQualityDto(
    val current: CurrentDto,
    val current_units: CurrentUnitsDto,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)