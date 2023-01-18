package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.database.entity.toDatabase
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class GetMyFavoriteWord  @Inject constructor(private val repository : CategoryRespository) {

    suspend operator fun invoke():List<DataMyFavoriteWord>{
        val resp = repository.GetAllMyFavoriteWord()
        return resp
    }

}