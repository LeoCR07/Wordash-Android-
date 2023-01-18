package com.example.betadiccompose.Domain.UserData

import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class UpdateStarsUser @Inject constructor(private val repository : CategoryRespository) {
    suspend operator fun invoke(stars : Int){
        repository.UpdateStarsUser(stars)
    }
}
