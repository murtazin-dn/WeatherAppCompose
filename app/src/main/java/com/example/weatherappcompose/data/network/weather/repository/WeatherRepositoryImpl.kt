package com.example.weatherappcompose.data.network.weather.repository

import com.example.weatherappcompose.data.util.API_KEY
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.data.network.weather.model.response.ForecastWeatherResponse
import com.example.weatherappcompose.domain.weather.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
): WeatherRepository {
    override suspend fun getForecastWeather(location: String?): Flow<Response<ForecastWeatherResponse>> {
        return flow {
            val options = hashMapOf<String, String>()
            options.put("q", location ?: "New York")
            options.put("days", "14")
            options.put("aqi", "yes")
            options.put("key", API_KEY)
            val response = weatherService.getForecastWeather(options)
            if (response.isSuccessful){
                val body = response.body()!!
                emit(Response.Success(body))
            }else{
                emit(Response.Error(response.errorBody()?.charStream()?.readText()!!))
            }
        }
    }
}