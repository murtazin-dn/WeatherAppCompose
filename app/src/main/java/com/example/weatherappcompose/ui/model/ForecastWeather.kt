package com.example.weatherappcompose.ui.model

import com.example.weatherappcompose.ui.model.base.Alerts
import com.example.weatherappcompose.ui.model.base.Current
import com.example.weatherappcompose.ui.model.base.Forecast
import com.example.weatherappcompose.ui.model.base.Location

data class ForecastWeather(
    val alerts: Alerts,
    val current: Current,
    val forecast: Forecast,
    val location: Location
)