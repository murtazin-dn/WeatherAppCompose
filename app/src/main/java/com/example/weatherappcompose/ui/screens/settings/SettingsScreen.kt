package com.example.weatherappcompose.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.screens.settings.model.PrecipitationUnit
import com.example.weatherappcompose.ui.screens.settings.model.SettingsEvent
import com.example.weatherappcompose.ui.screens.settings.model.TemperatureUnit
import com.example.weatherappcompose.ui.screens.settings.model.WeatherUnit
import com.example.weatherappcompose.ui.screens.settings.model.WindSpeedUnit
import com.example.weatherappcompose.ui.theme.Blue
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.TertiaryDark
import com.example.weatherappcompose.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    navController: NavController
) {
    val state = viewModel.settingsViewState.observeAsState()
    LaunchedEffect(key1 = state, block = { viewModel.obtainEvent(SettingsEvent.GetSettings) })
    Column(
        modifier = Modifier
            .background(Linear3)
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.weather),
                    style = Typography.titleMedium.copy(color = PrimaryDark)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() },
                    content = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(5.dp)
                                .rotate(180f),
                            tint = TertiaryDark
                        )
                    }
                )
            },
            windowInsets = WindowInsets.systemBars,
            colors = TopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
                navigationIconContentColor = TertiaryDark,
                titleContentColor = PrimaryDark,
                actionIconContentColor = TertiaryDark
            )
        )
        state.value?.let { settings ->
            Column {
                MenuItem(unit = settings.temperatureUnit) { unit ->
                    viewModel.obtainEvent(SettingsEvent.SetTemperatureUnit(unit))
                }
                MenuItem(unit = settings.windSpeedUnit) { unit ->
                    viewModel.obtainEvent(SettingsEvent.SetWindSpeedUnit(unit))
                }
                MenuItem(unit = settings.precipitationUnit) { unit ->
                    viewModel.obtainEvent(SettingsEvent.SetPrecipitationUnit(unit))
                }
            }
        }
    }

}

@Composable
fun <T> MenuItem(unit: T, onSelect: (T) -> Unit) where T : Enum<*>, T : WeatherUnit {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable { expanded = !expanded }
        ) {
            Text(
                modifier = Modifier.weight(1f),
                style = Typography.bodyLarge.copy(color = PrimaryDark),
                text = stringResource(
                    id = when (unit) {
                        is TemperatureUnit -> R.string.temperature
                        is WindSpeedUnit -> R.string.wind_speed
                        is PrecipitationUnit -> R.string.precipitation
                        else -> R.string.unknown_code
                    }
                )
            )
            Text(
                style = Typography.bodyLarge.copy(color = Blue),
                text = stringResource(id = unit.toStringRes())
            )
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .rotate(270f),
                    tint = TertiaryDark
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .rotate(90f),
                    tint = TertiaryDark
                )
            }
        }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                val values = when (unit) {
                    is TemperatureUnit -> enumValues<TemperatureUnit>()
                    is WindSpeedUnit -> enumValues<WindSpeedUnit>()
                    is PrecipitationUnit -> enumValues<PrecipitationUnit>()
                    else -> throw RuntimeException("unknown type temperature unit")
                }
                values.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(
                                style = Typography.bodyLarge.copy(color = Color.Black),
                                text = stringResource(id = it.toStringRes())
                            )
                        },
                        onClick = {
                            onSelect.invoke(it as T)
                            expanded = false
                        }
                    )
                }
            }
    }

}

@Composable
@Preview
fun MenuItem_Preview() {
    MenuItem(WindSpeedUnit.KMH) {}
}