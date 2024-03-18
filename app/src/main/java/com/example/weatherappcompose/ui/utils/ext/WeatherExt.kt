package com.example.weatherappcompose.ui.utils.ext

import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.screens.home.model.SunRiseModel
import com.example.weatherappcompose.ui.screens.home.model.SunState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun uvToString(uv: Int) = when (uv) {
    in 0..2 -> "Low"
    in 3..5 -> "Moderate"
    in 6..7 -> "High"
    in 8..10 -> "Very high"
    in 11..13 -> "Extreme"
    else -> "Error"
}

fun airQualityToString(index: Int) = when (index) {
    in 1..50 -> "Good"
    in 51..100 -> "Medium"
    in 101..150 -> "Slightly harmful"
    in 151..200 -> "Harmful"
    in 201..300 -> "Very harmful"
    else -> "Extremely"
}

fun getWeatherCodeDescription(code: Int) = when (code) {
    0 -> R.string.clear
    1 -> R.string.mostly_clear
    2 -> R.string.partly_cloudy
    3 -> R.string.cloudy
    45 -> R.string.fog
    48 -> R.string.freezing_fog
    51 -> R.string.light_drizzle
    53 -> R.string.drizzle
    55 -> R.string.heavy_drizzle
    56 -> R.string.light_freezing_drizzle
    57 -> R.string.freezing_drizzle
    61 -> R.string.light_rain
    63 -> R.string.rain
    65 -> R.string.heavy_rain
    66 -> R.string.light_freezing_rain
    67 -> R.string.freezing_rain
    71 -> R.string.light_snow
    73 -> R.string.snow
    75 -> R.string.heavy_snow
    77 -> R.string.snow_grains
    80 -> R.string.light_rain_shower
    81 -> R.string.rain_shower
    82 -> R.string.heavy_rain_shower
    85 -> R.string.snow_shower
    86 -> R.string.heavy_snow_shower
    95 -> R.string.thunderstorm
    96 -> R.string.hailstorm
    99 -> R.string.heavy_hailstorm
    else -> R.string.unknown_code
}

fun dateTimeToTime(date: String): String{
    val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val formatter2 = DateTimeFormatter.ofPattern("h:mm a")
    val dt = LocalDateTime.parse(date, formatter1)
    return dt.format(formatter2)
}

fun hhToHHInt(sunRise: String, sunSet: String): SunRiseModel {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val sunRiseTime = LocalTime.parse(sunRise, formatter)
    val sunSetTime = LocalTime.parse(sunSet, formatter)
    val currentTime = LocalTime.now()

    val sunRiseMinute = (sunRiseTime.hour * 60) + sunRiseTime.minute
    val sunSetMinute = (sunSetTime.hour * 60) + sunSetTime.minute
    val currentMinute = (currentTime.hour * 60) + currentTime.minute

    val maxMinute = (LocalTime.MAX.hour * 60) + LocalTime.MAX.minute

    println(sunRiseMinute)
    println(sunSetMinute)
    println(currentMinute)
    println(maxMinute)

    return when {
        currentMinute < sunRiseMinute -> SunRiseModel(
            sunState = SunState.BEFORE_SUN_RISE,
            sunScale = currentMinute.toFloat() / sunRiseMinute
        )

        currentMinute > sunSetMinute -> SunRiseModel(
            sunState = SunState.AFTER_SUN_SET,
            sunScale = (currentMinute - sunSetMinute).toFloat() / (maxMinute - sunSetMinute)
        )

        else -> SunRiseModel(
            sunState = SunState.AFTER_SUN_RISE,
            sunScale = (currentMinute - sunRiseMinute).toFloat() / (sunSetMinute - sunRiseMinute)
        )
    }
}

fun dateTo12(date: String): String {
    val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val formatter2 = DateTimeFormatter.ofPattern("h a")
    val date1 = LocalTime.parse(date, formatter1)
    return date1.format(formatter2).dropWhile { it == '0' }
}

fun dateToDayOfWeek(date: String): String {
    val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatter2 = DateTimeFormatter.ofPattern("E")
    val date1 = LocalDate.parse(date, formatter1)
    return date1.format(formatter2)
}