package com.example.weatherappcompose.data.network.weather.model.response.base

import com.example.weatherappcompose.ui.screens.home.model.ForecastModel
import com.example.weatherappcompose.ui.utils.ext.dateTo12
import com.example.weatherappcompose.ui.utils.ext.getIconFromCode

data class Hour(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val cloud: Int,
    val condition: Condition,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val time: String,
    val time_epoch: Int,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val will_it_rain: Int,
    val will_it_snow: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double
)

fun Hour.toForecastModel() = ForecastModel(
    time = dateTo12(this.time),
    icon = getIconFromCode(this.condition.icon, this.is_day),
    temp = this.temp_c.toInt()

)

