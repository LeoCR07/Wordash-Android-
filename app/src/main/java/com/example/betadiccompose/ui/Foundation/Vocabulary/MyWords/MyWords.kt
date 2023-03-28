package com.example.betadiccompose.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Vocabulary.MyWords.GetItemMyWord
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun MyWords(
    vocalview: VocabularyViewModel
) {

    MyApp(viewModel = vocalview, content = {
        Scaffold(
            topBar = {
                TopApp(title = "Mis palabras", viewModel = vocalview)
            }){

            vocalview.getMyFavoriteWords()
            val myfavorite = vocalview.mywords.value

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp ),
                contentPadding = PaddingValues(6.dp),
                modifier = Modifier
                    .padding(6.dp)
            ){
                items(myfavorite) { item ->
                    GetItemMyWord(
                        item = item ,
                        modifier = Modifier.padding(6.dp),
                        viewmodel =vocalview)
                }
            }
        }
    })
}