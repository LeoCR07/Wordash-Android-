package com.example.betadiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.betadiccompose.Domain.Provider.Prefs

import com.example.betadiccompose.Foundation.Category.Navegation.Navegation
import com.example.betadiccompose.Domain.Provider.Provider
import com.example.betadiccompose.data.network.model.DataUser
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.GeneradorJsonScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val VocaVM : VocabularyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs:Prefs = Prefs(applicationContext)
        val provider = Provider(assets,applicationContext)
        //val list = provider.createListMenu()
        //for (item in list) println(item.Menu_1)
       // provider.CreateJson()

        /** ViewModel **/
        //VocaVM.onCreate()
        //VocaVM.getList()
       // val lista = VocaVM.categoryModel.value


        println("Hola")

        /*
        VocaVM.rquote.observe(this, Observer {
            println("Este es el autor: ${it.author}")
        })*/

        provider.CreateJson()


        setContent {

            //GeneradorJsonScreen(provider)
            Navegation(provider,prefs,VocaVM)
        }


    }
}





