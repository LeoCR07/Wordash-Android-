package com.example.betadiccompose.ui.Navigation

import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Navigation.routes.MenuRoutes

sealed class ItemsMenu(
    val iconUnselected: Int,
    val iconSelected: Int,
    val route: String){

    object Pantalla_2 : ItemsMenu(R.drawable.libro,R.drawable.libro_out, MenuRoutes.learn.name)
    object Pantalla_1: ItemsMenu(R.drawable.play_on,R.drawable.play_off, MenuRoutes.play.name)
    object Pantalla_3 : ItemsMenu(R.drawable.account_on,R.drawable.account_off, MenuRoutes.account.name)
    object Pantalla_4 : ItemsMenu(R.drawable.store_on,R.drawable.store_off, MenuRoutes.store.name)
}
