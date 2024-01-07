package com.example.weatherappcompose.ui.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}