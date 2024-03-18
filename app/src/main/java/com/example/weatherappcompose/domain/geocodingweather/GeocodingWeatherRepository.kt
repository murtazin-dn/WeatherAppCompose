package com.example.weatherappcompose.domain.geocodingweather

import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather

interface GeocodingWeatherRepository {
    suspend fun getGeocoding(name: String): Response<List<GeocodingWeather>>
}