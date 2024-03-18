package com.example.weatherappcompose.data.network.mapper

import com.example.weatherappcompose.data.network.weather.model.air.response.AirQualityDto
import com.example.weatherappcompose.domain.mapper.Mapper
import com.example.weatherappcompose.domain.weather.model.AirQuality

class AirQualityMapper : Mapper<AirQualityDto, AirQuality> {
    override fun transform(entity: AirQualityDto) = AirQuality(
        aqi = entity.current.us_aqi,
        pm2_5 = entity.current.pm2_5,
        pm10 = entity.current.pm10,
        so2 = entity.current.sulphur_dioxide,
        no2 = entity.current.nitrogen_dioxide,
        co = entity.current.carbon_monoxide,
        o3 = entity.current.ozone
    )
}