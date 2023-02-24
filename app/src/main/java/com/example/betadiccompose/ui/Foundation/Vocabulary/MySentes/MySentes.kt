package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Vocabulary.MySentes.GetItemMySentes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun MySentes(
    vocalview: VocabularyViewModel,
) {

    MyApp {
        Scaffold(
            topBar = {
                TopApp(title = "Mis frases", viewModel = vocalview)
            }){

            vocalview.getMyFavoriteSentes()

            val lstmyfavoritesentes by remember {
                mutableStateOf(vocalview.lstfavoritesentes.value)
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(300.dp),
                contentPadding = PaddingValues(6.dp),
                modifier = Modifier
                    .padding(6.dp)
                    .background(Color.Black.copy(alpha = 0.02f))
            ){
                items(lstmyfavoritesentes) { item ->
                    GetItemMySentes(modifier = Modifier.padding(6.dp), item =item , viewmodel =vocalview )
                }
            }
        }
    }
}