package com.example.weatherappcompose.data.network.geocoding.model

data class GeoObjectDto(
    val Point: PointDto,
    val boundedBy: BoundedByDto,
    val description: String,
    val metaDataProperty: MetaDataPropertyDto,
    val name: String,
    val uri: String
)