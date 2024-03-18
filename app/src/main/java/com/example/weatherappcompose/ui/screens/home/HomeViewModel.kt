package com.example.weatherappcompose.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.location.LocationTracker
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.domain.weather.repository.WeatherRepository
import com.example.weatherappcompose.ui.base.EventHandler
import com.example.weatherappcompose.ui.screens.home.model.HomeEvent
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository,
    private val locationTracker: LocationTracker

) : ViewModel(), EventHandler<HomeEvent> {

    private val _homeViewState: MutableLiveData<HomeViewState> =
        MutableLiveData(HomeViewState.WeatherLoad)
    val homeViewState: LiveData<HomeViewState> = _homeViewState

    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadWeather -> reduceLoadWeather(event.geo)
        }
    }

    private fun reduceLoadWeather(geo: Geo?) {
        println("geo: $geo")
        _homeViewState.value = HomeViewState.WeatherLoad
        viewModelScope.launch(Dispatchers.IO) {
            val _geo: Geo = geo
                ?: locationTracker.getCurrentLocation()?.let { location ->
                    val _geo = Geo(
                        lat = location.latitude,
                        long = location.longitude
                    )
                    settingsRepository.saveGeo(_geo)
                    _geo
                } ?: settingsRepository.getGeo()

            val state = when (val response = weatherRepository.getWeather(_geo)) {
                is Response.Error -> HomeViewState.WeatherError
                is Response.Success -> HomeViewState.WeatherLoaded(response.data)
            }
            _homeViewState.postValue(state)
        }
    }
}
