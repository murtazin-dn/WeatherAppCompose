package com.example.weatherappcompose.ui.screens.settings.model

sealed class SettingsEvent{
    data object GetSettings: SettingsEvent()
    data class SetTemperatureUnit(val unit: TemperatureUnit): SettingsEvent()
    data class SetWindSpeedUnit(val unit: WindSpeedUnit): SettingsEvent()
    data class SetPrecipitationUnit(val unit: PrecipitationUnit): SettingsEvent()
}
