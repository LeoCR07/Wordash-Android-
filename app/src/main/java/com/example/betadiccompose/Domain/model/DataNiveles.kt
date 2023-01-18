package com.example.betadiccompose.Domain.model

data class DataNiveles(
    val id :Int,
    val name :String,
    val animation :String,
    val dir: String,
    val stars :Int,
    val inicio:Int = 1000,
    val fin:Int = 1000
)
