package com.example.betadiccompose.Domain.UserData

import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UpdateLevelUser  @Inject constructor(private val repository : CategoryRespository) {
    suspend operator fun invoke(level : Int) {
        repository.UpdateLevelUser(level)
    }

}