package com.example.betadiccompose.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.network.model.DataUser
import com.example.betadiccompose.data.network.model.DataWorld

@Entity(tableName ="user_table")
class UserEntity (

    @PrimaryKey
    @ColumnInfo(name = "id") val id:Int,

    @ColumnInfo(name = "exp") val exp:Int,
    @ColumnInfo(name = "level") val level:Int,
    @ColumnInfo(name = "stars") val stars:Int,
)

fun DataUser.toDatabase() = UserEntity(id,exp,level, stars)