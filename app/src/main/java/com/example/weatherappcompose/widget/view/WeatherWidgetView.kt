package com.example.weatherappcompose.widget.view

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.widget.workmanager.WeatherWidgetWorker

@Composable
fun WeatherWidgetView(prefs: Preferences) {
    val temp = prefs[tempKey] ?: 17
    val icon = prefs[iconKey] ?: R.drawable.day_116
    Row(
        modifier = GlanceModifier
            .fillMaxSize()
            .clickable {
                actionRunCallback<WidgetRefreshAction>()
            }
    ) {
        Image(
            modifier = GlanceModifier
                .width(60.dp),
            provider = ImageProvider(icon),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            style = TextStyle(
                color = ColorProvider(PrimaryDark),
                fontWeight = FontWeight.Normal,
                fontSize = 46.sp
            ),
            text = "${temp}°"
        )
    }
}

class WidgetRefreshAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        println("gggggg11111111111tuhrftghfdg")

        val myWorkRequest = OneTimeWorkRequestBuilder<WeatherWidgetWorker>().build()
        WorkManager.getInstance(context).enqueue(myWorkRequest)

    }

}

val tempKey = intPreferencesKey("tempKey")
val iconKey = intPreferencesKey("iconKey")