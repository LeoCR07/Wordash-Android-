package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.betadiccompose.Domain.model.DataSubMenu
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.ScreenSubMenu.ListSubMenu
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.Floating
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun SubMenuScreen(onMediaClick: (DataSubMenu) -> Unit, viewmodel: VocabularyViewModel) {

    MyApp {
        Scaffold {

            viewmodel.getSubMenu()
            if(viewmodel.isloding_3.value){
                Animation(R.raw.animacion,     modifier = Modifier
                    .fillMaxSize())
            }else{
                Column {
                    GetLogo(true,"sub menu")
                    ListSubMenu(viewmodel = viewmodel,onclick = onMediaClick)
                }
            }

        }
    }
}