package com.example.betadiccompose.data.local_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord

@Entity(tableName ="word_favority_table")
data class FavoriteWordEntity(

    @PrimaryKey()
    @ColumnInfo(name = "World_1") val World_1:String,
    @ColumnInfo(name = "World_2") val World_2:String,
    @ColumnInfo(name = "Img") val Img:String,
    @ColumnInfo(name = "sonido") val sonido:String)

fun DataMyFavoriteWord.toDatabase() = FavoriteWordEntity( World_1, World_2, Img,sonido)