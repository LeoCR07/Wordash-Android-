package com.example.betadiccompose.data.network_database.model

import com.example.betadiccompose.data.local_database.entity.LevelEntity

data class DataNiveles(
    val id :Int,
    val name :String,
    val animation :String,
    val dir: String,
    val stars :Int,
    val doc:String
)

fun LevelEntity.toDomain() = DataNiveles(
    id = id,
    name=name,
    animation=animation,
    dir = dir,
    stars = stars,
    doc= doc)
