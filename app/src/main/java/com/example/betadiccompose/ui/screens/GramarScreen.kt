package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.ScreenSentes.ListSentes
import com.example.betadiccompose.ui.Foundation.ScreenVerbs.ListVerb
import com.example.betadiccompose.ui.Foundation.Shared.AnimationUrl
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.lang.reflect.Modifier

@Composable
fun GramarScreen(onMediaClick: (DataWorld) -> Unit, viewmodel: VocabularyViewModel) {

    MyApp {
        Scaffold (topBar = { TopApp(title = viewmodel.GetCategoryName(), viewModel = viewmodel) }){
            Column {
                ListVerb(viewmodel = viewmodel)
            }
        }
    }

}