package com.example.weatherappcompose.ui.screens.home.views.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.data.network.weather.model.response.base.toForecastModel
import com.example.weatherappcompose.ui.screens.home.model.ForecastModel
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.theme.HourlyCardColorDisabled
import com.example.weatherappcompose.ui.theme.HourlyCardColorEnabled
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.Typography

@Composable
fun Forecast(
    state: HomeViewState.WeatherLoaded,
    forecastTabs: ForecastTabs
){
    val forecast = mutableListOf<ForecastModel>()
    when(forecastTabs){
        ForecastTabs.HOURLY_FORECAST ->
            state.weather.forecast.forecastday.first().hour.forEach {
                forecast.add(it.toForecastModel())
            }
        ForecastTabs.WEEKLY_FORECAST ->
            state.weather.forecast.forecastday.forEach {
                forecast.add(it.toForecastModel())
            }
    }


    LazyRow(
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(forecast){ hourly ->
            Card(
                modifier = Modifier.padding(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = HourlyCardColorEnabled,
                    disabledContainerColor = HourlyCardColorDisabled
                ),
                border = BorderStroke(1.dp, Color(0x33FFFFFF)),
                shape = RoundedCornerShape(30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                        style = Typography.bodySmall.copy(color = PrimaryDark),
                        text = hourly.time
                    )
                    Image(
                        modifier = Modifier
                            .width(60.dp)
                            .padding(horizontal = 8.dp),
                        painter = painterResource(id = hourly.icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                        style = Typography.titleSmall.copy(color = PrimaryDark),
                        text = "${hourly.temp}°"
                    )
                }
            }
        }
    }
}