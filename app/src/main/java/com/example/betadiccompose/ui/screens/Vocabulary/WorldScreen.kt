package com.example.betadiccompose.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenWorld.GetListWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun WordScreen(
    onMediaClick: (DataWorld) -> Unit,
    viewmodel: VocabularyViewModel,
) {

    MyApp {
        Scaffold (
            topBar = {
                TopApp(
                    title = viewmodel.GetWordCategoryName(),
                    viewModel = viewmodel)
        }){
            viewmodel.getListOfWordsFromRoom()

            if(viewmodel.loadWords.value){
                CircleProgress()
            }else{
                GetListWorld(
                    viewmodel = viewmodel,
                    onMediaClick = onMediaClick,
                    modifier = Modifier.padding(it))
            }
        }
    }

}


