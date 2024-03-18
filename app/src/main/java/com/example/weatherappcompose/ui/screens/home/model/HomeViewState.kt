package com.example.weatherappcompose.ui.screens.home.model

import com.example.weatherappcompose.domain.weather.model.Weather

sealed class HomeViewState{
    object WeatherLoad: HomeViewState()
    object WeatherError: HomeViewState()
    data class WeatherLoaded(val weather: Weather): HomeViewState()
}
