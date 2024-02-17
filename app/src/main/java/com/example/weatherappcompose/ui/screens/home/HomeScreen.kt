package com.example.weatherappcompose.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.weatherappcompose.ui.screens.home.model.HomeEvent
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.screens.home.views.HomeViewDisplay
import com.example.weatherappcompose.ui.screens.home.views.HomeViewError
import com.example.weatherappcompose.ui.screens.home.views.HomeViewLoading

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
){
    val viewState = viewModel.homeViewState.observeAsState()

    when(val state = viewState.value){
        is HomeViewState.WeatherError -> HomeViewError()
        HomeViewState.WeatherLoad -> HomeViewLoading()
        is HomeViewState.WeatherLoaded -> HomeViewDisplay(state)
        null -> TODO()
    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainEvent(event = HomeEvent.LoadWeather)
    })
}