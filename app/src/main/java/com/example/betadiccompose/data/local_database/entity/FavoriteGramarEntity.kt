package com.example.betadiccompose.data.local_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar

@Entity(tableName ="Gramar_favority_table")
data class FavoriteGramarEntity(

    @PrimaryKey()
    @ColumnInfo(name = "Gramar_1") val Gramar_1:String,
    @ColumnInfo(name = "Gramar_2") val Gramar_2:String,
    @ColumnInfo(name = "Example_1") val Example_1:String,
    @ColumnInfo(name = "Example_2") val Example_2:String)

fun DataMyFavoriteGramar.toDatabase() = FavoriteGramarEntity(Gramar_1, Gramar_2, Example_1, Example_2)