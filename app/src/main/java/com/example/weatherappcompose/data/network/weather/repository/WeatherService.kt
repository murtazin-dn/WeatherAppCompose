package com.example.weatherappcompose.data.network.weather.repository

import com.example.weatherappcompose.data.network.weather.model.forecast.response.ForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {
    @GET("forecast")
    suspend fun getForecastWeather(
        @QueryMap options: Map<String, String>
    ): Response<ForecastDto>
}