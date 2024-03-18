package com.example.weatherappcompose.data.network.mapper

import com.example.weatherappcompose.data.network.weather.model.forecast.response.DailyDto
import com.example.weatherappcompose.domain.mapper.Mapper
import com.example.weatherappcompose.domain.weather.model.HourlyDaily
import com.example.weatherappcompose.ui.utils.ext.dateToDayOfWeek
import com.example.weatherappcompose.ui.utils.ext.getIconFromCode

class DailyForecastMapper : Mapper<DailyDto, List<HourlyDaily>> {
    override fun transform(entity: DailyDto) : List<HourlyDaily> {
        val list = mutableListOf<HourlyDaily>()
        entity.time.forEachIndexed { index, s ->
            list.add(
                HourlyDaily(
                    time = dateToDayOfWeek(entity.time[index]),
                    icon = getIconFromCode(entity.weather_code[index], 1),
                    temp = ((entity.temperature_2m_max[index] + entity.temperature_2m_min[index])/2).toInt()
                )
            )
        }
        return list
    }
}