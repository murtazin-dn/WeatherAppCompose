package com.example.weatherappcompose.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappcompose.domain.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel() {

    private val _isFirstLaunchLivaData = MutableLiveData<Boolean>()
    val isFirstLaunchLivaData: LiveData<Boolean> get() = _isFirstLaunchLivaData

    init {
        _isFirstLaunchLivaData.value = settingsRepository.isFirstLaunch()
    }

    fun appLaunched(){
        settingsRepository.appLaunched()
        _isFirstLaunchLivaData.value = settingsRepository.isFirstLaunch()
    }
}