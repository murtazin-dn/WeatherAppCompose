package com.example.weatherappcompose.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.data.util.Response
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
    private val weatherRepository: WeatherRepository
): ViewModel(), EventHandler<HomeEvent> {

    private val _homeViewState: MutableLiveData<HomeViewState> = MutableLiveData(HomeViewState.WeatherLoad)
    val homeViewState: LiveData<HomeViewState> = _homeViewState

    override fun obtainEvent(event: HomeEvent) {
        when(event){
            HomeEvent.ChooseLocation -> TODO()
            HomeEvent.LoadWeather -> {
                _homeViewState.value = HomeViewState.WeatherLoad
                viewModelScope.launch(Dispatchers.IO) {
                    weatherRepository.getForecastWeather("Orenburg").collect{ response ->
                        val state = when(response){
                            is Response.Error -> HomeViewState.WeatherError
                            is Response.Success -> HomeViewState.WeatherLoaded(response.data)
                        }
                        _homeViewState.postValue(state)
                    }
                }
            }
            HomeEvent.OpenMenu -> TODO()
            HomeEvent.ReloadScreen -> TODO()
        }
    }
}