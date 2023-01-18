package com.example.betadiccompose.Domain.UserData

import com.example.betadiccompose.data.database.entity.toDatabase
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network.model.DataUser
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class InsertDataUser @Inject constructor(private val repository : CategoryRespository) {
    suspend operator fun invoke(resp : DataUser){
        repository.InsertUser(resp.toDatabase())
    }
}