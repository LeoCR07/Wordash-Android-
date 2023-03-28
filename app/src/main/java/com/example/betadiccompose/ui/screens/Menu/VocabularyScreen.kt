package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.NavToBackDialog
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenVocabulary.GetListVocabulary
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.Navigation.ItemsMenu


val menu = listOf(ItemsMenu.Pantalla_1, ItemsMenu.Pantalla_2, ItemsMenu.Pantalla_3)

@Composable
fun VocabularyScreen(
    onMediaClick: (DataVocabulary) -> Unit,
    vocalview: VocabularyViewModel,
    onclickNav: (ItemsMenu) -> Unit,
    current: String?,
    onBack:()->Unit
) {
    MyApp(viewModel = vocalview, content = {

        NavToBackDialog(onBack)

        Scaffold(
            topBar = {
                     TopApp(viewModel =vocalview)
            },
            bottomBar = {
                navegationinferior(menu,current,onclickNav) }
        )
             {

                 if(vocalview.loadVocabulary.value){
                     CircleProgress()
                 }else{
                     GetListVocabulary(
                         viewmodel = vocalview,
                         onMediaClick = onMediaClick,
                         modifier = Modifier.padding(it) )
                 }

        }
    })
}



