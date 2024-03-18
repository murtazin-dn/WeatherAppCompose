package com.example.weatherappcompose.ui.screens.home.model

import com.example.weatherappcompose.data.settings.model.Geo

sealed class HomeEvent{
    data class LoadWeather(val geo: Geo?): HomeEvent()
}
