package com.example.betadiccompose.data.local_database.model

import com.example.betadiccompose.data.local_database.entity.FavoriteGramarEntity

data class DataMyFavoriteGramar(
    val Gramar_1:String,
    val Gramar_2:String,
    val Example_1:String,
    val Example_2:String)

fun FavoriteGramarEntity.toDomain() = DataMyFavoriteGramar(Gramar_1,Gramar_2, Example_1,Example_2)