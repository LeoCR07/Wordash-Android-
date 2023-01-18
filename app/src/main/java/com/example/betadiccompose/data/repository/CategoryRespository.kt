package com.example.betadiccompose.data.repository

import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Domain.model.DataGramar
import com.example.betadiccompose.Domain.model.DataNiveles
import com.example.betadiccompose.Domain.model.DataSentes
import com.example.betadiccompose.Domain.model.DataSubMenu
import com.example.betadiccompose.data.database.dao.GameDao
import com.example.betadiccompose.data.database.entity.FavoriteWordEntity
import com.example.betadiccompose.data.database.entity.GameEntity
import com.example.betadiccompose.data.database.entity.UserEntity
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.database.model.toDomain
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.data.network.model.DataUser
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

    //Words
    suspend fun getWords():List<DataWorld>{
        val response = api.getwordNew()  //spanish
        val response_2 = api.getwordOld() //english


        var lst :ArrayList<DataWorld> = ArrayList()
        for (i in 0 until response.size){
            val id = response[i].id

            if(!prefs.IsSubMenu()){
                lst.add(
                    DataWorld(
                        id = response[i].id,
                        World_1= response[i].name,
                        World_2 = response_2[i].name,
                        Img = "https://duq14sjq9c7gs.cloudfront.net/Images/${prefs.GetCategory()}/${response[i].id}.jpg")
                )
            }else{
                if(prefs.GetNumberCategory() == response[i].subcat){
                  lst.add(
                      DataWorld(
                          id = response[i].id,
                          World_1= response[i].name,
                          World_2 = response_2[i].name,
                          Img = "https://duq14sjq9c7gs.cloudfront.net/Images/${prefs.GetCategory()}/${response[i].id}.jpg")

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
                Img = "https://duq14sjq9c7gs.cloudfront.net/Images/${prefs.GetCategory()}/${it.img}.jpg",
                doc = it.doc
            )

        }

        return  result
    }

    //Sentes
    suspend fun getsentes():List<DataSentes>{
        val response = api.getwordNew()
        val response_2 = api.getwordOld()

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

    /** Gramar **/
    suspend fun getgramar():List<DataGramar>{
        val gramar_new= api.getwordNew()
        val gramar_old = api.getwordOld()

        val example_old = api.getgramarOld()
        val example_new = api.getgramarNew()


        var lst :ArrayList<DataGramar> = ArrayList()

        for (i in 0 until gramar_new.size){
            val id = gramar_new[i].id

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

        val list:List<DataGramar> = lst.toList()

        return list
    }


    //Lista de niveles
    suspend fun getniveles():List<DataNiveles>{
        val response = api.getdatanivel()

        val result:List<DataNiveles> = response.mapIndexed { index, it ->

            DataNiveles(
                id = index+1,
                name = it.name,
                animation = "https://dicvocabulary.s3.us-east-2.amazonaws.com/lottie/${index}.json",
                dir = it.path.trim(),
                stars =  it.stars,
                inicio = it.inicio,
                fin = it.fin
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

    /**  My favorite word **/

    suspend fun GetAllMyFavoriteWord():List<DataMyFavoriteWord>{
        val response :List<FavoriteWordEntity> = dao.GetAllMyWordFavorite()
        return response.map { it.toDomain() }
    }

    suspend fun InsertMyFavoriteWord(palabra :FavoriteWordEntity){
        dao.insertmyfavoriteword(palabra)
    }

    suspend fun DeleteMyWordByImg(img:String){
         dao.deleteMyFavorityWordByImg(img)
    }


    /**  User data **/
    suspend fun InsertUser(userdata:UserEntity){
        dao.insertUser(userdata)
    }

    suspend fun DeleteUser(){
        dao.deleteAllUser()
    }

    suspend fun GetDetaillsUser():DataUser{
        val result = dao.getDetaillsUser()
        val obj = DataUser()

        return  obj
        //return  result.toDomain()
    }

    suspend fun UpdateExpUser(exp:Int){
        var value = dao.getExp() + exp
        dao.updateExp(value)
    }

    suspend fun UpdateStarsUser(stars:Int){
        var value = dao.getStars() + stars
        dao.updateStars(value)
    }

    suspend fun UpdateLevelUser(level:Int){
        var value = dao.getLevel()+ level
        dao.updateLevel(value)
    }


}