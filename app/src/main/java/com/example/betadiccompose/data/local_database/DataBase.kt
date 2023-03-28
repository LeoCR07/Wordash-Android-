package com.example.betadiccompose.data.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.betadiccompose.data.local_database.dao.GameDao
import com.example.betadiccompose.data.local_database.entity.*

@Database(
    entities = [
        FavoriteWordEntity_2::class,
        UserEntity::class,
        WordEntity::class,
        FavoriteSentesEntity::class,
        FavoriteGramarEntity::class,
        LevelEntity::class,
        VocabularyEntity::class],
    version = 23
    )

abstract class DataBase:RoomDatabase() {
    abstract fun getQuoteDao():GameDao
}