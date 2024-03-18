package com.example.weatherappcompose.ui.screens.searchcity.model

import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather

sealed class SearchCityViewState{
    data class SearchLoaded(val search: List<GeocodingWeather>): SearchCityViewState()
    object SearchLoading: SearchCityViewState()
    object SearchNoData: SearchCityViewState()
    object SearchError: SearchCityViewState()
    data class CitySelected(val geo: Geo): SearchCityViewState()

}
