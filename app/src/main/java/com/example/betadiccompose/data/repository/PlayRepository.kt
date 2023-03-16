package com.example.betadiccompose.data.repository

import com.example.betadiccompose.Domain.Prefs
import com.example.betadiccompose.data.local_database.dao.GameDao
import com.example.betadiccompose.data.local_database.entity.LevelEntity
import com.example.betadiccompose.data.network_database.model.DataNiveles
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.data.network_database.model.toDomain
import javax.inject.Inject

class PlayRepository @Inject constructor(
    private val prefs: Prefs,
    private val api : ApiService,
    private val dao: GameDao
) {
    //Specific table for a level
    suspend fun GetListOfWordsToPlayForLevel():List<DataWorld>{
        val response = api.getwordNew()  //spanish
        val response_2 = api.getwordOld() //english

        var lst :ArrayList<DataWorld> = ArrayList()

        for (i in 0 until response.size){
            val id = response[i].id
            lst.add(
                DataWorld(
                    id = response[i].id,
                    subcat = response[i].subcat,
                    World_1= response[i].name,
                    World_2 = response_2[i].name,
                    Img = "https://duq14sjq9c7gs.cloudfront.net/Images/${prefs.GetPath()}/${response[i].id}.jpg")

            )
        }

        val list:List<DataWorld> = lst.toList()

        return  list
    }

    //List of all levels
    suspend fun GetListOfAllWordsToPlay():List<DataNiveles>{

        val response = api.getdatanivel()

        val result:List<DataNiveles> = response.mapIndexed { index, it ->
            DataNiveles(
                id = index+1,
                name = it.name,
                animation = "https://duq14sjq9c7gs.cloudfront.net/Animations/Levels/ON/${it.path.trim()}.json",
                dir = it.path.trim(),
                stars =  it.stars,
                doc = it.cat.trim()
            )


        }

        result.forEach {
            println(it)
        }
        return  result
    }


    /**************  level from room ****************/
    suspend fun GetListOfLevelFromRoom():List<DataNiveles>{
        val response :List<LevelEntity> = dao.getalllevel()
        return response.map { it.toDomain() }
    }

    suspend fun InsertLevelToRoom(levels:List<LevelEntity>){
        dao.insertAllLevel(levels)
    }

    suspend fun ClearLevel(){
        dao.deleteAllLevel()
    }

}