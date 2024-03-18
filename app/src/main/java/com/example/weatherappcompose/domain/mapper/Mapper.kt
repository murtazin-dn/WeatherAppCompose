package com.example.weatherappcompose.domain.mapper

interface Mapper<Entity, Model> {
    fun transform(entity: Entity): Model
}