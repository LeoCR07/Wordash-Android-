package com.example.betadiccompose.Domain

import com.example.betadiccompose.Domain.model.DataGramar
import com.example.betadiccompose.Domain.model.DataSentes
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class GetGramar  @Inject constructor(private val resposity: CategoryRespository){
    suspend operator fun invoke ():List<DataGramar>? = resposity.getgramar()
}