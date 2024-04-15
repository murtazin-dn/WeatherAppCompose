package com.example.weatherappcompose.widget.utils

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import com.example.weatherappcompose.widget.WeatherWidget
import com.example.weatherappcompose.widget.view.iconKey
import com.example.weatherappcompose.widget.view.tempKey

suspend fun updateWeatherWidget(temp: Int, icon: Int, context: Context) {
    GlanceAppWidgetManager(context).getGlanceIds(WeatherWidget::class.java).forEach { glanceId ->
        updateAppWidgetState(context, glanceId) { prefs ->
            prefs[tempKey] = temp
            prefs[iconKey] = icon
        }
    }
    WeatherWidget().updateAll(context)
}