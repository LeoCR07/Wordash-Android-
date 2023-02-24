package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.local_database.entity.toDatabase
import com.example.betadiccompose.data.network_database.model.DataUser
import com.example.betadiccompose.data.repository.UserRepository
import javax.inject.Inject

class User @Inject constructor(private val repository : UserRepository)  {

    suspend fun DeleteUser(){
        repository.DeleteUser()
    }

    suspend fun GetDetaillsUser(): DataUser {
        return repository.GetDetaillsUser()
    }

    suspend fun InsertUser(resp : DataUser){
        repository.InsertUser(resp.toDatabase())
    }

    suspend fun UpdateExpUser(exp : Int){
        val value = repository.getUserExp() + exp
        repository.UpdateExpUser(value)

    }

    suspend fun UpdateLevelUser(level : Int) {
        repository.UpdateLevelUser(level)
    }

    suspend fun UpdateStarsUser(stars : Int){
        repository.UpdateStarsUser(stars)
    }

    suspend fun countUser():Int=repository.count()

}