package com.example.weatherappcompose.data.network.weather.model.response.base

import com.example.weatherappcompose.ui.screens.home.model.ForecastModel
import com.example.weatherappcompose.ui.utils.ext.dateToDayOfWeek
import com.example.weatherappcompose.ui.utils.ext.getIconFromCode

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)

fun Forecastday.toForecastModel() = ForecastModel(
    time = dateToDayOfWeek(this.date),
    icon = getIconFromCode(this.day.condition.icon, 1),
    temp = this.day.avgtemp_c.toInt()

)