package com.example.betadiccompose.ui.Foundation.Category.Navegation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavData(
    val baseRoute :String,
    val navArgs : List<com.example.betadiccompose.ui.Foundation.Category.Navegation.NavArgs> = emptyList()
){
    val route = run{
        val argkey = navArgs.map{ "{${it.Key}}"}
        listOf(baseRoute)
            .plus(argkey)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.Key){type = it.navType}
    }

    object Menu : com.example.betadiccompose.ui.Foundation.Category.Navegation.NavData("menu")

    /*
    object Category : NavData("category"){
        fun createNavRoute() = "$baseRoute/"
    }*/

    object  Category  : com.example.betadiccompose.ui.Foundation.Category.Navegation.NavData("category", listOf(
        com.example.betadiccompose.ui.Foundation.Category.Navegation.NavArgs.MediaId
    )){
        fun createNavRoute(mediaId:Int) = "$baseRoute/$mediaId"
    }
}


enum class NavArgs (val Key:String,val navType : NavType<*>){
    MediaId("key", NavType.IntType)
}
