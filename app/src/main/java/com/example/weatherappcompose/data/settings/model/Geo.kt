package com.example.weatherappcompose.data.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val long: Double,
    val lat: Double
)
