package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Domain.Provider.Provider

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.ScreenWorld.GetListWorld
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun WorldScreen(
    onMediaClick: (DataWorld) -> Unit,
    provider: Prefs,
    viewmodel: VocabularyViewModel
) {

    MyApp {
        Scaffold (
            topBar = {
                TopApp(title = viewmodel.GetCategoryName(), viewModel = viewmodel)
           // TopAppBar()
        }){


            viewmodel.getWorlds()

            if(viewmodel.isloding_2.value){
                Animation(R.raw.animacion,modifier = Modifier
                    .fillMaxSize())
            }else{
                Column() {
                   // GetLogo(true,"World")

                    GetListWorld(
                        viewmodel = viewmodel,
                        onMediaClick = onMediaClick,
                        provider = provider,
                        modifier = Modifier.padding(it))
                }

            }



            //
            //GetListMenu(onMediaClick)
            //MediaList(onMediaClick = onMediaClick,modifier = Modifier.padding(it))
        }
    }

}


