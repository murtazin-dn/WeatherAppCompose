package com.example.weatherappcompose.domain.weather.repository

import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.weather.model.Weather

interface WeatherRepository {
    suspend fun getWeather(geo: Geo): Response<Weather>
}