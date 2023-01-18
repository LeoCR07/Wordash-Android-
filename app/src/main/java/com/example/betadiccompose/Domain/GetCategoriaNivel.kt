package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.database.entity.toDatabase
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class GetCategoriaNivel @Inject constructor(private val repository : CategoryRespository) {


    suspend operator fun invoke():List<DataWorld>{

        val resp = repository.getWords()

        return if(resp.isNotEmpty()){

            repository.clearWords()
            repository.InsertWord(resp.map { it.toDatabase() })
            repository.getAllwordFromDataBaseByID()

        }else{
            repository.getAllwordFromDataBaseByID()
        }

    }
}