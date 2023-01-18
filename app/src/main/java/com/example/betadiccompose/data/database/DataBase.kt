package com.example.betadiccompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.betadiccompose.data.database.dao.GameDao
import com.example.betadiccompose.data.database.entity.AnimacionEntity
import com.example.betadiccompose.data.database.entity.FavoriteWordEntity
import com.example.betadiccompose.data.database.entity.GameEntity
import com.example.betadiccompose.data.database.entity.UserEntity

@Database(entities = [
    GameEntity::class , AnimacionEntity::class,FavoriteWordEntity::class,UserEntity::class], version = 4)
    abstract class DataBase:RoomDatabase() {
    abstract fun getQuoteDao():GameDao
}