package com.example.weatherappcompose.ui.screens.searchcity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.geocodingweather.GeocodingWeatherRepository
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.ui.screens.searchcity.model.SearchCityEvent
import com.example.weatherappcompose.ui.screens.searchcity.model.SearchCityViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val geocodingRepository: GeocodingWeatherRepository,
    private val settingsRepository: SettingsRepository
): ViewModel() {

    private val _searchCityState: MutableLiveData<SearchCityViewState> =
        MutableLiveData(SearchCityViewState.SearchLoaded(listOf()))
    val searchCityState: LiveData<SearchCityViewState> get() = _searchCityState

    fun obtainEvent(event: SearchCityEvent){
        when(event){
            is SearchCityEvent.SearchCity -> reduceSearchCity(event.name)
            is SearchCityEvent.SelectCity -> reduceSelectCity(event.geo)
        }
    }

    private fun reduceSelectCity(geo: GeocodingWeather) {
        val _geo = Geo(
            long = geo.long,
            lat = geo.lat
        )
        settingsRepository.saveGeo(_geo)
        _searchCityState.postValue(SearchCityViewState.CitySelected(_geo))
    }

    private fun reduceSearchCity(name: String) {
        _searchCityState.postValue(SearchCityViewState.SearchLoading)
        if(name.isNotBlank()) viewModelScope.launch(Dispatchers.IO) {
            val state = when(val response = geocodingRepository.getGeocoding(name)){
                is Response.Error -> SearchCityViewState.SearchError
                is Response.Success -> {
                    if (response.data.isNotEmpty()) SearchCityViewState.SearchLoaded(response.data)
                    else SearchCityViewState.SearchNoData
                }
            }
            _searchCityState.postValue(state)
        } else _searchCityState.postValue(SearchCityViewState.SearchNoData)
    }


}