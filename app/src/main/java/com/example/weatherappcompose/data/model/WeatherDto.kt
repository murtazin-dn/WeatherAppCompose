package com.example.weatherappcompose.data.model

import com.example.weatherappcompose.data.network.geocoding.model.GeocodingDto
import com.example.weatherappcompose.data.network.weather.model.air.response.AirQualityDto
import com.example.weatherappcompose.data.network.weather.model.forecast.response.ForecastDto

data class WeatherDto(
    val geocoding: GeocodingDto,
    val airQuality: AirQualityDto,
    val forecast: ForecastDto
)
