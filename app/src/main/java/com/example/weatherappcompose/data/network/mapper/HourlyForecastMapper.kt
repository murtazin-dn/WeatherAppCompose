package com.example.weatherappcompose.data.network.mapper

import com.example.weatherappcompose.data.network.weather.model.forecast.response.HourlyDto
import com.example.weatherappcompose.domain.mapper.Mapper
import com.example.weatherappcompose.domain.weather.model.HourlyDaily
import com.example.weatherappcompose.ui.utils.ext.dateTo12
import com.example.weatherappcompose.ui.utils.ext.getIconFromCode

class HourlyForecastMapper : Mapper<HourlyDto, List<HourlyDaily>> {
    override fun transform(entity: HourlyDto) : List<HourlyDaily> {
        val list = mutableListOf<HourlyDaily>()
        entity.time.forEachIndexed { index, s ->
            list.add(
                HourlyDaily(
                    time = dateTo12(entity.time[index]),
                    icon = getIconFromCode(entity.weather_code[index], entity.is_day[index]),
                    temp = entity.temperature_2m[index].toInt()
                )
            )
        }
        return list
    }
}