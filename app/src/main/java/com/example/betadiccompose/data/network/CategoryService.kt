package com.example.betadiccompose.data.network

import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Domain.model.DataNiveles
import com.example.betadiccompose.data.network.model.DataCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CategoryService @Inject constructor(
    private val pref:Prefs,
    private val api:IApiClient){
    //private val retrofit = RetrofitHelper.getRetrofit()

    //Categories
    suspend fun getCategorys():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories(pref.dirreccion())
            response.body()?: emptyList()
        }
    }


    //Words
    suspend fun getwordNew():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories("Categorias/${pref.GetNew()}/${pref.GetCategory()}.json")
            response.body()?: emptyList()
        }
    }

    suspend fun getwordOld():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories("Categorias/${pref.GetOld()}/${pref.GetCategory()}.json")
            response.body()?: emptyList()
        }
    }


    suspend fun getdatafromapi():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories("subcat/${pref.GetNew()}/${pref.GetIndex()}.json")
            response.body()?: emptyList()
        }
    }


    suspend fun getdatanivel():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getsomethings("niveles/0/niveles.json")
            response.body()?: emptyList()
        }
    }


}