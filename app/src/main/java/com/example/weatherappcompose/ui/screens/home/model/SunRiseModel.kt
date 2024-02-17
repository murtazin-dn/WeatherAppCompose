package com.example.weatherappcompose.ui.screens.home.model

data class SunRiseModel(
    val sunState: SunState,
    val sunScale: Float
)
enum class SunState{
    BEFORE_SUN_RISE, AFTER_SUN_RISE, AFTER_SUN_SET
}
