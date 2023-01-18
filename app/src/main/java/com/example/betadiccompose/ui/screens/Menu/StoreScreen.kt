package com.example.betadiccompose.ui.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.Navigation.ItemsMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun StoreScreen(
    current: String?,
    onclickNav: (ItemsMenu) -> Unit,
    navToSettings: () -> Unit,
    viewmodel: VocabularyViewModel
) {

    MyApp {
        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel,opcion = 2, navToSomeWhere = navToSettings)
            },
            bottomBar = { navegationinferior(menu, current, onclickNav) }){


        }


    }
}