package com.example.betadiccompose.data.local_database.model

import com.example.betadiccompose.data.local_database.entity.FavoriteSentesEntity

data class DataMyFavoriteSentes(
    val Sentes_1:String,
    val Sentes_2:String,
    val sonido:String)

fun FavoriteSentesEntity.toDomain() = DataMyFavoriteSentes(Sentes_1,Sentes_2, sonido)