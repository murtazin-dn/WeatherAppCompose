package com.example.weatherappcompose.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappcompose.ui.base.EventHandler
import com.example.weatherappcompose.ui.screens.home.model.HomeEvent
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel(), EventHandler<HomeEvent> {

    private val _homeViewState: MutableLiveData<HomeViewState> = MutableLiveData(HomeViewState.WeatherLoad)
    val homeViewState: LiveData<HomeViewState> = _homeViewState

    override fun obtainEvent(event: HomeEvent) {
        TODO("Not yet implemented")
    }
}