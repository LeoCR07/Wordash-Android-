package com.example.betadiccompose.data.local_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes

@Entity(tableName ="sentes_favority_table")
data class FavoriteSentesEntity(

    @PrimaryKey()
    @ColumnInfo(name = "Sentes_1") val Sentes_1:String,
    @ColumnInfo(name = "Sentes_2") val Sentes_2:String,
    @ColumnInfo(name = "sonido") val sonido:String)

fun DataMyFavoriteSentes.toDatabase() = FavoriteSentesEntity( Sentes_1, Sentes_2,sonido)