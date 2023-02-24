package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.local_database.entity.toDatabase
import com.example.betadiccompose.data.network_database.model.DataNiveles
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.data.repository.PlayRepository
import com.example.betadiccompose.data.repository.VocabularyRepository
import javax.inject.Inject

class Level @Inject constructor(private val repository : PlayRepository,val repository_2 :VocabularyRepository) {


    //List of one level
    suspend fun GetListOfWordsToPlayForLevel():List<DataWorld>{

        val resp = repository.GetListOfWordsToPlayForLevel()
      //  val resp = repository_2.GetListOfWords()

        return if(resp.isNotEmpty()){

            repository_2.ClearWords()
            repository_2.InsertWord(resp.map { it.toDatabase() })
            repository_2.GetListOfWordsFromRoomById()


        }else{
            repository_2.GetListOfWordsFromRoomById()
        }
    }


    //List of all level by intenet
    suspend  fun GetListOfAlllevel ():List<DataNiveles>?  {
       var resp = repository.GetListOfAllWordsToPlay()

        if(!resp.isNullOrEmpty()){
            repository.ClearLevel()
            repository.InsertLevelToRoom(resp.map{it.toDatabase()})
        }else {
            //In just in case but i don't thing so
            resp = repository.GetListOfLevelFromRoom()
        }

        return resp

        /*var resp = repository.GetListOfAllWordsToPlay()

        if(resp.isNullOrEmpty()){
            repository.ClearLevel()
            repository.InsertLevelToRoom(resp.map{it.toDatabase()})
        }

        return resp*/

    }


    suspend  fun GetListOfAlllevelFromRoom () = repository.GetListOfLevelFromRoom()
}