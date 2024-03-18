package com.example.weatherappcompose.ui.screens.settings.model

import com.example.weatherappcompose.R
import kotlinx.serialization.Serializable

@Serializable
data class SettingsViewState(
    var temperatureUnit: TemperatureUnit = TemperatureUnit.C,
    var windSpeedUnit: WindSpeedUnit = WindSpeedUnit.KMH,
    var precipitationUnit: PrecipitationUnit = PrecipitationUnit.MILLIMETER
)

@Serializable
enum class TemperatureUnit : WeatherUnit{
    C{ override fun toStringRes() = R.string.c },
    F{ override fun toStringRes() = R.string.f }
}


@Serializable
enum class WindSpeedUnit : WeatherUnit{
    KMH{ override fun toStringRes() = R.string.kmh },
    MPH{ override fun toStringRes() = R.string.mph },
    MS{ override fun toStringRes() = R.string.ms },
    KNOTS{ override fun toStringRes() = R.string.knots }
}

@Serializable
enum class PrecipitationUnit : WeatherUnit{
    MILLIMETER{ override fun toStringRes() = R.string.millimeter },
    INCH{ override fun toStringRes() = R.string.inch }
}

fun WeatherUnit.toStringRes(): Int{
    return when(this){
        is TemperatureUnit -> {
            when(this) {
                TemperatureUnit.C -> R.string.c
                TemperatureUnit.F -> R.string.f
            }
        }
        is WindSpeedUnit -> {
            when(this){
                WindSpeedUnit.KMH -> R.string.kmh
                WindSpeedUnit.MPH -> R.string.mph
                WindSpeedUnit.MS -> R.string.ms
                WindSpeedUnit.KNOTS -> R.string.knots
            }
        }
        is PrecipitationUnit -> {
            when(this){
                PrecipitationUnit.MILLIMETER -> R.string.millimeter
                PrecipitationUnit.INCH -> R.string.inch
            }
        }

        else -> R.string.unknown_code
    }
}
interface WeatherUnit {
    fun toStringRes(): Int
}