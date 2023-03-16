package com.example.betadiccompose.data.local_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.betadiccompose.data.local_database.entity.*

@Dao
interface GameDao {

    /** Table Level **/
    @Query("select * from level_table")
    suspend fun getalllevel():List<LevelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLevel(level:List<LevelEntity>)

    @Query("Delete from level_table")
    suspend fun deleteAllLevel()


    /** Table vocabulary  **/
    @Query("select * from vocabulary_table ORDER BY id")
    suspend fun getallVocabulary():List<VocabularyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVocabulary(vocabulary:List<VocabularyEntity>)

    @Query("Delete from vocabulary_table")
    suspend fun deleteAllVocabulary()

     /** Table Words  **/

    @Query("select * from word_table ORDER BY id")
    suspend fun getallQuotes():List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quetes:List<WordEntity>)

    @Query("Delete from word_table")
    suspend fun deleteAllWords()

    @Query("select * from word_table ORDER BY id ")
    suspend fun getallQuotesByID():List<WordEntity>


    /**  My favorite word **/


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertmyfavoriteword(words:FavoriteWordEntity_2)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarmipalabra(words:FavoriteWordEntity_2)

    @Query("select * from word_favority_table_2 ORDER BY World_1")
    suspend fun GetAllMyWordFavorite():List<FavoriteWordEntity_2>

    @Query("Delete from word_favority_table_2 where World_1 =:img")
    suspend fun deleteMyFavorityWordByImg(img :String)



    /**  My favorite sentes **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertmyfavoritesentes(quetes:FavoriteSentesEntity)

    @Query("select * from sentes_favority_table ORDER BY Sentes_1")
    suspend fun GetAllMySentesFavorite():List<FavoriteSentesEntity>

    @Query("Delete from sentes_favority_table where Sentes_1 = :sentes_1")
    suspend fun deleteMyFavoritySentesbySentes_1(sentes_1 :String)

    /**  My favorite gramar **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertmyfavoriteGramar(quetes: FavoriteGramarEntity)

    @Query("select * from Gramar_favority_table ORDER BY Gramar_1")
    suspend fun GetAllMyGramarFavorite():List<FavoriteGramarEntity>

    @Query("Delete from Gramar_favority_table where Gramar_1 = :gramar_1")
    suspend fun deleteMyFavorityGramarbyGramar(gramar_1 :String)

    /**  User data **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserEntity)

    @Query("select * from user_table where id=0")
    suspend fun getDetaillsUser():UserEntity

    @Query("Delete from user_table")
    suspend fun deleteAllUser()

    @Query("select count(*) from user_table")
    suspend fun countUser():Int

    /** Exp **/
    @Query("update user_table set exp =:Exp")
    suspend fun updateExp(Exp:Int)

    @Query("select exp from user_table")
    suspend fun getExp():Int

    /** Stars **/

    @Query("update user_table set exp =:Stars")
    suspend fun updateStars(Stars:Int)

    @Query("select stars from USER_TABLE")
    suspend fun getStars():Int

    /** level **/
    @Query("select level  from user_table")
    suspend fun getLevel():Int

    @Query("update user_table set exp =:level")
    suspend fun updateLevel(level:Int)

    /** lives **/
    @Query("select lives  from user_table")
    suspend fun getLives():Int

    @Query("update user_table set lives =:lives")
    suspend fun updateLives(lives:Int)


}