package com.example.betadiccompose.data.local_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.network_database.model.DataUser

@Entity(tableName ="user_table")
data class UserEntity (

    @PrimaryKey
    @ColumnInfo(name = "id") val id:String,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "email") val email:String,
    @ColumnInfo(name = "exp") val exp:Int,
    @ColumnInfo(name = "stars") val crowns:Int,
    @ColumnInfo(name = "lives") val lives:Int ,
    @ColumnInfo(name = "spanish") val Spanish:Int = 1,
    @ColumnInfo(name = "english") val English:Int = 1,
    @ColumnInfo(name = "level") val level:Int = 1,

)

fun DataUser.toDatabase() = UserEntity(
    id=id,
    name = name,
    email = email,
    exp = exp,
    crowns = crowns,
    lives = lives,
    Spanish = Spanish,
    English = English,
    level = level)
