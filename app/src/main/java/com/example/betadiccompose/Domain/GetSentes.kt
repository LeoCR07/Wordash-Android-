package com.example.betadiccompose.Domain

import com.example.betadiccompose.Domain.model.DataSentes
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class GetSentes  @Inject constructor(private val resposity: CategoryRespository){
    suspend operator fun invoke ():List<DataSentes>? = resposity.getsentes()
}