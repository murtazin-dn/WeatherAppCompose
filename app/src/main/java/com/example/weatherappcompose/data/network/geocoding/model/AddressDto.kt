package com.example.weatherappcompose.data.network.geocoding.model

data class AddressDto(
    val Components: List<ComponentDto>,
    val country_code: String,
    val formatted: String
)