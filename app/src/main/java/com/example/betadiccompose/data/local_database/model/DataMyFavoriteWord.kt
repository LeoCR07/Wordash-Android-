package com.example.betadiccompose.data.local_database.model

import com.example.betadiccompose.data.local_database.entity.FavoriteWordEntity_2

data class DataMyFavoriteWord(
    val World_1:String,
    val World_2:String,
    val Img:String,
    val sonido:String)

fun FavoriteWordEntity_2.toDomain() = DataMyFavoriteWord(World_1, World_2, Img,sonido)

