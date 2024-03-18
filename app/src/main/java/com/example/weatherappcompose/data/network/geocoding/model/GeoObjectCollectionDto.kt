package com.example.weatherappcompose.data.network.geocoding.model

data class GeoObjectCollectionDto(
    val featureMember: List<FeatureMemberDto>,
    val metaDataProperty: MetaDataPropertyXDto
)