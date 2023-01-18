package com.example.betadiccompose.data.repository

import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.data.network.IApiClient
import com.example.betadiccompose.data.network.model.DataCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CategoryService @Inject constructor(
    private val pref:Prefs,
    private val api: IApiClient
){
    //private val retrofit = RetrofitHelper.getRetrofit()

    //Categories
    suspend fun getCategorys():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories("Vocabulary/${pref.GetLearnLanguage()}/vocabulary.json")
            response.body()?: emptyList()
        }
    }

    //Words to learn
    suspend fun getwordNew():List<DataCategory>{
        return withContext(Dispatchers.IO){
           // val response : Response<List<DataCategory>> = api.getAllCategories("Categorias/${pref.GetNew()}/${pref.GetCategory()}.json")
            val response : Response<List<DataCategory>> = api.getAllCategories("Vocabulary/${pref.GetLearnLanguage()}/${pref.GetCategory()}.json")
            println(pref.GetCategory())
            response.body()?: emptyList()
        }
    }

    //Words local
    suspend fun getwordOld():List<DataCategory>{
        return withContext(Dispatchers.IO){
            //val response : Response<List<DataCategory>> = api.getAllCategories("Categorias/${pref.GetOld()}/${pref.GetCategory()}.json")
            val response : Response<List<DataCategory>> = api.getAllCategories("Vocabulary/${pref.GetLocalLanguage()}/${pref.GetCategory()}.json")
            response.body()?: emptyList()
        }
    }

    //Gramar learn
    suspend fun getgramarNew():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories("Vocabulary/${pref.GetLearnLanguage()}/${pref.GetCategory()}ex.json")
            response.body()?: emptyList()
        }
    }

    //Gramar local
    suspend fun getgramarOld():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories("Vocabulary/${pref.GetLocalLanguage()}/${pref.GetCategory()}ex.json")
            response.body()?: emptyList()
        }
    }


//Sub categoria
    suspend fun getdatafromapi():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getAllCategories("subcat/${pref.GetLearnLanguage()}/${pref.GetCategory()}.json")
            response.body()?: emptyList()
        }
    }

    suspend fun getdatanivel():List<DataCategory>{
        return withContext(Dispatchers.IO){
            val response : Response<List<DataCategory>> = api.getsomethings("niveles/0/TodosLiveles.json")
            response.body()?: emptyList()
        }
    }


}