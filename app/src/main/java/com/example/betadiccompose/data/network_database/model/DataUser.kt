package com.example.betadiccompose.data.network_database.model

import com.example.betadiccompose.data.local_database.entity.UserEntity

data class DataUser(
    val id:Int = 0,
    val exp:Int = 0,
    val level: Int = 0,
    val stars: Int = 0
    )
fun UserEntity.toDomain() = DataUser(id,exp,level,stars)

