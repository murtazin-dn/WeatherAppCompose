package com.example.weatherappcompose.ui.utils.ext

import com.example.weatherappcompose.ui.screens.home.model.SunRiseModel
import com.example.weatherappcompose.ui.screens.home.model.SunState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun uvToString(uv: Int) = when(uv){
    in 0..2 -> "Low"
    in 3..5 -> "Moderate"
    in 6..7 -> "High"
    in 8..10 -> "Very high"
    in 11..13 -> "Extreme"
    else -> "Error"
}

fun airQualityToString(index: Int) = when(index){
    in 1..3 -> "Low"
    in 4..6 -> "Moderate"
    in 7..9 -> "High"
    else -> "Very high"
}

fun hhToHHInt(sunRise: String, sunSet: String): SunRiseModel{
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
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

    return when{
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

fun dateTo12(date: String): String{
    val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val formatter2 = DateTimeFormatter.ofPattern("h a")
    val date1 = LocalTime.parse(date, formatter1)
    return date1.format(formatter2).dropWhile { it == '0' }
}
fun dateToDayOfWeek(date: String): String{
    val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatter2 = DateTimeFormatter.ofPattern("E")
    val date1 = LocalDate.parse(date, formatter1)
    return date1.format(formatter2)
}