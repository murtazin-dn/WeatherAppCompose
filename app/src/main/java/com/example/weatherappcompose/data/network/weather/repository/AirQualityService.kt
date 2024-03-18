package com.example.weatherappcompose.data.network.weather.repository

import com.example.weatherappcompose.data.network.weather.model.air.response.AirQualityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AirQualityService {

    @GET("air-quality")
    suspend fun getAirQuality(
        @QueryMap options: Map<String, String>
    ): Response<AirQualityDto>


}