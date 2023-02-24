package com.example.betadiccompose.ui.screens.Menu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network_database.model.DataBooks
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import com.example.betadiccompose.ui.Foundation.Library.GetListLibrary
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenVocabulary.GetListVocabulary
import com.example.betadiccompose.ui.Navigation.ItemsMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.menu

@Composable
fun LibraryScreen(
    onMediaClick: (DataBooks) -> Unit,
    vocalview: VocabularyViewModel,
    onclickNav: (ItemsMenu) -> Unit,
    current: String?
) {
    MyApp {
        Scaffold(
            topBar = {
                TopApp(viewModel = vocalview, opcion = 4)
            },
            bottomBar = {
                navegationinferior(menu, current, onclickNav)
            }
        ) {

            vocalview.getListOfAllBooks()

            if(vocalview.loadBooks.value){
                CircleProgress()
            }else{

                GetListLibrary(
                    viewmodel = vocalview,
                    onMediaClick = onMediaClick,
                    modifier = Modifier.padding(it) )
            }

        }

    }
}