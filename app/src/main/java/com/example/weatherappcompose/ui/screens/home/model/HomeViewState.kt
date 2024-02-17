package com.example.weatherappcompose.ui.screens.home.model

import com.example.weatherappcompose.data.network.weather.model.response.ForecastWeatherResponse

sealed class HomeViewState{
    object WeatherLoad: HomeViewState()
    object WeatherError: HomeViewState()
    data class WeatherLoaded(val weather: ForecastWeatherResponse): HomeViewState()
}
