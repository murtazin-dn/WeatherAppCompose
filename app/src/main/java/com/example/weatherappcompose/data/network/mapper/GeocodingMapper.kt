package com.example.weatherappcompose.data.network.mapper

import com.example.weatherappcompose.data.network.geocoding.model.GeocodingDto
import com.example.weatherappcompose.domain.mapper.Mapper

class GeocodingMapper : Mapper<GeocodingDto, String> {
    override fun transform(entity: GeocodingDto): String {
        val geocode = entity
            .response.GeoObjectCollection.featureMember.first().GeoObject.metaDataProperty
            .GeocoderMetaData.Address.Components
        val comp =  geocode.find { it.kind == "locality" }
            ?: geocode.find { it.kind == "area" }
            ?: geocode.find { it.kind == "province" }
            ?: geocode.find { it.kind == "country" }
            ?: geocode.firstOrNull()
            ?: return "unknown"
        return comp.name
    }
}