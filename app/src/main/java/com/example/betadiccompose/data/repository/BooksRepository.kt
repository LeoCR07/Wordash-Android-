package com.example.betadiccompose.data.repository

import com.example.betadiccompose.Domain.Game_Provider.Prefs
import com.example.betadiccompose.data.local_database.dao.GameDao
import com.example.betadiccompose.data.network_database.model.DataBooks
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import javax.inject.Inject

class BooksRepository  @Inject constructor(
    private val prefs: Prefs,
    private val api : ApiService,
    private val dao: GameDao
) {

    /**************  From Api ****************/
    //List of all Books
    suspend fun GetListOfAllBooks():List<DataBooks>{
        val response = api.getbooks()

        val result:List<DataBooks> = response.mapIndexed { index, it ->


          DataBooks(
             // Id = index,
              name = it.name,
             time = it.time,
              stars = it.stars,
              description = it.description,
              img = "https://duq14sjq9c7gs.cloudfront.net/Images/books/${it.image}.jpg",
              cats = it.cat
          )

        }

        return  result
    }
}