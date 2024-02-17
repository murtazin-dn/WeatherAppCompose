package com.example.weatherappcompose.domain.weather.repository

import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.data.network.weather.model.response.ForecastWeatherResponse
import kotlinx.coroutines.flow.Flow
interface WeatherRepository {
    suspend fun getForecastWeather(location: String?): Flow<Response<ForecastWeatherResponse>>
}