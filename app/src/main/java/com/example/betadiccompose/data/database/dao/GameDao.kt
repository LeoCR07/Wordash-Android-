package com.example.betadiccompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.betadiccompose.data.database.entity.GameEntity

@Dao
interface GameDao {

    @Query("select * from word_table ORDER BY World_1 ASC")
    suspend fun getallQuotes():List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quetes:List<GameEntity>)

    @Query("Delete from word_table")
    suspend fun deleteAllWords()

    @Query("select * from word_table ORDER BY id ")
    suspend fun getallQuotesByID():List<GameEntity>
}