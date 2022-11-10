package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.AnimationUrl
import java.lang.reflect.Modifier

@Composable
fun GramarScreen(onMediaClick: (DataWorld) -> Unit) {

    MyApp {
        Scaffold {
            Column() {
                GetLogo(true,"Gramar")
                AnimationUrl(
                    url = "https://dicvocabulary.s3.us-east-2.amazonaws.com/beta.json"
                )
                /*
                GetListWorld(
                    onMediaClick = onMediaClick,
                    provider = provider,
                    modifier = Modifier.padding(it))*/
            }

            //
            //GetListMenu(onMediaClick)
            //MediaList(onMediaClick = onMediaClick,modifier = Modifier.padding(it))
        }
    }

}