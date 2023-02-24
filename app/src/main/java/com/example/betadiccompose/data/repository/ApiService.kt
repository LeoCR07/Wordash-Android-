package com.example.betadiccompose.data.repository

import com.example.betadiccompose.Domain.Game_Provider.Prefs
import com.example.betadiccompose.data.network_database.IApiClient
import com.example.betadiccompose.data.network_database.model.DataCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiService @Inject constructor(
    private val pref:Prefs,
    private val api: IApiClient
){



    //List of vocabulary categories
    suspend fun getCategorys():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.GetDataFromAws("Vocabulary/${pref.GetLearnLanguage()}/vocabulary.json")
            response.body()?: emptyList()
        }
    }

    //List of the fisrt words and sentes
    suspend fun getwordNew():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.GetDataFromAws("Vocabulary/${pref.GetLearnLanguage()}/${pref.GetPath()}.json")
            println(pref.GetPath())
            response.body()?: emptyList()
        }
    }

    //List of the second words and sentes
    suspend fun getwordOld():List<DataCategory>{
        return withContext(Dispatchers.IO){
            //val response : Response<List<DataCategory>> = api.getAllCategories("Categorias/${pref.GetOld()}/${pref.GetCategory()}.json")
            val response : Response<List<DataCategory>> = api.GetDataFromAws("Vocabulary/${pref.GetLocalLanguage()}/${pref.GetPath()}.json")
            response.body()?: emptyList()
        }
    }

    //List of the fisrt gramar
    suspend fun getgramarNew():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.GetDataFromAws("Vocabulary/${pref.GetLearnLanguage()}/${pref.GetPath()}ex.json")
            response.body()?: emptyList()
        }
    }

    //List of the second gramar
    suspend fun getgramarOld():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.GetDataFromAws("Vocabulary/${pref.GetLocalLanguage()}/${pref.GetPath()}ex.json")
            response.body()?: emptyList()
        }
    }


    //List of the sub menus by categories
    suspend fun getdatafromapi():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.GetDataFromAws("subcat/${pref.GetLearnLanguage()}/${pref.GetPath()}.json")
            response.body()?: emptyList()
        }
    }


    //List of all levels
    suspend fun getdatanivel():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.GetDataFromAws("level/levels.json")
            response.body()?: emptyList()
        }
    }

    //List of the sub menus by categories
    suspend fun getbooks():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.GetDataFromAws("Books/books.json")
            response.body()?: emptyList()
        }
    }

}