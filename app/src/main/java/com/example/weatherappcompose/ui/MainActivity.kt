package com.example.weatherappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherappcompose.ui.screens.home.HomeScreen
import com.example.weatherappcompose.ui.screens.home.HomeViewModel
import com.example.weatherappcompose.ui.screens.searchcity.SearchCityScreen
import com.example.weatherappcompose.ui.screens.searchcity.SearchCityViewModel
import com.example.weatherappcompose.ui.screens.settings.SettingsScreen
import com.example.weatherappcompose.ui.screens.settings.SettingsViewModel
import com.example.weatherappcompose.ui.theme.WeatherAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            WeatherAppComposeTheme {
                NavHost(
                    navController = navController,
                    startDestination = "home_screen"
                ){
                    composable(
                        "home_screen?long={long}&lat={lat}",
                        arguments = listOf(
                            navArgument("lat"){
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            },
                            navArgument("long"){
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            }
                        )
                    ){ backStackEntry ->
                        val homeViewModel = hiltViewModel<HomeViewModel>()

                        HomeScreen(
                            navController = navController,
                            viewModel = homeViewModel,
                            savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
                        )
                    }
                    composable("search_city"){
                        val searchCityViewModel = hiltViewModel<SearchCityViewModel>()
                        SearchCityScreen(
                            navController = navController,
                            viewModel = searchCityViewModel
                        ){ geo ->
                            println("main activity geo: $geo")
                            navController.previousBackStackEntry?.savedStateHandle?.let {
                                it.set(GEO_KEY, Json.encodeToString(geo))
                            }
                            navController.popBackStack()
                        }
                    }
                    composable("settings_screen"){
                        val settingsViewModel = hiltViewModel<SettingsViewModel>()
                        SettingsScreen(
                            navController = navController,
                            viewModel = settingsViewModel
                        )
                    }
                }
            }
        }
    }

    companion object{
        const val GEO_KEY = "GEO_KEY"
    }
}

