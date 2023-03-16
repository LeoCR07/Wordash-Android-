package com.example.betadiccompose.data.repository

import com.example.betadiccompose.Domain.Prefs
import com.example.betadiccompose.data.network_database.model.DataGramar
import com.example.betadiccompose.data.network_database.model.DataSubMenu
import com.example.betadiccompose.data.local_database.dao.GameDao
import com.example.betadiccompose.data.local_database.entity.*
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.data.network_database.model.toDomain
//import com.example.betadiccompose.data.network.model.toDomain
import javax.inject.Inject

class VocabularyRepository @Inject constructor(
    private val prefs: Prefs,
    private val api : ApiService,
    private val dao:GameDao) {

    /**************  From Api ****************/
    //List of all categories
    suspend fun GetListOfAllCategories():List<DataVocabulary>{
        val response = api.getCategorys()
        //return response


       val result:List<DataVocabulary> = response.mapIndexed { index, it ->
            DataVocabulary(
                id = index,
                name = it.name,
                sub = it.issub,
                path = it.path,
                document = it.doc,
                img = "https://duq14sjq9c7gs.cloudfront.net/Images/Diccionario/${index}.jpg"

            )
        }

        return  result
    }

    //Words and sentes on vocabulary
    suspend fun GetListOfWords():List<DataWorld>{

        val response = api.getwordNew()  //spanish
        val response_2 = api.getwordOld() //english

        var lst :ArrayList<DataWorld> = ArrayList()

        for (i in 0 until response.size){
            val id = response[i].id

            //Cuando no tiene Sub
            if(!prefs.IsSubMenu()){
                lst.add(
                    DataWorld(
                        id = response[i].id,
                        World_1= response[i].name,
                        World_2 = response_2[i].name,
                        Img = "https://duq14sjq9c7gs.cloudfront.net/Images/${prefs.GetPath()}/${response[i].id}.jpg")
                )
            }else{

                //Cuando tiene sub categoria
                if(prefs.GetSubNumberCategory() == response[i].subcat){
                  lst.add(
                      DataWorld(
                          id = response[i].id,
                          subcat = response[i].subcat,
                          World_1= response[i].name,
                          World_2 = response_2[i].name,
                          Img = "https://duq14sjq9c7gs.cloudfront.net/Images/${prefs.GetPath()}/${response[i].id}.jpg")

                  )

                }

            }

        }

         val list:List<DataWorld> = lst.toList()

        return  list
    }

    //List of Gramar
    suspend fun GetListOfGramar():List<DataGramar>{



        val gramar_new= api.getwordNew()
        val gramar_old = api.getwordOld()

        val example_old = api.getgramarOld()
        val example_new = api.getgramarNew()



        var lst :ArrayList<DataGramar> = ArrayList()

        for (i in 0 until gramar_new.size){
            val id = gramar_new[i].id
            val sub = gramar_new[i].subcat

            if(!prefs.IsSubMenu()){
                lst.add(
                    DataGramar(
                        id = id,
                        gramar_1 = gramar_new[i].name,
                        gramar_2 = gramar_old[i].name,
                        example_1= example_new[i].name,
                        example_2 = example_old[i].name
                    )
                )
            }else{
                //Cuando tiene sub categoria
                if(prefs.GetSubNumberCategory() == sub){
                    lst.add(
                        DataGramar(
                            id = id,
                            gramar_1 = gramar_new[i].name,
                            gramar_2 = gramar_old[i].name,
                            example_1= example_new[i].name,
                            example_2 = example_old[i].name
                        )
                    )

                }
            }


        }

        val list:List<DataGramar> = lst.toList()

        return list
    }

    //List of Sub menu
    suspend fun GetListOfSubMenu():List<DataSubMenu>{

        val response = api.getdatafromapi()
        val result:List<DataSubMenu> = response.mapIndexed { index, it ->
            DataSubMenu(
                id = it.id,
                name = it.name,
                Img = "https://duq14sjq9c7gs.cloudfront.net/Images/${prefs.GetPath()}/${it.img}.jpg",
                doc = it.doc
            )

        }

        return  result
    }


    /**************  Vocabulary ****************/

    suspend fun GetListOfVocabularyFromRoom():List<DataVocabulary>{
        val response :List<VocabularyEntity> = dao.getallVocabulary()
        return response.map { it.toDomain()}
    }

    suspend fun InsertVocabulary(vocabulary:List<VocabularyEntity>){
        dao.insertAllVocabulary(vocabulary)
    }

    suspend fun ClearVocabulary(){
        dao.deleteAllVocabulary()
    }



    /**************  Words ****************/

    suspend fun GetListOfWordsFromRoom():List<DataWorld>{
        val response :List<WordEntity> = dao.getallQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun InsertWord(palabras:List<WordEntity>){
        dao.insertAll(palabras)
    }

    suspend fun ClearWords(){
        dao.deleteAllWords()
    }



    suspend fun GetListOfWordsFromRoomById():List<DataWorld>{
        val response :List<WordEntity> = dao.getallQuotesByID()
        return response.map { it.toDomain() }
    }

}