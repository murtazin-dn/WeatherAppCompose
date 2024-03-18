package com.example.weatherappcompose.data.network.geocoding.model

data class CountryDto(
    val AddressLine: String,
    val AdministrativeArea: AdministrativeAreaDto,
    val CountryName: String,
    val CountryNameCode: String
)