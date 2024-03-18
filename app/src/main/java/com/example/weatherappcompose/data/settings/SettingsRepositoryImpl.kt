package com.example.weatherappcompose.data.settings

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.ui.screens.settings.model.PrecipitationUnit
import com.example.weatherappcompose.ui.screens.settings.model.SettingsViewState
import com.example.weatherappcompose.ui.screens.settings.model.TemperatureUnit
import com.example.weatherappcompose.ui.screens.settings.model.WindSpeedUnit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val context: Context
): SettingsRepository {
    private val preferences: SharedPreferences = context.getSharedPreferences("WeatherPrefs", Context.MODE_PRIVATE)

    override fun isFirstLaunch() = preferences.getBoolean(FIRST_LAUNCH_KEY, true)

    override fun appLaunched() {
        preferences.edit().putBoolean(FIRST_LAUNCH_KEY, false).apply()
    }

    override fun saveGeo(geo: Geo) {
        preferences
            .edit()
            .putString(LONGITUDE_KEY, geo.long.toString())
            .putString(LATITUDE_KEY, geo.lat.toString())
            .apply()
    }

    override fun getGeo(): Geo {
        val long = preferences.getString(LONGITUDE_KEY, "37.37") ?: "37.37"
        val lat = preferences.getString(LATITUDE_KEY, "55.45") ?: "55.45"
        return Geo(
            long = long.toDouble(),
            lat = lat.toDouble()
        )
    }

    override fun getSettings(): SettingsViewState {
        return preferences.getString(UNITS_KEY, null)?.let {
            Json.decodeFromString<SettingsViewState>(it)
        } ?: SettingsViewState()
    }

    override fun setTemperatureUnit(unit: TemperatureUnit) {
        val settings = getSettings()
        settings.temperatureUnit = unit
        saveSettings(settings)
    }

    override fun setWindSpeedUnit(unit: WindSpeedUnit) {
        val settings = getSettings()
        settings.windSpeedUnit = unit
        saveSettings(settings)
    }

    override fun setPrecipitationUnit(unit: PrecipitationUnit) {
        val settings = getSettings()
        settings.precipitationUnit = unit
        saveSettings(settings)
    }

    private fun saveSettings(settings: SettingsViewState){
        val settingsStr = Json.encodeToString(settings)
        preferences.edit().putString(UNITS_KEY, settingsStr).apply()
    }


    companion object{
        private const val FIRST_LAUNCH_KEY = "IS_FIRST_LAUNCH"
        private const val LATITUDE_KEY = "LATITUDE_KEY"
        private const val LONGITUDE_KEY = "LONGITUDE_KEY"

        private const val UNITS_KEY = "UNITS_KEY"
    }
}