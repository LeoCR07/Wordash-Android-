package com.example.betadiccompose.data.network_database.model
import com.example.betadiccompose.data.local_database.entity.UserEntity

data class DataUser(
    val id:Int,
    val name:String = "",
    val email:String = "",
    val exp:Int = 0,
    val level: Int = 1,
    val stars: Int = 0,
    val lives:Int = 3
    )

fun UserEntity.toDomain() = DataUser(id = id,
    name =name,
    email =  email,
    exp = exp,
    level = level,
    stars = stars,
    lives = lives)
//fun UserEntity.toDomain() = DataUser(id=id,
  //  name = name,email=email,exp = exp,level = level,stars = stars,lives=lives)

