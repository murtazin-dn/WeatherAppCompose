package com.example.weatherappcompose.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AirQualityRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeocodingRetrofit
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeocodingWeatherRetrofit