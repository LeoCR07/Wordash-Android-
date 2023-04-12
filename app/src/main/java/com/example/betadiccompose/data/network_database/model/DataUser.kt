package com.example.betadiccompose.data.network_database.model
import com.example.betadiccompose.data.local_database.entity.UserEntity

data class DataUser(
    val id:String = "",
    val name:String = "No User",
    val email:String = "",
    val exp:Int = 0,
    val Spanish:Int = 1,
    val English:Int = 1,
    val level:Int = 1,
    val crowns: Int = 0,
    val lives:Int = 3
    )

fun UserEntity.toDomain() = DataUser(
    id = id,
    name =name,
    email =  email,
    exp = exp,
    crowns = crowns,
    lives = lives,
    level = level,
    English = English,
    Spanish = Spanish)


