package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.runtime.Composable
import com.example.betadiccompose.Foundation.Menu.GetListMenu
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.Domain.Provider.Provider
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network.model.DataMenu

@Composable
fun MenuScreen(onMediaClick: (DataMenu) -> Unit, provider: Provider) {

    MyApp {
        Scaffold {
            Column() {
                GetLogo(true,"Dicvocabulary")
                GetListMenu(onMediaClick,provider = provider)
            }

           //
            //GetListMenu(onMediaClick)
            //MediaList(onMediaClick = onMediaClick,modifier = Modifier.padding(it))
        }
    }

}