package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class CleanWord @Inject constructor(private val resposity: CategoryRespository){

    suspend operator fun invoke (){
        resposity.clearWords()
    }

}