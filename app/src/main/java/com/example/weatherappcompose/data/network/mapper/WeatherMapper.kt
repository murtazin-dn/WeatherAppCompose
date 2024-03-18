package com.example.weatherappcompose.data.network.mapper

import com.example.weatherappcompose.data.model.WeatherDto
import com.example.weatherappcompose.data.network.geocoding.model.GeocodingDto
import com.example.weatherappcompose.data.network.weather.model.air.response.AirQualityDto
import com.example.weatherappcompose.data.network.weather.model.forecast.response.DailyDto
import com.example.weatherappcompose.data.network.weather.model.forecast.response.HourlyDto
import com.example.weatherappcompose.domain.mapper.Mapper
import com.example.weatherappcompose.domain.weather.model.AirQuality
import com.example.weatherappcompose.domain.weather.model.Current
import com.example.weatherappcompose.domain.weather.model.HourlyDaily
import com.example.weatherappcompose.domain.weather.model.Precipitation
import com.example.weatherappcompose.domain.weather.model.Sun
import com.example.weatherappcompose.domain.weather.model.Weather
import com.example.weatherappcompose.domain.weather.model.Wind
import com.example.weatherappcompose.ui.utils.ext.getWeatherCodeDescription
import javax.inject.Inject

class WeatherMapper @Inject constructor(
    private val airQualityMapper: Mapper<AirQualityDto, AirQuality>,
    private val geocodingMapper: Mapper<GeocodingDto, String>,
    private val hourlyForecastMapper: Mapper<HourlyDto, List<HourlyDaily>>,
    private val dailyForecastMapper: Mapper<DailyDto, List<HourlyDaily>>
) : Mapper<WeatherDto, Weather> {
    override fun transform(entity: WeatherDto) = Weather(
        city = geocodingMapper.transform(entity.geocoding),
        current = Current(
            temp = entity.forecast.current.temperature_2m.toInt(),
            tempMin = entity.forecast.daily.temperature_2m_min.first().toInt(),
            tempMax = entity.forecast.daily.temperature_2m_max.first().toInt(),
            weatherDescription = getWeatherCodeDescription(entity.forecast.current.weather_code),
            airQuality = airQualityMapper.transform(entity.airQuality),
            uvIndex = entity.forecast.hourly.uv_index.first().toInt(),
            sun = Sun(
                sunRise = entity.forecast.daily.sunrise.first(),
                sunSet = entity.forecast.daily.sunset.first()
            ),
            wind = Wind(
                speed = entity.forecast.current.wind_speed_10m,
                direction = entity.forecast.current.wind_direction_10m,
                speedUnit = entity.forecast.current_units.wind_speed_10m
            ),
            precipitation = Precipitation(
                precipitation = entity.forecast.current.precipitation,
                precipitationSum = entity.forecast.daily.precipitation_sum.first(),
                unit = entity.forecast.current_units.precipitation
            ),
            humidity = entity.forecast.current.relative_humidity_2m,
            dewPoint = entity.forecast.hourly.dew_point_2m.first().toInt(),
            feelsLike = entity.forecast.current.apparent_temperature.toInt()
        ),
        daily = dailyForecastMapper.transform(entity.forecast.daily),
        hourly = hourlyForecastMapper.transform(entity.forecast.hourly)
    )
}