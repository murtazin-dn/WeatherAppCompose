package com.example.weatherappcompose.data.network.mapper

import com.example.weatherappcompose.data.network.geocodingweather.model.GeocodingWeatherDto
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather
import com.example.weatherappcompose.domain.mapper.Mapper

class GeocodingWeatherMapper : Mapper<GeocodingWeatherDto, List<GeocodingWeather>> {
    override fun transform(entity: GeocodingWeatherDto) : List<GeocodingWeather> {
        val list = mutableListOf<GeocodingWeather>()
        entity.results?.forEach { geo ->
            list.add(
                GeocodingWeather(
                    name = geo.name,
                    lat = geo.latitude,
                    long = geo.longitude,
                    country = geo.country ?: geo.admin1 ?: geo.admin2 ?: geo.admin3 ?: geo.admin4 ?: ""
                )
            )
        }
        return list
    }
}