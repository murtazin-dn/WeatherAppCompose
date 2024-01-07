package com.example.weatherappcompose.ui.screens.home.model

sealed class HomeEvent{
    object OpenMenu: HomeEvent()
    object LoadWeather: HomeEvent()
    object ChooseLocation: HomeEvent()
    object ReloadScreen: HomeEvent()
}
