package com.example.weatherappcompose.domain.settings

import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.ui.screens.settings.model.PrecipitationUnit
import com.example.weatherappcompose.ui.screens.settings.model.SettingsViewState
import com.example.weatherappcompose.ui.screens.settings.model.TemperatureUnit
import com.example.weatherappcompose.ui.screens.settings.model.WindSpeedUnit

interface SettingsRepository{
    fun isFirstLaunch(): Boolean
    fun appLaunched()

    fun saveGeo(geo: Geo)
    fun getGeo(): Geo

    fun getSettings(): SettingsViewState
    fun setTemperatureUnit(unit: TemperatureUnit)
    fun setWindSpeedUnit(unit: WindSpeedUnit)
    fun setPrecipitationUnit(unit: PrecipitationUnit)
}