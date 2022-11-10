package com.example.betadiccompose.ui.screens

import android.widget.SearchView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.Domain.Provider.Provider

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.ScreenWorld.GetListWorld
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.SearchAppBar
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun WorldScreen(
    onMediaClick: (DataWorld) -> Unit,
    provider: Provider,
    viewmodel: VocabularyViewModel
) {

    MyApp {
        Scaffold (topBar = {
            TopAppBar(
                title = { Text(text = "hola") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = null
                        )

                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null
                        )

                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Sharp.Settings,
                            contentDescription = null
                        )

                    }

                }
            )
        }){


            viewmodel.getWorlds()

            if(viewmodel.isloding_2.value){
                Animation(R.raw.animacion,modifier = Modifier
                    .fillMaxSize())
            }else{
                Column() {
                   // GetLogo(true,"World")

                    SearchAppBar(
                        text = "Buscar",
                        onTextChange = {},
                        onCloseClicked = { /*TODO*/ },
                        onSearchClicked = {}
                    )
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


