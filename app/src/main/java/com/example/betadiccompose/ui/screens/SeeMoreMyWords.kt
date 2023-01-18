package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.ui.Foundation.Account.itemWord
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.ScreenVocabulary.GetListVocabulary
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun SeeMoreMyWords(
    vocalview: VocabularyViewModel,
) {



    MyApp {
        Scaffold(
            topBar = {
                TopApp(title = "My Words", viewModel = vocalview)
            }){


            vocalview.GetMyFavoriteWords()
            val lstmyfavoritewords by remember {
                mutableStateOf(vocalview.lstfavoritewords.value)
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp ),
                contentPadding = PaddingValues(6.dp),
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.02f))
                    .padding(6.dp)
            ){
                items(lstmyfavoritewords) { item ->
                    itemWord(item,vocalview)
                }
            }
        }
    }
}