package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.network_database.model.DataBooks
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.data.repository.BooksRepository
import com.example.betadiccompose.data.repository.VocabularyRepository
import javax.inject.Inject

class Books @Inject constructor(private val resposity: BooksRepository){

    suspend fun GetListOfBooks ():List<DataBooks>? = resposity.GetListOfAllBooks()
}