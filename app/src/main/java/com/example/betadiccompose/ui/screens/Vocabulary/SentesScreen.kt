package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSentes.ListSentes
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenWorld.GetListWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import kotlinx.coroutines.Job

@Composable
fun SentesScreen(
    onClick: (DataWorld) -> Unit,
    viewmodel: VocabularyViewModel,
) {


    MyApp {
        Scaffold (topBar = { TopApp(title = viewmodel.GetCategoryName(), viewModel = viewmodel) }){

            viewmodel.getListOfSentesFromRoom()

            if(viewmodel.loadSentes.value){
                CircleProgress()
            }else{
                ListSentes(viewmodel = viewmodel,onClick=onClick)
            }

        }
    }

}