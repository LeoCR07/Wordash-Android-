package com.example.betadiccompose.Domain

import com.example.betadiccompose.data.local_database.entity.toDatabase
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.repository.FavoriteRepository
import javax.inject.Inject

class Favorite @Inject constructor(private val repository : FavoriteRepository)  {

    /* My Words */


    suspend fun DeleteMyWordByImg (img: String){
        repository.DeleteMyWordByImg(img)
    }

    suspend fun InsertMyFavoriteWord(resp : DataMyFavoriteWord){
        repository.InsertMyFavoriteWord(resp.toDatabase())
    }

    suspend fun insertarmyPalabra(resp:DataMyFavoriteWord){
        repository.insertarmipalabra(resp.toDatabase())
    }


    /* My Sentes */
    suspend fun DeleteMySentesBySentes (sentes: String){
        repository.DeleteMySentesBySentes(sentes)
    }

    suspend fun GetAllMyFavoriteSentes(): List<DataMyFavoriteSentes> {
        return repository.GetListOfAllMyFavoriteSentes()
    }

    suspend fun GetListOfAllMyFavoriteWord(): List<DataMyFavoriteWord> {
        return repository.GetListOfAllMyFavoriteWord()
    }

    suspend fun InsertMyFavoriteSentes(resp : DataMyFavoriteSentes){
        repository.InsertMyFavoriteSentes(resp.toDatabase())
    }

    /* My Gramar */

    suspend fun InsertMyFavoriteGramar(resp : DataMyFavoriteGramar){
        repository.InsertMyFavoriteGramar(resp.toDatabase())
    }

    suspend fun DeleteMyGramarByGramar(gramar: String){
        repository.DeleteMyGramarByGramar(gramar)
    }

    suspend fun GetAllMyFavoriteGramar():List<DataMyFavoriteGramar>{
        val resp = repository.GetListOfAllMyFavoriteGramar()
        return resp
    }


}