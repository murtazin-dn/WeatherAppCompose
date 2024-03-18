package com.example.weatherappcompose.data.network.geocoding.repository

import com.example.weatherappcompose.data.network.geocoding.model.GeocodingDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GeocodingService {

    @GET("1.x/")
    suspend fun getGeocoding(
        @QueryMap options: Map<String, String>
    ): Response<GeocodingDto>
}