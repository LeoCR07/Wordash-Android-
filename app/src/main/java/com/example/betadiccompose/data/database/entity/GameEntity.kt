package com.example.betadiccompose.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.network.model.DataWorld

@Entity(tableName ="word_table")
data class GameEntity(

    @PrimaryKey()
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "World_1") val World_1:String,
    @ColumnInfo(name = "World_2") val World_2:String,
    @ColumnInfo(name = "Img") val Img:String)

fun DataWorld.toDatabase() = GameEntity(id, World_1, World_2, Img)