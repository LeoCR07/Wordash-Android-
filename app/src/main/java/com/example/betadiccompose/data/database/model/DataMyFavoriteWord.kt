package com.example.betadiccompose.data.database.model

import com.example.betadiccompose.data.database.entity.FavoriteWordEntity
import com.example.betadiccompose.data.database.entity.GameEntity
import com.example.betadiccompose.data.network.model.DataWorld

data class DataMyFavoriteWord(
    val World_1:String,
    val World_2:String,
    val Img:String,
    val sonido:String)

fun FavoriteWordEntity.toDomain() = DataMyFavoriteWord(World_1, World_2, Img,sonido)

