package com.example.weatherappcompose.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.ui.MainActivity
import com.example.weatherappcompose.ui.screens.home.model.HomeEvent
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.screens.home.views.HomeViewDisplay
import com.example.weatherappcompose.ui.screens.home.views.HomeViewError
import com.example.weatherappcompose.ui.screens.home.views.HomeViewLoading
import kotlinx.serialization.json.Json

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    savedStateHandle: SavedStateHandle?
) {
    val viewState = viewModel.homeViewState.observeAsState()
    var geo: Geo? = null
    if (savedStateHandle != null) {
        val lat by savedStateHandle.getLiveData<String>(MainActivity.GEO_KEY).observeAsState()
        LaunchedEffect(lat) {
            lat?.let {
                geo = Json.decodeFromString<Geo>(it)
                // do something
                savedStateHandle.remove<String>(MainActivity.GEO_KEY)
            }
        }
    }
    when (val state = viewState.value) {
        is HomeViewState.WeatherError -> HomeViewError {
            viewModel.obtainEvent(event = HomeEvent.LoadWeather(geo))
        }

        HomeViewState.WeatherLoad -> HomeViewLoading()
        is HomeViewState.WeatherLoaded -> HomeViewDisplay(
            state = state,
            navigateToSettings = {
                navController.navigate("settings_screen")
            },
            navigateToSearchCity = {
                navController.navigate("search_city")
            }
        )

        null -> TODO()
    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainEvent(event = HomeEvent.LoadWeather(geo))
    })
}
