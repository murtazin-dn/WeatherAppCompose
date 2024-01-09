package com.example.weatherappcompose.ui.screens.home.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.model.ForecastWeather
import com.example.weatherappcompose.ui.model.base.AirQuality
import com.example.weatherappcompose.ui.model.base.Alert
import com.example.weatherappcompose.ui.model.base.Alerts
import com.example.weatherappcompose.ui.model.base.Astro
import com.example.weatherappcompose.ui.model.base.Condition
import com.example.weatherappcompose.ui.model.base.Current
import com.example.weatherappcompose.ui.model.base.Day
import com.example.weatherappcompose.ui.model.base.Forecast
import com.example.weatherappcompose.ui.model.base.Forecastday
import com.example.weatherappcompose.ui.model.base.Hour
import com.example.weatherappcompose.ui.model.base.Location
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.theme.interFamily
import com.example.weatherappcompose.ui.utils.ext.dateToDate

@Composable
fun HomeViewDisplay(
    state: HomeViewState.WeatherLoaded
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.wp_minimalist_sunset_wallpapers),
            contentDescription = "Wallpaper",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ){
                Icon(
                    modifier = Modifier.width(40.dp),
                    painter = painterResource(id = R.drawable.ic_hamburger),
                    contentDescription = "Hamburger",
                    tint = Color.White
                )
                Column(
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        Text(
                            color = Color.White,
                            fontFamily = interFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 17.sp,
                            text = state.weather.location.region
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_expand_more),
                            contentDescription = "Expand more",
                            tint = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        fontFamily = interFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White,
                        text = state.weather.current.last_updated.dateToDate()
                    )
                }
                Box(modifier = Modifier.width(50.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 160.sp,
                    color = Color.White,
                    text = state.weather.current.temp_c.toInt().toString()
                )
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 70.sp,
                    color = Color.White,
                    text = "°"
                )
            }

        }

    }
}

@Composable
@Preview
fun HomeViewDisplay_Preview() {
    HomeViewDisplay(
        state = data
    )
}


private val data = HomeViewState.WeatherLoaded(
    ForecastWeather(
        location = Location(
            name = "New York",
            region = "New York",
            country = "United States of America",
            lat = 40.71,
            lon = -74.01,
            tz_id = "America/New_York",
            localtime_epoch = 1658522976,
            localtime = "2022-07-22 16:49"
        ),
        current = Current(
            last_updated_epoch = 1658522700,
            last_updated = "2022-07-22 16:45",
            temp_c = 34.4,
            temp_f = 93.9,
            is_day = 1,
            condition = Condition(
                text = "Partly cloudy",
                icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                code = 1003
            ),
            wind_mph = 16.1,
            wind_kph = 25.9,
            wind_degree = 180,
            wind_dir = "S",
            pressure_mb = 1011,
            pressure_in = 29.85,
            precip_mm = 0,
            precip_in = 0,
            humidity = 31,
            cloud = 75,
            feelslike_c = 37,
            feelslike_f = 98.6,
            vis_km = 16,
            vis_miles = 9,
            uv = 8,
            gust_mph = 11.6,
            gust_kph = 18.7,
            air_quality = AirQuality(
                co = 293.70001220703125,
                no2 = 18.5,
                o3 = 234.60000610351562,
                so2 = 12,
                pm2_5 = 13.600000381469727,
                pm10 = 15,
                us_epa_index = 1,
                gb_defra_index = 2
            )
        ),
        forecast = Forecast(
            forecastday = listOf(
                Forecastday(
                    date = "2022-07-22",
                    date_epoch = 1658448000,
                    day = Day(
                        maxtemp_c = 35.9,
                        maxtemp_f = 96.6,
                        mintemp_c = 26.3,
                        mintemp_f = 79.3,
                        avgtemp_c = 30.7,
                        avgtemp_f = 87.3,
                        maxwind_mph = 12.8,
                        maxwind_kph = 20.5,
                        totalprecip_mm = 0,
                        totalprecip_in = 0,
                        avgvis_km = 10,
                        avgvis_miles = 6,
                        avghumidity = 53,
                        daily_will_it_rain = 0,
                        daily_chance_of_rain = 0,
                        daily_will_it_snow = 0,
                        daily_chance_of_snow = 0,
                        uv = 8,
                        condition = Condition(
                            text = "Sunny",
                            icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                            code = 1000
                        )
                    ),
                    astro = Astro(
                        sunrise = "05:44 AM",
                        sunset = "08:20 PM",
                        moonrise = "12:58 AM",
                        moonset = "03:35 PM",
                        moon_phase = "Last Quarter",
                        moon_illumination = "36"
                    ),
                    hour = listOf(
                        Hour(
                            time_epoch = 1658462400,
                            time = "2022-07-22 00:00",
                            temp_c = 28.7,
                            temp_f = 83.7,
                            is_day = 0,
                            condition = Condition(
                                text = "Clear",
                                icon = "//cdn.weatherapi.com/weather/64x64/night/113.png",
                                code = 1000
                            ),
                            wind_mph = 9.4,
                            wind_kph = 15.1,
                            wind_degree = 265,
                            wind_dir = "W",
                            pressure_mb = 1007,
                            pressure_in = 29.73,
                            precip_mm = 0,
                            precip_in = 0,
                            humidity = 58,
                            cloud = 19,
                            feelslike_c = 30.5,
                            feelslike_f = 86.9,
                            windchill_c = 28.7,
                            windchill_f = 83.7,
                            heatindex_c = 30.5,
                            heatindex_f = 86.9,
                            dewpoint_c = 19.6,
                            dewpoint_f = 67.3,
                            will_it_rain = 0,
                            chance_of_rain = 0,
                            will_it_snow = 0,
                            chance_of_snow = 0,
                            vis_km = 10,
                            vis_miles = 6,
                            gust_mph = 15,
                            gust_kph = 24.1,
                            uv = 1
                        )
                    )
                )
            )
        ),
        alerts = Alerts(
            alert = listOf(
                Alert(
                    headline = "NWS New York City - Upton (Long Island and New York City)",
                    msgtype = null,
                    severity = null,
                    urgency = null,
                    areas = null,
                    category = "Extreme temperature value",
                    certainty = null,
                    event = "Heat Advisory",
                    note = null,
                    effective = "2022-07-21T19:38:00+00:00",
                    expires = "2022-07-25T00:00:00+00:00",
                    desc = "...HEAT ADVISORY REMAINS IN EFFECT UNTIL 8 PM EDT SUNDAY... * WHAT...Heat index values up to 105. * WHERE...Eastern Passaic Hudson Western Bergen Western Essex Eastern Bergen and Eastern Essex Counties. * WHEN...Until 8 PM EDT Sunday. * IMPACTS...High temperatures and high humidity may cause heat illnesses to occur.",
                    instruction = ""
                )
            )
        )
    )
)




