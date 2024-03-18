package com.example.weatherappcompose.di

import com.example.weatherappcompose.data.model.WeatherDto
import com.example.weatherappcompose.data.network.geocoding.model.GeocodingDto
import com.example.weatherappcompose.data.network.geocodingweather.model.GeocodingWeatherDto
import com.example.weatherappcompose.data.network.mapper.AirQualityMapper
import com.example.weatherappcompose.data.network.mapper.DailyForecastMapper
import com.example.weatherappcompose.data.network.mapper.GeocodingMapper
import com.example.weatherappcompose.data.network.mapper.GeocodingWeatherMapper
import com.example.weatherappcompose.data.network.mapper.HourlyForecastMapper
import com.example.weatherappcompose.data.network.mapper.WeatherMapper
import com.example.weatherappcompose.data.network.weather.model.air.response.AirQualityDto
import com.example.weatherappcompose.data.network.weather.model.forecast.response.DailyDto
import com.example.weatherappcompose.data.network.weather.model.forecast.response.HourlyDto
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather
import com.example.weatherappcompose.domain.mapper.Mapper
import com.example.weatherappcompose.domain.weather.model.AirQuality
import com.example.weatherappcompose.domain.weather.model.HourlyDaily
import com.example.weatherappcompose.domain.weather.model.Weather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideAirQualityMapper(): Mapper<AirQualityDto, AirQuality> {
        return AirQualityMapper()
    }
    @Provides
    @Singleton
    fun provideGeocodingMapper(): Mapper<GeocodingDto, String> {
        return GeocodingMapper()
    }
    @Provides
    @Singleton
    fun provideGeocodingWeatherMapper(): Mapper<GeocodingWeatherDto, List<GeocodingWeather>> {
        return GeocodingWeatherMapper()
    }
    @Provides
    @Singleton
    fun provideHourlyForecastMapper(): Mapper<HourlyDto, List<HourlyDaily>> {
        return HourlyForecastMapper()
    }
    @Provides
    @Singleton
    fun provideDailyForecastMapper(): Mapper<DailyDto, List<HourlyDaily>> {
        return DailyForecastMapper()
    }
    @Provides
    @Singleton
    fun provideWeatherMapper(
        airQualityMapper: Mapper<AirQualityDto, AirQuality>,
        geocodingMapper: Mapper<GeocodingDto, String>,
        hourlyForecastMapper: Mapper<HourlyDto, List<HourlyDaily>>,
        dailyForecastMapper: Mapper<DailyDto, List<HourlyDaily>>
    ): Mapper<WeatherDto, Weather> {
        return WeatherMapper(
            airQualityMapper,
            geocodingMapper,
            hourlyForecastMapper,
            dailyForecastMapper
        )
    }

}