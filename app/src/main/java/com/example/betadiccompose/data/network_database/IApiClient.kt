package com.example.betadiccompose.data.network_database

import com.example.betadiccompose.data.network_database.model.DataCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IApiClient {

    @GET()
    suspend fun GetDataFromAws(@Url url: String): Response<List<DataCategory>>

}