package com.example.betadiccompose.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

import com.example.betadiccompose.Runtime.MyApp

import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.MyBannerWords
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSentes.ListSentes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun SentesScreen(
    onClick: (DataWorld) -> Unit,
    viewmodel: VocabularyViewModel,
) {

    var context = LocalContext.current

    MyApp(viewModel = viewmodel, content = {
        Scaffold (
            bottomBar = {
                MyBannerWords()
            },
            topBar = {
                TopApp(
                    title = viewmodel.GetWordCategoryName(),
                   // title = viewmodel.GetCategoryName(),
                    viewModel = viewmodel) }){



            LaunchedEffect(key1 =true ){
                viewmodel.getListOfSentesFromRoom()
                viewmodel.ShowInterstitalVoca(context)
            }

            if(viewmodel.loadSentes.value){
                CircleProgress()
            }else{
                ListSentes(viewmodel = viewmodel,onClick=onClick)
            }

        }
    })

}