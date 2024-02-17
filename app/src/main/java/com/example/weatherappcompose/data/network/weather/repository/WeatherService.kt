package com.example.weatherappcompose.data.network.weather.repository

import com.example.weatherappcompose.data.network.weather.model.response.ForecastWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {
    @GET("forecast.json")
    suspend fun getForecastWeather(
        @QueryMap options: Map<String, String>
    ): Response<ForecastWeatherResponse>
}