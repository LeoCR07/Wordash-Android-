package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.MyBannerWords
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenWorld.GetListWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun WordScreen(
    onMediaClick: (DataWorld) -> Unit,
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
                    viewModel = viewmodel)
        }){

            LaunchedEffect(key1 =true ){
                viewmodel.getListOfWordsFromRoom()
                viewmodel.ShowInterstitalVoca(context)
            }

            if(viewmodel.loadWords.value){
                CircleProgress()
            }else{

                GetListWorld(
                    viewmodel = viewmodel,
                    onMediaClick = onMediaClick,
                    modifier = Modifier.padding(it))
            }

        }
    })



}


