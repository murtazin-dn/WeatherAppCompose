package com.example.weatherappcompose.di

import android.app.Application
import com.example.weatherappcompose.data.location.repository.DefaultLocationTracker
import com.example.weatherappcompose.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Singleton
    @Provides
    fun providesLocationTracker(
        locationProvider: FusedLocationProviderClient,
        app: Application
    ): LocationTracker =
        DefaultLocationTracker(locationProvider, app)
}