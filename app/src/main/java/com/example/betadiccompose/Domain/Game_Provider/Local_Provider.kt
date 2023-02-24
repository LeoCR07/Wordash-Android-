package com.example.betadiccompose.Domain.Game_Provider

import android.content.Context
import android.content.res.AssetManager
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataMenu
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import com.example.betadiccompose.data.network_database.model.DataWorld
import java.io.InputStream

class Local_Provider (val ass: AssetManager, val context: Context) {

    //val prefs: Prefs = Prefs(context)

    fun CreateListOfSettings(): ArrayList<DataMenu> {

        val i1 = 0
        var lst: ArrayList<DataMenu> = ArrayList()
        val inputStream: InputStream = ass.open("Menu")

        val inputString = inputStream.reader().use { it.readText() }
        val data: List<String> = inputString.split('\n')

        for (i in 1 until data.size) {
            val opcion: String = data[i].split(";")[i1]
            val id = (i - 1)
            lst.add(DataMenu(Id = id, Menu_1 = opcion, ImgMenu = R.drawable.ic_launcher_foreground))
        }

        return lst
    }

}