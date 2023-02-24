package com.example.betadiccompose.core

sealed class Result<T>(
    val data :T? = null,
    val messages :String ?= null
){

    class Success<T>(data:T?):Result<T>(data)
    class Error<T>(messages: String?,data: T? = null):Result<T>(data,messages)
    class Loading<T>(data:T?= null):Result<T>(data)

}
