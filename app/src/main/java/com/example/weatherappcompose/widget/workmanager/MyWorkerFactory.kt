package com.example.weatherappcompose.widget.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.weatherappcompose.domain.location.LocationTracker
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.domain.weather.repository.WeatherRepository
import javax.inject.Inject

class MyWorkerFactory @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository,
    private val locationTracker: LocationTracker
) : WorkerFactory(){
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when(workerClassName){
            WeatherWidgetWorker::class.java.name -> WeatherWidgetWorker(
                appContext,
                workerParameters,
                weatherRepository,
                settingsRepository,
                locationTracker
            )
            else -> null
        }
    }
}