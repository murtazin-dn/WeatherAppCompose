package com.example.weatherappcompose.data.network.weather.repository

import com.example.weatherappcompose.data.model.WeatherDto
import com.example.weatherappcompose.data.network.geocoding.repository.GeocodingService
import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.data.util.GEOCODING_APIKEY
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.mapper.Mapper
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.domain.weather.model.Weather
import com.example.weatherappcompose.domain.weather.repository.WeatherRepository
import com.example.weatherappcompose.ui.screens.settings.model.PrecipitationUnit.INCH
import com.example.weatherappcompose.ui.screens.settings.model.PrecipitationUnit.MILLIMETER
import com.example.weatherappcompose.ui.screens.settings.model.TemperatureUnit.C
import com.example.weatherappcompose.ui.screens.settings.model.TemperatureUnit.F
import com.example.weatherappcompose.ui.screens.settings.model.WindSpeedUnit.KMH
import com.example.weatherappcompose.ui.screens.settings.model.WindSpeedUnit.KNOTS
import com.example.weatherappcompose.ui.screens.settings.model.WindSpeedUnit.MPH
import com.example.weatherappcompose.ui.screens.settings.model.WindSpeedUnit.MS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val airQualityService: AirQualityService,
    private val geocodingService: GeocodingService,
    private val settingsRepository: SettingsRepository,
    private val weatherMapper: Mapper<WeatherDto, Weather>
): WeatherRepository {

    override suspend fun getWeather(geo: Geo): Response<Weather> =
        withContext(Dispatchers.IO) {
            val locale = when(Locale.getDefault().language){
                "ru" -> "ru_RU"
                else -> "en_US"
            }
            val settings = settingsRepository.getSettings()
            val temperatureUnit = when(settings.temperatureUnit){
                C -> null
                F -> "fahrenheit"
            }
            val precipitationUnit = when(settings.precipitationUnit){
                MILLIMETER -> null
                INCH -> "inch"
            }
            val windSpeedUnit = when(settings.windSpeedUnit){
                KMH -> null
                MPH -> "mph"
                MS -> "ms"
                KNOTS -> "kn"
            }
            val weatherOptions = hashMapOf<String, String>()
            weatherOptions["latitude"] = geo.lat.toString()
            weatherOptions["longitude"] = geo.long.toString()
            weatherOptions["current"] = WEATHER_CURRENT_QUERY
            weatherOptions["hourly"] = WEATHER_HOURLY_QUERY
            weatherOptions["daily"] = WEATHER_DAILY_QUERY
            weatherOptions["forecast_hours"] = WEATHER_FORECAST_HOURS_QUERY
            weatherOptions["timezone"] = WEATHER_TIMEZONE_AUTO

            if(temperatureUnit != null) weatherOptions["temperature_unit"] = temperatureUnit
            if(precipitationUnit != null) weatherOptions["precipitation_unit"] = precipitationUnit
            if(windSpeedUnit != null) weatherOptions["wind_speed_unit"] = windSpeedUnit


            val airOptions = hashMapOf<String, String>()
            airOptions["latitude"] = geo.lat.toString()
            airOptions["longitude"] = geo.long.toString()
            airOptions["current"] = AIR_CURRENT_QUERY
            airOptions["timezone"] = AIR_TIMEZONE_QUERY
            airOptions["forecast_days"] = AIR_FORECAST_DAYS_QUERY

            val geocodingOptions = hashMapOf<String, String>()
            geocodingOptions["apikey"] = GEOCODING_APIKEY
            geocodingOptions["geocode"] = "${geo.long}, ${geo.lat}"
            geocodingOptions["format"] = "json"
            geocodingOptions["results"] = "1"
            geocodingOptions["lang"] = locale

            val forecastDeferred = async{
                weatherService.getForecastWeather(weatherOptions)
            }
            val airDeferred = async{
                airQualityService.getAirQuality(airOptions)
            }
            val geocodingDeferred = async{
                geocodingService.getGeocoding(geocodingOptions)
            }
            val airData = airDeferred.await().let { airResponse ->
                if (airResponse.isSuccessful){
                    return@let airResponse.body()!!

                }else{
                    return@withContext Response.Error(airResponse.errorBody()?.charStream()?.readText()!!)
                }
            }
            val geocodingData = geocodingDeferred.await().let { geocodingResponse ->
                if (geocodingResponse.isSuccessful){
                    return@let geocodingResponse.body()!!
                }else{
                    return@withContext Response.Error(geocodingResponse.errorBody()?.charStream()?.readText()!!)
                }
            }
            val weatherData = forecastDeferred.await().let { weatherResponse ->
                if (weatherResponse.isSuccessful){
                    return@let weatherResponse.body()!!
                }else{
                    return@withContext Response.Error(weatherResponse.errorBody()?.charStream()?.readText()!!)
                }
            }



            return@withContext Response.Success(
                weatherMapper.transform(
                    WeatherDto(
                        airQuality = airData,
                        geocoding = geocodingData,
                        forecast = weatherData
                    )
                )
            )
    }


    companion object{
        private const val WEATHER_CURRENT_QUERY = "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,surface_pressure,wind_speed_10m,wind_direction_10m"
        private const val WEATHER_HOURLY_QUERY = "temperature_2m,dew_point_2m,weather_code,is_day,uv_index"
        private const val WEATHER_DAILY_QUERY = "weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_sum"
        private const val WEATHER_FORECAST_HOURS_QUERY = "24"
        private const val WEATHER_TIMEZONE_AUTO = "auto"

        private const val AIR_CURRENT_QUERY = "european_aqi,us_aqi,pm10,pm2_5,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone"
        private const val AIR_TIMEZONE_QUERY = "auto"
        private const val AIR_FORECAST_DAYS_QUERY = "1"

    }
}