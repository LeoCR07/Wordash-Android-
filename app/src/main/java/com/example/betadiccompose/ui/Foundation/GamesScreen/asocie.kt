package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.ui.Foundation.Shared.Button.*
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun asocie() {

    //val porcentaje = viewModel.Step.value / 20f
    val porcentaje = 20f
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

        GetLogo(icon = true, titulo = "Days")
        NameGame(titulo = "Days")
        LinearProgressIndicator(
            modifier = Modifier
                .height(4.dp)
                .width(340.dp),
            progress = porcentaje
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
        ){
            items(4){
                FilledTonalButtonAsocie(
                    word = "Hola ",
                    onMediaClick = {

                    },
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .padding(6.dp)
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
        ){
            items(4){
                ElevatedButtonAsocie(
                    word = "Hola ",
                    onMediaClick = {

                    },
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .padding(6.dp)
                )
            }
        }



    }
}