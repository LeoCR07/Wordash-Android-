package com.example.betadiccompose.ui.Foundation.Game.ScreenNiveles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.network_database.model.DataNiveles
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun ListNiveles(
    viewmodel: VocabularyViewModel,
    modifier: Modifier,
    onMediaClick: (DataNiveles) -> Unit)  {

    val lst = viewmodel.lstniveles.value

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp ),
        contentPadding = PaddingValues(6.dp),
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.00f))

    ){

        lst.forEachIndexed { index, e ->
            val span = if(e.id % 3 == 0){
                GridItemSpan(2)
            }else{
                GridItemSpan(1)
            }

            item(span = {span}) {
                ItemNiveles(
                    viewModel = viewmodel,
                    onClick =  {onMediaClick(e)},
                    item = e,Modifier.padding(6.dp))
            }
        }


    }
}