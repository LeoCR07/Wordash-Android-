package com.example.betadiccompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.betadiccompose.data.database.entity.FavoriteWordEntity
import com.example.betadiccompose.data.database.entity.GameEntity
import com.example.betadiccompose.data.database.entity.UserEntity

@Dao
interface GameDao {

     /**  Words **/
    @Query("select * from word_table ORDER BY id")
    suspend fun getallQuotes():List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quetes:List<GameEntity>)

    @Query("Delete from word_table")
    suspend fun deleteAllWords()

    @Query("select * from word_table ORDER BY id ")
    suspend fun getallQuotesByID():List<GameEntity>


    /**  My favorite word **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertmyfavoriteword(quetes:FavoriteWordEntity)

    @Query("select * from word_favority_table ORDER BY World_1")
    suspend fun GetAllMyWordFavorite():List<FavoriteWordEntity>

    @Query("Delete from word_favority_table where Img = :img")
    suspend fun deleteMyFavorityWordByImg(img :String)




    /**  User data **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserEntity)

    @Query("update user_table set exp =:Exp")
    suspend fun updateExp(Exp:Int)

    @Query("update user_table set exp =:Stars")
    suspend fun updateStars(Stars:Int)

    @Query("update user_table set exp =:level")
    suspend fun updateLevel(level:Int)

    @Query("select * from user_table")
    suspend fun getDetaillsUser():UserEntity

    @Query("Delete from user_table")
    suspend fun deleteAllUser()


    @Query("select exp from user_table")
    suspend fun getExp():Int

    @Query("select level  from user_table")
    suspend fun getLevel():Int

    @Query("select stars from USER_TABLE")
    suspend fun getStars():Int
}