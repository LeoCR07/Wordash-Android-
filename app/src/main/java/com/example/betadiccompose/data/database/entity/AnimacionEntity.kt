package com.example.betadiccompose.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.network.model.DataWorld
import com.google.gson.JsonArray

@Entity(tableName ="word_animation")
data class AnimacionEntity(

    @PrimaryKey()
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "animacion") val animacion: String)






