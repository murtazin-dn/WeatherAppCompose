package com.example.weatherappcompose.ui.utils.ext

import android.icu.text.SimpleDateFormat

fun String.dateToDate(): String{
    val dateString = this.substringBefore(" ")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = dateFormat.parse(dateString)
    val newDateFormat = SimpleDateFormat("EEE, MMM d")
    return newDateFormat.format(date)
}