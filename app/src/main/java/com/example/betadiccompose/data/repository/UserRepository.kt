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
        return  result
    }

    suspend fun UpdateExpUser(exp:Int){
        dao.updateExp(exp)
    }

    suspend fun UpdateUserData(user: DataUser){
        dao.UpdateuserData(
            id = user.id,
            name = user.name,
            email = user.email,
            exp = user.exp,
            spanish = user.Spanish,
            english = user.English,
            crowns = user.crowns,

            )
    }

    suspend fun UpdateStarsUser(stars:Int){
        var value = dao.getStars() + stars
        dao.updateStars(value)
    }

    suspend fun UpdateLevelEnglish(level:Int){
          dao.updateenglish(level)
    }

    suspend fun UpdateLevelSpanish(level:Int){
          dao.updatespanish(level)
    }

    suspend fun UpdateLevelUser(level:Int){
        dao.updatelevel(level)
    }

    suspend fun SetLevelUser(level:Int){
        dao.updatelevel(level)
    }

    suspend fun getUserLevel() = dao.getExp()

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