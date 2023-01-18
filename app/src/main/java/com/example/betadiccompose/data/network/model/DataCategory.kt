package com.example.betadiccompose.data.network.model

import com.google.gson.annotations.SerializedName

data class DataCategory(
    @SerializedName("id") val id:Int,
    @SerializedName("dir") val path:String,
    @SerializedName("name") val name:String,
    @SerializedName("sub") val issub:Boolean,
    @SerializedName("doc") val doc:Int,
    @SerializedName("img") val img:Int,
    @SerializedName("subcat") val subcat:Int,
    @SerializedName("stars") val stars:Int,
    @SerializedName("inicio") val inicio:Int,
    @SerializedName("fin") val fin:Int
)
