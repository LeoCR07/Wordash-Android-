package com.example.betadiccompose.data.network.model

import com.example.betadiccompose.data.database.entity.GameEntity
import com.example.betadiccompose.data.database.entity.UserEntity

data class DataUser(
    val id:Int = 0,
    val exp:Int = 0,
    val level: Int = 0,
    val stars: Int = 0
    )


fun UserEntity.toDomain() = DataUser(id,exp,level,stars)
//fun UserEntity.toDomain() = DataUser(id,exp, level, stars)
