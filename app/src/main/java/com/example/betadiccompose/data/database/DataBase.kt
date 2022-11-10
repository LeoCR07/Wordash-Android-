package com.example.betadiccompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.betadiccompose.data.database.dao.GameDao
import com.example.betadiccompose.data.database.entity.AnimacionEntity
import com.example.betadiccompose.data.database.entity.GameEntity

@Database(entities = [GameEntity::class , AnimacionEntity::class], version = 1)
abstract class DataBase:RoomDatabase() {
    abstract fun getQuoteDao():GameDao
}