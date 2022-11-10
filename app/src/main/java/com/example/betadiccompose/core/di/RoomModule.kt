package com.example.betadiccompose.core.di

import android.content.Context
import androidx.room.Room
import com.example.betadiccompose.data.database.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATA_BASE = "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)=
        Room.databaseBuilder(context, DataBase::class.java,QUOTE_DATA_BASE).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db:DataBase) = db.getQuoteDao()
}