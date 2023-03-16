package com.example.betadiccompose.data.repository

import com.example.betadiccompose.data.local_database.dao.GameDao
import com.example.betadiccompose.data.local_database.entity.FavoriteGramarEntity
import com.example.betadiccompose.data.local_database.entity.FavoriteSentesEntity
import com.example.betadiccompose.data.local_database.entity.FavoriteWordEntity_2
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.local_database.model.toDomain
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val api : ApiService,
    private val dao: GameDao
){
    /**  My favorite word **/



    suspend fun InsertMyFavoriteWord(palabra : FavoriteWordEntity_2){
        dao.insertmyfavoriteword(palabra)
    }

    suspend fun insertarmipalabra(palabra: FavoriteWordEntity_2){
        dao.insertarmipalabra(palabra)
    }

    suspend fun DeleteMyWordByImg(img:String){
        dao.deleteMyFavorityWordByImg(img)
    }


    /**  My favorite sentes **/

    suspend fun GetListOfAllMyFavoriteWord():List<DataMyFavoriteWord>{
        val response :List<FavoriteWordEntity_2> = dao.GetAllMyWordFavorite()
        return response.map { it.toDomain() }
    }

    suspend fun GetListOfAllMyFavoriteSentes():List<DataMyFavoriteSentes>{
        val response :List<FavoriteSentesEntity> = dao.GetAllMySentesFavorite()
        return response.map { it.toDomain() }
    }

    suspend fun InsertMyFavoriteSentes(sentes : FavoriteSentesEntity){
        dao.insertmyfavoritesentes(sentes)
    }

    suspend fun DeleteMySentesBySentes(Sentes:String){
        dao.deleteMyFavoritySentesbySentes_1(Sentes)
    }

    /**  My Favorite Gramar **/
    suspend fun GetListOfAllMyFavoriteGramar():List<DataMyFavoriteGramar>{
        val response :List<FavoriteGramarEntity> = dao.GetAllMyGramarFavorite()
        return response.map { it.toDomain() }
    }

    suspend fun InsertMyFavoriteGramar(gramar : FavoriteGramarEntity){
        dao.insertmyfavoriteGramar(gramar)
    }

    suspend fun DeleteMyGramarByGramar(gramar:String){
        dao.deleteMyFavorityGramarbyGramar(gramar)
    }

}