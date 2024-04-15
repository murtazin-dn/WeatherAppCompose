package com.example.weatherappcompose

import android.app.Application
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.weatherappcompose.widget.workmanager.MyWorkerFactory
import com.example.weatherappcompose.widget.workmanager.WeatherWidgetWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App: Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: MyWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

//    private val myWorkRequest = OneTimeWorkRequestBuilder<WeatherWidgetWorker>().build()
    private val myWorkRequest = PeriodicWorkRequestBuilder<WeatherWidgetWorker>(
        60,
        TimeUnit.MINUTES,
        55,
        TimeUnit.MINUTES
    ).build()

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(this, workManagerConfiguration)

        WorkManager.getInstance(applicationContext).enqueue(myWorkRequest)
    }


}