package com.example.betadiccompose.Domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.betadiccompose.data.local_database.entity.toDatabase
import com.example.betadiccompose.data.network_database.model.*
import com.example.betadiccompose.data.repository.VocabularyRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Vocabulary @Inject constructor(
    @ApplicationContext context:Context,
    private val resposity: VocabularyRepository){
    suspend fun GetListOfWords ():List<DataWorld>? = resposity.GetListOfWords()

    suspend fun ClearWords (){
        resposity.ClearWords()
    }

    suspend  fun GetListOfAllVocabularyFromRoom () =   resposity.GetListOfVocabularyFromRoom()

    suspend fun GetListOfAllCategories ():List<DataVocabulary>?{
        var resp = resposity.GetListOfAllCategories()

        if(resp.isNotEmpty()){
            resposity.ClearVocabulary()
            resposity.InsertVocabulary(resp.map { it.toDatabase() })
        }else{
          resp = resposity.GetListOfVocabularyFromRoom()
        }

        return resp

    }

    suspend fun GetListOfWordsFromRoom():List<DataWorld>{

        val resp = resposity.GetListOfWords()

        return if(resp.isNotEmpty()){
            resposity.ClearWords()
            resposity.InsertWord(resp.map { it.toDatabase() })
            resposity.GetListOfWordsFromRoom()

        }else{
            resposity.GetListOfWordsFromRoom()
        }

    }

    suspend fun GetListOfGramar ():List<DataGramar>? = resposity.GetListOfGramar()

    suspend fun GetListOfSubMenu ():List<DataSubMenu>? = resposity.GetListOfSubMenu()
}