package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.betadiccompose.Domain.Provider.Provider

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.ui.Foundation.ScreenVocabulary.GetListVocabulary
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.Floating
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.model.ItemsMenu


val menu = listOf(ItemsMenu.Pantalla_1,ItemsMenu.Pantalla_2,ItemsMenu.Pantalla_3)

@Composable
fun VocabularyScreen(
    onMediaClick: (DataVocabulary) -> Unit,
    provider: Provider,
    vocalview: VocabularyViewModel,
    navController: NavController,
    onclickNav: (ItemsMenu) -> Unit,
    current: String?
) {
    MyApp {
        Scaffold(
            topBar = {
                     TopApp()
            },
            floatingActionButton = {
                                   Floating()
            },
            isFloatingActionButtonDocked = true,
            bottomBar = {
                navegationinferior(menu,current,onclickNav) }
        )
                /* Bottom app bar content */
             {

           // vocalview.getList()

                 println("Hola mundo como esta")

            if(vocalview.isloding.value){
                Animation(R.raw.animacion   ,  modifier = Modifier
                        .fillMaxSize())
            }else{
                Column() {


                    GetListVocabulary(
                        viewmodel = vocalview,
                        onMediaClick = onMediaClick,
                        provider = provider,
                        modifier = Modifier.padding(it) )
                }
            }
        }
    }

}



