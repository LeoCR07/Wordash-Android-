package com.example.betadiccompose.data.network.model

import com.example.betadiccompose.data.database.entity.GameEntity

data class DataWorld(
    val id:Int,
    val World_1:String,
    val World_2:String,
    val Img:String)

fun GameEntity.toDomain() = DataWorld(id, World_1, World_2, Img)