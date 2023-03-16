package com.example.betadiccompose.data.repository

import com.example.betadiccompose.data.local_database.dao.GameDao
import com.example.betadiccompose.data.local_database.entity.UserEntity
import com.example.betadiccompose.data.network_database.model.DataUser
import com.example.betadiccompose.data.network_database.model.toDomain
import javax.inject.Inject

class UserRepository  @Inject constructor(
    private val dao: GameDao
){
    /**  User data **/
    suspend fun InsertUser(userdata: UserEntity){
        dao.insertUser(userdata)
    }

    suspend fun DeleteUser(){
        dao.deleteAllUser()
    }

    suspend fun GetDetaillsUser(): DataUser {
        val result = dao.getDetaillsUser().toDomain()
        val obj = DataUser()
        return  result
    }

    suspend fun UpdateExpUser(exp:Int){
        dao.updateExp(exp)
    }

    suspend fun UpdateStarsUser(stars:Int){
        var value = dao.getStars() + stars
        dao.updateStars(value)
    }

    suspend fun UpdateLevelUser(level:Int){
        var value = dao.getLevel()+ level
        dao.updateLevel(value)
    }

    suspend fun getUserExp() = dao.getExp()

    suspend fun count():Int{
        return dao.countUser()
    }

    suspend fun lessLives(){
        var value = dao.getLives() - 1
        dao.updateLives(value)
    }

    suspend fun plusLives(){
        dao.updateLives(3)
    }


    suspend fun getUserLives()= dao.getLives()


}