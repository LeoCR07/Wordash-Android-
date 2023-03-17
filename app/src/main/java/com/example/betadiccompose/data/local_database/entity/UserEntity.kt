package com.example.betadiccompose.data.local_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.network_database.model.DataUser

@Entity(tableName ="user_table")
data class UserEntity (

    @PrimaryKey
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "email") val email:String,
    @ColumnInfo(name = "exp") val exp:Int,
    @ColumnInfo(name = "level") val level:Int,
    @ColumnInfo(name = "stars") val stars:Int,
    @ColumnInfo(name = "lives") val lives:Int,
)

fun DataUser.toDatabase() = UserEntity(
    id=id,
    name = name,
    email = email,
    exp = exp,
    level = level,
    stars = stars,
    lives = lives)
