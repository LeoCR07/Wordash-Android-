package com.example.betadiccompose.data.network.model

import com.google.gson.annotations.SerializedName

data class QuoteMode(@SerializedName("quote")  val quote:String,
                     @SerializedName("author") val author:String)
