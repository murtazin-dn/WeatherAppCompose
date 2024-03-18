package com.example.weatherappcompose.ui.screens.searchcity.model

import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather

sealed class SearchCityEvent{
    data class SearchCity(val name: String): SearchCityEvent()
    data class SelectCity(val geo: GeocodingWeather): SearchCityEvent()
}
