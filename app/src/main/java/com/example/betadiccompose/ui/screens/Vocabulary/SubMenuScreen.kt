package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.betadiccompose.data.network_database.model.DataSubMenu

import com.example.betadiccompose.Runtime.MyApp

import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSubMenu.ListSubMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun SubMenuScreen(
    onMediaClick: (DataSubMenu) -> Unit,
    viewmodel: VocabularyViewModel) {

    MyApp (viewModel = viewmodel, content = {
        Scaffold (
            bottomBar = {
            },
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
    })
}