package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.betadiccompose.data.network_database.model.DataSubMenu

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSubMenu.ListSubMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun SubMenuScreen(
    onMediaClick: (DataSubMenu) -> Unit,
    viewmodel: VocabularyViewModel) {

    MyApp {
        Scaffold (
            topBar = {
                TopApp(
                    title = viewmodel.GetCategoryName(),
                    viewModel = viewmodel)
            }){

            viewmodel.getListOfSubMenu()

            if(viewmodel.loadSubMenu.value){
                CircleProgress()
            }else{
                Column {
                    ListSubMenu(viewmodel = viewmodel,onclick = onMediaClick)
                }
            }

        }
    }
}