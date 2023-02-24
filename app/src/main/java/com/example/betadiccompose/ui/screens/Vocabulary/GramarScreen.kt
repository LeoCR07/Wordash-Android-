package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network_database.model.DataGramar
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSentes.ListSentes
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenVerbs.ListVerb
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun GramarScreen(
    onMediaClick: (DataGramar) -> Unit,
    viewmodel: VocabularyViewModel) {

    MyApp {
        Scaffold (topBar = { TopApp(title = viewmodel.GetCategoryName(), viewModel = viewmodel) }){

            viewmodel.getListOfGramar()

            if(viewmodel.loadGramar.value){
                CircleProgress()
            }else{
                ListVerb(viewmodel = viewmodel,onMediaClick=onMediaClick)
            }
        }
    }

}