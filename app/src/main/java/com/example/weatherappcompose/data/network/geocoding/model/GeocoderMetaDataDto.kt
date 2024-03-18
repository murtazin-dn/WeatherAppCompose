package com.example.weatherappcompose.data.network.geocoding.model

data class GeocoderMetaDataDto(
    val Address: AddressDto,
    val AddressDetails: AddressDetailsDto,
    val kind: String,
    val precision: String,
    val text: String
)