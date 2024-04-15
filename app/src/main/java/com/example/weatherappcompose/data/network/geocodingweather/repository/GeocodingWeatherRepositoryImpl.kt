package com.example.weatherappcompose.data.network.geocodingweather.repository

import com.example.weatherappcompose.data.network.geocodingweather.model.GeocodingWeatherDto
import com.example.weatherappcompose.data.util.Response
import com.example.weatherappcompose.domain.geocodingweather.GeocodingWeatherRepository
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather
import com.example.weatherappcompose.domain.mapper.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class GeocodingWeatherRepositoryImpl @Inject constructor(
    private val geocodingWeatherService: GeocodingWeatherService,
    private val mapper: Mapper<GeocodingWeatherDto, List<GeocodingWeather>>
) : GeocodingWeatherRepository {
    override suspend fun getGeocoding(name: String): Response<List<GeocodingWeather>> = withContext(Dispatchers.IO){
        val locale = when(Locale.getDefault().language){
            "ru" -> "ru"
            else -> "en"
        }
        val geocodingOptions = hashMapOf<String, String>()
        geocodingOptions["name"] = name
        geocodingOptions["count"] = "50"
        geocodingOptions["format"] = "json"
        geocodingOptions["language"] = "en"
        geocodingOptions["language"] = locale

        val geocodingResponse = geocodingWeatherService.getGeocoding(geocodingOptions)
        return@withContext if (geocodingResponse.isSuccessful){
            val body = geocodingResponse.body()!!
            Response.Success(
                mapper.transform(body)
            )
        }else{
            Response.Error(geocodingResponse.errorBody()?.charStream()?.readText()!!)
        }
    }

}