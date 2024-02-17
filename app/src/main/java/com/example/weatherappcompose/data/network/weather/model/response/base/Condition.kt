package com.example.weatherappcompose.data.network.weather.model.response.base

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)