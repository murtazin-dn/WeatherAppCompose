package com.example.weatherappcompose.widget.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.location.LocationTracker
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.domain.weather.repository.WeatherRepository
import com.example.weatherappcompose.widget.utils.updateWeatherWidget
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class WeatherWidgetWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository,
    private val locationTracker: LocationTracker
) : CoroutineWorker(context, workParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        println("gggggg")
        val geo: Geo = locationTracker.getCurrentLocation()?.let { location ->
            val _geo = Geo(
                lat = location.latitude,
                long = location.longitude
            )
            settingsRepository.saveGeo(_geo)
            _geo
        } ?: settingsRepository.getGeo()

        val response = weatherRepository.getWeather(geo)

        return@withContext try {
            when(response) {
                is Response.Error -> Result.failure()
                is Response.Success -> {

                    println("gggggg11111111111")
                    updateWeatherWidget(response.data.current.temp, response.data.current.icon, context)
                    Result.success()
                }
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}