package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.database.entity.toDatabase
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class DeleteMyFavoriteWord @Inject constructor(private val repository : CategoryRespository) {
    suspend operator fun invoke(img: String){
        repository.DeleteMyWordByImg(img)
    }
}