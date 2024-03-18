package com.example.weatherappcompose.data.network.geocoding.model

data class GeocoderResponseMetaDataDto(
    val Point: PointDto,
    val found: String,
    val request: String,
    val results: String
)