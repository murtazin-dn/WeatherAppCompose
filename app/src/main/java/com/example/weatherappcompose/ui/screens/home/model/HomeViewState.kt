package com.example.weatherappcompose.ui.screens.home.model

import com.example.weatherappcompose.ui.model.ForecastWeather

sealed class HomeViewState{
    object WeatherLoad: HomeViewState()
    object WeatherError: HomeViewState()
    data class WeatherLoaded(val weather: ForecastWeather): HomeViewState()
}
