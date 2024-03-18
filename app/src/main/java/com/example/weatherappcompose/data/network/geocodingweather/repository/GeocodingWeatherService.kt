package com.example.weatherappcompose.data.network.geocodingweather.repository

import com.example.weatherappcompose.data.network.geocodingweather.model.GeocodingWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GeocodingWeatherService {
    @GET("search")
    suspend fun getGeocoding(@QueryMap options: Map<String, String>): Response<GeocodingWeatherDto>
}