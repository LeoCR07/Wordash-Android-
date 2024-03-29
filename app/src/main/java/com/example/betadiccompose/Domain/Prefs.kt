package com.example.betadiccompose.Domain

import android.content.Context
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class Prefs @Inject constructor(
    files :LocalFiles,
    @ApplicationContext context: Context) {

    val file = files
    val contexto = context

    private val KeyReview = "KeyReview"
    private val db = "datos"  //Base de datos
    private val KeySubCats = "KeySubCats"
    private val KeyNameSubCat = "KeySubCatsName"
    private val KeyCategory = "KeyCat" // LLave para la categoria
    private val KeyIndex = "KeyIndex"  //Index de la navegacion
    private val KeyDocument: String = "KeyDoc" //kind of document
    private val KeySub:String = "KeySub" // Index del sub
    private val KeyOld:String= "keyOld"
    private val KeyNew:String = "KeyNew"
    private val KeyNumberCategory:String = "KeyNumberCategory"
    private val KeyGameInit:String = "KeyIndexInitGame"
    private val KeyGameEnd:String = "KeyIndexEndGame"
    private val KeyCatName:String = "KeyIndexCat"
    private val Keyfirsttime:String = "timeFirstTime"
    private val KeyIndexCurrentLevel:String = "IndexCurrentLevel"

    private val KeyIsLogin :String = "KeyIsLogin"
    private val KeyCountInterstitialAdGame:String = "KeyCountInterstitialAdGame"
    private val  KeyCountInterstitialAdVoca:String = "KeyCountInterstitialAdVoca"

    private val  KeyTheme:String = "KeyTheme"

    val storage = context.getSharedPreferences(db,0)

    /** Category **/
    fun SavePath(category:String){
        storage.edit().putString(KeyCategory,category).apply()
    }

    fun SaveCats(subs:String){
        storage.edit().putString(KeySubCats,subs).apply()
    }

    fun SaveIndexGame(start:Int,end:Int){
        storage.edit().putInt(KeyGameInit,start).apply()
        storage.edit().putInt(KeyGameEnd,end).apply()
    }

    fun GetPath():String{
        return storage.getString(KeyCategory,"")!!
    }

    fun GetSubCategory():String{
        return storage.getString(KeySubCats,"0")!!
    }

    /** index **/
    fun SaveIndex(index:Int){
        storage.edit().putInt(KeyIndex,index).apply()
    }

    fun GetIndex():Int{
        return storage.getInt(KeyIndex,0)
    }

    /** kind of document **/
    fun SaveDocument(document:Int){
        storage.edit().putInt(KeyDocument,document).apply()
    }

    fun GetDocument():Int{
        return storage.getInt(KeyDocument,0)
    }

    /** SubMenu **/
    fun IsSaveSubMenu(sub:Boolean){
        storage.edit().putBoolean(KeySub,sub).apply()
    }

    fun IsSubMenu():Boolean{
        return storage.getBoolean(KeySub,false)
    }

    /** Languages new **/
    fun SaveLearnLanguage(language:String){
        storage.edit().putString(KeyNew,language).apply()
    }

    fun GetLearnLanguage(): String{
       return storage.getString(KeyNew,"English")!!
    }


    /** Languages old **/
    fun SaveLocalLanguage(language:String){
        storage.edit().putString(KeyOld,language).apply()
    }

    fun GetLocalLanguage():String{
        var idioma = "English"

        val defaultLocale = Locale.getDefault()
        val code = defaultLocale.language

        for( e in file.readLocalLanguege(contexto)){
            if(e.code == code){
                println("code ${e.code}  $code")
                idioma = e.language
            }
        }
        return storage.getString(KeyOld,idioma)!!
    }

    /** Number of category **/
    fun SaveSubNumberCategory(number:Int){
        storage.edit().putInt(KeyNumberCategory,number).apply()
    }

    fun GetSubNumberCategory():Int{
        return storage.getInt(KeyNumberCategory,0)
    }

    fun SaveNameCategory(name: String) {
        storage.edit().putString(KeyCatName,name).apply()
    }

    fun GetNameCategory():String {
        return storage.getString(KeyCatName,"")!!
    }

    fun GetWordNameCategory():String{
        return storage.getString(KeyNameSubCat,"")!!
    }

    fun SaveWordNameCategory(name:String){
        storage.edit().putString(KeyNameSubCat,name).apply()
    }


    fun SaveIndex(dato: DataVocabulary){
        SaveIndex(dato.id)  //Category ID
        SaveNameCategory(dato.name)  //Nombre de la categoria de la sub
        SaveWordNameCategory(dato.name) //Nombre de la categoria de la palabra
        SavePath(dato.path)  //Direccion para aceder a todos los datos
        SaveDocument(dato.document) //Guarda el tipo de documento
        IsSaveSubMenu(dato.sub) //Determina si tiene un sub menu
    }

    fun SaveFirstTime(time:String){
        storage.edit().putString(Keyfirsttime,time).apply()
    }

    fun SetIndexCurrentLevel(index:Int){
        storage.edit().putInt(KeyIndexCurrentLevel,index).apply()
    }

    fun GetIndexCurrentLevel() = storage.getInt(KeyIndexCurrentLevel,1)!!


    fun GetFirstTime() = storage.getString(Keyfirsttime,"null")!!


    fun setCountInterstitialAdVoca(c:Int){
        storage.edit().putInt(KeyCountInterstitialAdVoca,c).apply()
    }

    fun getCountInterstitialAdVoca()= storage.getInt(KeyCountInterstitialAdVoca,0)!!

    fun setCountInterstitialAdGame(c:Int){
        storage.edit().putInt(KeyCountInterstitialAdGame,c).apply()
    }

    fun getCountInterstitialAdGame() = storage.getInt(KeyCountInterstitialAdGame,0)!!


    // Theme
    fun setTheme(num:Int){
        storage.edit().putInt(KeyTheme,num).apply()
    }

    fun getTheme()= storage.getInt(KeyTheme,0)!!

    /**  Login **/
    fun isLogin():Boolean= storage.getBoolean(KeyIsLogin,false)!!

    fun setIsLogin(value:Boolean){
        storage.edit().putBoolean(KeyIsLogin,value).apply()
    }

    fun getReview() = storage.getBoolean(KeyReview,false)

    fun setReview(){
        storage.edit().putBoolean(KeyReview,true).apply()
    }

}