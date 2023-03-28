package com.example.betadiccompose.Domain

import android.content.Context
import com.example.betadiccompose.ui.Foundation.Shared.MyFavorite.EmptyLst
import com.google.gson.Gson
import java.io.InputStreamReader
import javax.inject.Inject

class LocalFiles @Inject constructor(){


    private  val TARGETS = "archivo.json"
    data class MiObjeto(val languge: String, val code: String,val local:String)



    fun getLocalLanguages(context: Context): ArrayList<String> {
        return ReadFileJSON(context, TARGETS).map { it.local } as ArrayList<String>
    }



    private fun ReadFileJSON(context: Context,FILE_NAME:String): List<MiObjeto> {

        val inputStream = context.assets.open(FILE_NAME)
        val jsonString = InputStreamReader(inputStream).use { it.readText() }
        val gson = Gson()
        val lst  = gson.fromJson(jsonString, Array<MiObjeto>::class.java).toList()

       return lst
    }
}