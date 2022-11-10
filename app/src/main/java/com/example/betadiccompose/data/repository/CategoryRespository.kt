package com.example.betadiccompose.data.repository

import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Domain.model.DataNiveles
import com.example.betadiccompose.Domain.model.DataSentes
import com.example.betadiccompose.Domain.model.DataSubMenu
import com.example.betadiccompose.data.database.dao.GameDao
import com.example.betadiccompose.data.database.entity.GameEntity
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.data.network.CategoryService
import com.example.betadiccompose.data.network.model.toDomain
//import com.example.betadiccompose.data.network.model.toDomain
import javax.inject.Inject

class CategoryRespository @Inject constructor(
    private val prefs: Prefs,
    private val api : CategoryService,
    private val dao:GameDao) {

    //private val api = CategoryService()

    //Categoris
    suspend fun getAllCategorys():List<DataVocabulary>{
        val response = api.getCategorys()
        //return response

        print("llamando repositoy....")
       val result:List<DataVocabulary> = response.mapIndexed { index, it ->
            DataVocabulary(
                id = index,
                name = it.name,
                sub = it.issub,
                path = it.path,
                document = it.doc,
                img = "https://d1i3grysbjja6f.cloudfront.net/IMG/Diccionario/${index}.jpg"

            )
        }

        return  result
    }

    //Words
    suspend fun getWords():List<DataWorld>{
        val response = api.getwordNew()
        val response_2 = api.getwordOld()
        //return response

        var lst :ArrayList<DataWorld> = ArrayList()
        for (i in 0 until response.size){
            val id = response[i].id

            if(!prefs.IsSubMenu()){
                lst.add(
                    DataWorld(
                        id = response[i].id,
                        World_1= response[i].name,
                        World_2 = response_2[i].name,
                        Img = "https://d1i3grysbjja6f.cloudfront.net/IMG/${prefs.GetCategory()}/${response[i].id}.jpg")
                )
            }else{
                print("otro")
                if(prefs.GetNumberCategory() == response[i].subcat){
                  lst.add(
                      DataWorld(
                          id = response[i].id,
                          World_1= response[i].name,
                          World_2 = response_2[i].name,
                          Img = "https://d1i3grysbjja6f.cloudfront.net/IMG/${prefs.GetCategory()}/${response[i].id}.jpg")

                  )

                }




            }

        }

         val list:List<DataWorld> = lst.toList()

        return  list
    }

    //sub
    suspend fun getsubmenu():List<DataSubMenu>{

        val response = api.getdatafromapi()
        val result:List<DataSubMenu> = response.mapIndexed { index, it ->
            DataSubMenu(
                id = it.id,
                name = it.name,
                Img = "https://d1i3grysbjja6f.cloudfront.net/IMG/${prefs.GetCategory()}/${it.doc}.jpg"
            )

        }

        return  result
    }

    //Sentes
    suspend fun getsentes():List<DataSentes>{
        val response = api.getwordNew()
        val response_2 = api.getwordOld()
        //return response

        var lst :ArrayList<DataSentes> = ArrayList()

        for (i in 0 until response.size){
            val id = response[i].id

            lst.add(
                DataSentes(
                    id = response[i].id,
                    sentes_1 =  response[i].name,
                    sentes_2 = response_2[i].name))

        }

        val list:List<DataSentes> = lst.toList()

        return list
    }

    //Niveles
    suspend fun getniveles():List<DataNiveles>{
        val response = api.getdatanivel()

        val result:List<DataNiveles> = response.mapIndexed { index, it ->
            DataNiveles(
                id = index,
                name = it.name,
                animation = "https://dicvocabulary.s3.us-east-2.amazonaws.com/Animaciones/${index}.json",
                dir = it.path.trim()
            )
        }

        return  result
    }


    /**************  Room ****************/

    suspend fun getAllwordFromDataBase():List<DataWorld>{
        val response :List<GameEntity> = dao.getallQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun InsertWord(palabras:List<GameEntity>){
        dao.insertAll(palabras)
    }

    suspend fun clearWords(){
        dao.deleteAllWords()
    }

    suspend fun getAllwordFromDataBaseByID():List<DataWorld>{
        val response :List<GameEntity> = dao.getallQuotesByID()
        return response.map { it.toDomain() }
    }

}