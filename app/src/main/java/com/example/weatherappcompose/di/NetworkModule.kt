package com.example.weatherappcompose.di

import com.example.weatherappcompose.data.network.geocoding.repository.GeocodingService
import com.example.weatherappcompose.data.network.geocodingweather.repository.GeocodingWeatherService
import com.example.weatherappcompose.data.network.weather.repository.AirQualityService
import com.example.weatherappcompose.data.network.weather.repository.WeatherService
import com.example.weatherappcompose.data.util.AIR_QUALITY_URL
import com.example.weatherappcompose.data.util.BASE_URL
import com.example.weatherappcompose.data.util.GEOCODING_URL
import com.example.weatherappcompose.data.util.GEOCODING_WEATHER_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @BaseUrlRetrofit
    @Provides
    fun provideBaseRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @AirQualityRetrofit
    @Provides
    fun provideAirQualityRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(AIR_QUALITY_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @GeocodingRetrofit
    @Provides
    fun provideGeocodingRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(GEOCODING_URL)
        .client(okHttpClient)
        .build()
    @Singleton
    @GeocodingWeatherRetrofit
    @Provides
    fun provideGeocodingWeatherRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(GEOCODING_WEATHER_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideWeatherService(@BaseUrlRetrofit retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Singleton
    @Provides
    fun provideAirQualityService(@AirQualityRetrofit retrofit: Retrofit): AirQualityService =
        retrofit.create(AirQualityService::class.java)

    @Singleton
    @Provides
    fun provideGeocodingService(@GeocodingRetrofit retrofit: Retrofit): GeocodingService =
        retrofit.create(GeocodingService::class.java)
    @Singleton
    @Provides
    fun provideGeocodingWeatherService(@GeocodingWeatherRetrofit retrofit: Retrofit): GeocodingWeatherService =
        retrofit.create(GeocodingWeatherService::class.java)



}