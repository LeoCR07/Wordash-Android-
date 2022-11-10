package com.example.betadiccompose.data.network

import com.example.betadiccompose.data.network.model.DataCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IApiClient {

    //@GET("vocabulario.json")
    //suspend fun getAllCategories():Response<List<DataCategory>>

    @GET()
    suspend fun getAllCategories(@Url url:String):Response<List<DataCategory>>

    @GET()
    suspend fun getAllWords(@Url url:String):Response<List<DataCategory>>

    @GET()
    suspend fun getsomethings(@Url url:String):Response<List<DataCategory>>


    @GET()
    suspend fun getanimation(@Url url:String):Response<String>


}