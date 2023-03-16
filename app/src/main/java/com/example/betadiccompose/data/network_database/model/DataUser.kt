package com.example.betadiccompose.data.network_database.model

import com.example.betadiccompose.data.local_database.entity.UserEntity

data class DataUser(
    val id:Int = 0,
    val name:String = "no user",
    val email:String = "dicvobulary@",
    val exp:Int = 0,
    val level: Int = 1,
    val stars: Int = 0,
    val lives:Int = 5
    )
fun UserEntity.toDomain() = DataUser(id,name,email,exp,level,stars,lives)

