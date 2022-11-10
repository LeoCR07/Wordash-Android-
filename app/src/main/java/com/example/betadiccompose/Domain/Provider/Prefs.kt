package com.example.betadiccompose.Domain.Provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Prefs @Inject constructor(@ApplicationContext context: Context) {

    private val db = "datos"  //Base de datos
    private val KeyCategory = "KeyCat" // LLave para la categoria
    private val KeyIndex = "KeyIndex"  //Index de la navegacion
    private val KeyDocument: String = "KeyDoc" //kind of document
    private val KeySub:String = "KeySub" // Index del sub
    private val KeyOld:String= "keyOld"
    private val KeyNew:String = "KeyNew"
    private val KeyNumberCategory:String = "KeyNumberCategory"

    val storage = context.getSharedPreferences(db,0)

    /** Category **/
    fun SavePath(category:String){
        storage.edit().putString(KeyCategory,category).apply()
    }

    fun GetCategory():String{
        return storage.getString(KeyCategory,"")!!
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
    fun SaveNew(language:Int){
        storage.edit().putInt(KeyNew,language).apply()
    }

    fun GetNew():Int{
       // return storage.getInt(KeyNew,0)
        return 1
    }


    /** Languages old **/
    fun SaveOld(language:Int){
        storage.edit().putInt(KeyOld,language).apply()
    }

    fun GetOld():Int{
       // return storage.getInt(KeyOld,0)
        return 0
    }



    /** Number of category **/
    fun SaveNumberCategory(number:Int){
        storage.edit().putInt(KeyNumberCategory,number).apply()
    }

    fun GetNumberCategory():Int{
        return storage.getInt(KeyNumberCategory,0)
    }

    /********* Respuesta de NIVEL */



    fun dirreccion() = "vocabulario.json"

    fun vegetales_1() = "Categorias/0/Vegetales.json"

    fun vegetales_2() = "Categorias/1/Vegetales.json"

    fun  subpath() = "subcat/1/sub.json"

    fun  getlink_1() = "subcat/0/1.json"

    fun  getlink_2() = "subcat/0/1.json"

}