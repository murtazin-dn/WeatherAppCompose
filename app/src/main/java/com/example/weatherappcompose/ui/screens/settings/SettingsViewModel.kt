package com.example.weatherappcompose.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.ui.base.EventHandler
import com.example.weatherappcompose.ui.screens.settings.model.SettingsEvent
import com.example.weatherappcompose.ui.screens.settings.model.SettingsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel(), EventHandler<SettingsEvent> {

    private val _settingsViewState = MutableLiveData<SettingsViewState>()
    val settingsViewState: LiveData<SettingsViewState> get() = _settingsViewState

    override fun obtainEvent(event: SettingsEvent) {
        when(event){
            SettingsEvent.GetSettings -> {
                _settingsViewState.value = settingsRepository.getSettings()
            }
            is SettingsEvent.SetPrecipitationUnit -> {
                settingsRepository.setPrecipitationUnit(unit = event.unit)
                _settingsViewState.value = settingsRepository.getSettings()
            }
            is SettingsEvent.SetTemperatureUnit -> {
                settingsRepository.setTemperatureUnit(unit = event.unit)
                _settingsViewState.value = settingsRepository.getSettings()
            }
            is SettingsEvent.SetWindSpeedUnit -> {
                settingsRepository.setWindSpeedUnit(unit = event.unit)
                _settingsViewState.value = settingsRepository.getSettings()
            }
        }
    }


}