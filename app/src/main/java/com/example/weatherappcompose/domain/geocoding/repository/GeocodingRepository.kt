package com.example.weatherappcompose.domain.geocoding.repository

import com.example.weatherappcompose.data.util.Response

interface GeocodingRepository {
    suspend fun getLocationName(lat: Double, long: Double): Response<String>
}