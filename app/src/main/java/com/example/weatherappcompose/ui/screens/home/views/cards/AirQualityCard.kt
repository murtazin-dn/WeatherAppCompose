package com.example.weatherappcompose.ui.screens.home.views.cards

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.domain.weather.model.AirQuality
import com.example.weatherappcompose.ui.theme.HourlyCardColorDisabled
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.TertiaryDark
import com.example.weatherappcompose.ui.theme.Typography
import com.example.weatherappcompose.ui.utils.ext.airQualityToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirQualityCard(modifier: Modifier, airQuality: AirQuality){
    var expanded by remember { mutableStateOf(true) }
    Card(
        modifier = modifier
            .padding(7.dp)
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Linear3,
            disabledContainerColor = Linear3
        ),
        border = BorderStroke(1.dp, Color(0x80FFFFFF)),
        shape = RoundedCornerShape(22.dp),
        onClick = {expanded = !expanded}
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row {
                Icon(
                    modifier = Modifier
                        .width(17.dp)
                        .height(17.dp),
                    painter = painterResource(id = R.drawable.ic_sun),
                    contentDescription = null,
                    tint = SecondaryDark
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = Typography.bodySmall.copy(color = SecondaryDark),
                    text = "AIR QUALITY"
                )
            }

            Text(
                modifier = Modifier.padding(top = 15.dp),
                style = Typography.titleLarge.copy(color = PrimaryDark),
                text = "${airQuality.aqi} " +
                        airQualityToString(airQuality.aqi)
            )
            DrawAirQualityLine(airQuality.aqi)
            Divider(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .padding(bottom = 10.dp),
                thickness = 1.dp,
                color = TertiaryDark
            )
            val airParams = listOf(
                Pair("CO", airQuality.co.toInt().toString()),
                Pair("NO2", airQuality.no2.toInt().toString()),
                Pair("O3", airQuality.o3.toInt().toString()),
                Pair("SO2", airQuality.so2.toString()),
                Pair("PM2,5", airQuality.pm2_5.toInt().toString()),
                Pair("PM10", airQuality.pm10.toString())
            )

            if (expanded){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    airParams.forEach {
                        AirQualityParam(param = it)
                    }
                }
            }else{
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "See more",
                        style = Typography.bodyLarge.copy(color = Color.White)
                    )
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_next),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

        }
    }
}

@Composable
private fun AirQualityParam(param: Pair<String, String>){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = Typography.bodyLarge.copy(
                color = PrimaryDark,
                fontWeight = FontWeight.SemiBold
            ),
            text = param.first
        )

        Text(
            style = Typography.bodyLarge.copy(color = PrimaryDark),
            text = param.second
        )
    }
}


@Composable
private fun DrawAirQualityLine(airQuality: Int){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
            .height(height = 6.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Color(0xFF3759B1), Color(0xFFE64393))),
                shape = RoundedCornerShape(size = 5.dp)
            )
            .drawWithContent {
                val radiusCircle = 3.dp.toPx()
                val radiusBorder = 4.dp.toPx()
                val offsetCircle =
                    Offset(((size.width - radiusBorder) / 500) * airQuality, radiusCircle)
                drawContent()
                drawCircle(
                    color = Color.White,
                    radius = radiusCircle,
                    center = offsetCircle
                )
                drawCircle(
                    color = HourlyCardColorDisabled,
                    radius = radiusBorder,
                    style = Stroke(width = 1.dp.toPx()),
                    center = offsetCircle
                )
            }
    )
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AirQualityCard_Preview() {
    Row(modifier = Modifier.fillMaxSize()) {
        AirQualityCard(
            modifier = Modifier.fillMaxWidth(),
            airQuality = AirQuality(
                aqi = 42,
                pm10 = 14.6,
                pm2_5 = 9.0,
                co = 201.0,
                no2 = 4.4,
                so2 = 1.8,
                o3 = 85.0
            )
        )
    }
}