package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.betadiccompose.data.network_database.model.DataNiveles

import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Game.ScreenNiveles.ListNiveles
import com.example.betadiccompose.ui.Foundation.MyThemeSplash
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.NavToBackDialog
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.Navigation.ItemsMenu

@Composable
fun PlayScreen(
    vocalview: VocabularyViewModel,
    onMediaClick: (DataNiveles) -> Unit,
    current: String?,
    onclickNav: (ItemsMenu) -> Unit,
    onBack: () -> Unit
) {
    NavToBackDialog(onBack,)

    MyThemeSplash() {
        Scaffold (
            topBar = {
            TopApp(vocalview) },
            bottomBar = {
                navegationinferior(menu,current,onclickNav) }){
            if (vocalview.isloding_5.value) {
                CircleProgress()
            }
            else {
                ListNiveles(
                    viewmodel = vocalview,
                    modifier = Modifier.padding(it),
                    onMediaClick = onMediaClick
                )
            }
        }
    }
}