package com.example.weatherappcompose.di

import android.content.Context
import com.example.weatherappcompose.data.model.WeatherDto
import com.example.weatherappcompose.data.network.geocoding.repository.GeocodingRepositoryImpl
import com.example.weatherappcompose.data.network.geocoding.repository.GeocodingService
import com.example.weatherappcompose.data.network.geocodingweather.model.GeocodingWeatherDto
import com.example.weatherappcompose.data.network.geocodingweather.repository.GeocodingWeatherRepositoryImpl
import com.example.weatherappcompose.data.network.geocodingweather.repository.GeocodingWeatherService
import com.example.weatherappcompose.data.network.weather.repository.AirQualityService
import com.example.weatherappcompose.data.network.weather.repository.WeatherRepositoryImpl
import com.example.weatherappcompose.data.network.weather.repository.WeatherService
import com.example.weatherappcompose.data.settings.SettingsRepositoryImpl
import com.example.weatherappcompose.domain.geocoding.repository.GeocodingRepository
import com.example.weatherappcompose.domain.geocodingweather.GeocodingWeatherRepository
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather
import com.example.weatherappcompose.domain.mapper.Mapper
import com.example.weatherappcompose.domain.settings.SettingsRepository
import com.example.weatherappcompose.domain.weather.model.Weather
import com.example.weatherappcompose.domain.weather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesWeatherRepository(
        weatherService: WeatherService,
        airQualityService: AirQualityService,
        geocodingService: GeocodingService,
        settingsRepository: SettingsRepository,
        weatherMapper: Mapper<WeatherDto, Weather>
    ): WeatherRepository = WeatherRepositoryImpl(
        weatherService,
        airQualityService,
        geocodingService,
        settingsRepository,
        weatherMapper
    )
    @Singleton
    @Provides
    fun providesGeocodingWeatherRepository(
        geocodingWeatherService: GeocodingWeatherService,
        geocodingWeatherMapper: Mapper<GeocodingWeatherDto, List<GeocodingWeather>>
    ): GeocodingWeatherRepository = GeocodingWeatherRepositoryImpl(
        geocodingWeatherService,
        geocodingWeatherMapper
    )

    @Singleton
    @Provides
    fun providesSettingsRepository(@ApplicationContext context: Context): SettingsRepository =
        SettingsRepositoryImpl(context)

    @Singleton
    @Provides
    fun providesGeocodingRepository(geocodingService: GeocodingService): GeocodingRepository =
        GeocodingRepositoryImpl(geocodingService)



}