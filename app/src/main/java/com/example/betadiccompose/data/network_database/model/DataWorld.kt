package com.example.betadiccompose.data.network_database.model

import com.example.betadiccompose.data.local_database.entity.WordEntity

data class DataWorld(
    val id:Int,
    val subcat:Int = 0,
    val World_1:String,
    val World_2:String,
    val Img:String)

fun WordEntity.toDomain() = DataWorld(id, subcat, World_1, World_2, Img)