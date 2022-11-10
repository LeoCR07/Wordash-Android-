package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class GetWords @Inject constructor( private val resposity:CategoryRespository){
    suspend operator fun invoke ():List<DataWorld>? = resposity.getWords()
}