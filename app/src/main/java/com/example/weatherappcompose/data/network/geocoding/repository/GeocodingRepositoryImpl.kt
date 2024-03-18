package com.example.weatherappcompose.data.network.geocoding.repository

import com.example.weatherappcompose.data.util.GEOCODING_APIKEY
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.geocoding.repository.GeocodingRepository

class GeocodingRepositoryImpl(
    private val geocodingService: GeocodingService
) : GeocodingRepository {
    override suspend fun getLocationName(lat: Double, long: Double): Response<String> {
        val geocodingOptions = hashMapOf<String, String>()
        geocodingOptions["apikey"] = GEOCODING_APIKEY
        geocodingOptions["geocode"] = "$lat, $long"
        geocodingOptions["format"] = "json"
        geocodingOptions["apikey"] = "1"


        val geocodingResponse = geocodingService.getGeocoding(geocodingOptions)
        return if (geocodingResponse.isSuccessful){
            val body = geocodingResponse.body()!!
            Response.Success(
                body.response.GeoObjectCollection.featureMember
                    .first().GeoObject.metaDataProperty.GeocoderMetaData
                    .AddressDetails.Country.AdministrativeArea.Locality.LocalityName)
        }else{
            Response.Error(geocodingResponse.errorBody()?.charStream()?.readText()!!)
        }
    }
}