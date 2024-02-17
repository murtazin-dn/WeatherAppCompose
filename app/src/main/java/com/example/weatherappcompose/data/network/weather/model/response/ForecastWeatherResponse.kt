package com.example.weatherappcompose.data.network.weather.model.response

import com.example.weatherappcompose.data.network.weather.model.response.base.AirQuality
import com.example.weatherappcompose.data.network.weather.model.response.base.Alert
import com.example.weatherappcompose.data.network.weather.model.response.base.Alerts
import com.example.weatherappcompose.data.network.weather.model.response.base.Astro
import com.example.weatherappcompose.data.network.weather.model.response.base.Condition
import com.example.weatherappcompose.data.network.weather.model.response.base.Current
import com.example.weatherappcompose.data.network.weather.model.response.base.Day
import com.example.weatherappcompose.data.network.weather.model.response.base.Forecast
import com.example.weatherappcompose.data.network.weather.model.response.base.Forecastday
import com.example.weatherappcompose.data.network.weather.model.response.base.Hour
import com.example.weatherappcompose.data.network.weather.model.response.base.Location

data class ForecastWeatherResponse(
    val alerts: Alerts,
    val current: Current,
    val forecast: Forecast,
    val location: Location
)

val testData =
    ForecastWeatherResponse(
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
            precip_mm = 0.0,
            precip_in = 0,
            humidity = 31,
            cloud = 75,
            feelslike_c = 37.0,
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
                so2 = 12.0,
                pm2_5 = 13.600000381469727,
                pm10 = 15.0,
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
                        totalprecip_mm = 0.0,
                        totalprecip_in = 0.0,
                        avgvis_km = 10.0,
                        avgvis_miles = 6.0,
                        avghumidity = 53,
                        daily_will_it_rain = 0,
                        daily_chance_of_rain = 0,
                        daily_will_it_snow = 0,
                        daily_chance_of_snow = 0,
                        uv = 8.0,
                        condition = Condition(
                            text = "Sunny",
                            icon = "//cdn.weatherapi.com/weather/64x64/day/119.png",
                            code = 1000
                        )
                    ),
                    astro = Astro(
                        sunrise = "05:44 AM",
                        sunset = "08:20 PM",
                        moonrise = "12:58 AM",
                        moonset = "03:35 PM",
                        moon_phase = "Last Quarter",
                        moon_illumination = 36.0
                    ),
                    hour = listOf(
                        Hour(
                            time_epoch = 1658462400,
                            time = "2022-07-22 01:00",
                            temp_c = 28.7,
                            temp_f = 83.7,
                            is_day = 0,
                            condition = Condition(
                                text = "Clear",
                                icon = "//cdn.weatherapi.com/weather/64x64/night/116.png",
                                code = 1000
                            ),
                            wind_mph = 9.4,
                            wind_kph = 15.1,
                            wind_degree = 265,
                            wind_dir = "W",
                            pressure_mb = 1007.0,
                            pressure_in = 29.73,
                            precip_mm = 0.0,
                            precip_in = 0.0,
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
                            vis_km = 10.0,
                            vis_miles = 6.0,
                            gust_mph = 15.0,
                            gust_kph = 24.1,
                            uv = 1.0
                        ),
                        Hour(
                            time_epoch = 1658462400,
                            time = "2022-07-22 02:00",
                            temp_c = 28.7,
                            temp_f = 83.7,
                            is_day = 0,
                            condition = Condition(
                                text = "Clear",
                                icon = "//cdn.weatherapi.com/weather/64x64/night/119.png",
                                code = 1000
                            ),
                            wind_mph = 9.4,
                            wind_kph = 15.1,
                            wind_degree = 265,
                            wind_dir = "W",
                            pressure_mb = 1007.0,
                            pressure_in = 29.73,
                            precip_mm = 0.0,
                            precip_in = 0.0,
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
                            vis_km = 10.0,
                            vis_miles = 6.0,
                            gust_mph = 15.0,
                            gust_kph = 24.1,
                            uv = 1.0
                        ),
                        Hour(
                            time_epoch = 1658462400,
                            time = "2022-07-22 03:00",
                            temp_c = 28.7,
                            temp_f = 83.7,
                            is_day = 0,
                            condition = Condition(
                                text = "Clear",
                                icon = "//cdn.weatherapi.com/weather/64x64/night/122.png",
                                code = 1000
                            ),
                            wind_mph = 9.4,
                            wind_kph = 15.1,
                            wind_degree = 265,
                            wind_dir = "W",
                            pressure_mb = 1007.0,
                            pressure_in = 29.73,
                            precip_mm = 0.0,
                            precip_in = 0.0,
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
                            vis_km = 10.0,
                            vis_miles = 6.0,
                            gust_mph = 15.0,
                            gust_kph = 24.1,
                            uv = 1.0
                        ),
                        Hour(
                            time_epoch = 1658462400,
                            time = "2022-07-22 04:00",
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
                            pressure_mb = 1007.0,
                            pressure_in = 29.73,
                            precip_mm = 0.0,
                            precip_in = 0.0,
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
                            vis_km = 10.0,
                            vis_miles = 6.0,
                            gust_mph = 15.0,
                            gust_kph = 24.1,
                            uv = 1.0
                        ),
                        Hour(
                            time_epoch = 1658462400,
                            time = "2022-07-22 05:00",
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
                            pressure_mb = 1007.0,
                            pressure_in = 29.73,
                            precip_mm = 0.0,
                            precip_in = 0.0,
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
                            vis_km = 10.0,
                            vis_miles = 6.0,
                            gust_mph = 15.0,
                            gust_kph = 24.1,
                            uv = 1.0
                        ),
                        Hour(
                            time_epoch = 1658462400,
                            time = "2022-07-22 06:00",
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
                            pressure_mb = 1007.0,
                            pressure_in = 29.73,
                            precip_mm = 0.0,
                            precip_in = 0.0,
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
                            vis_km = 10.0,
                            vis_miles = 6.0,
                            gust_mph = 15.0,
                            gust_kph = 24.1,
                            uv = 1.0
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


