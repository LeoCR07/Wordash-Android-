package com.example.betadiccompose.ui.Foundation.ScreenWorld


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Domain.Provider.Provider
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun GetListWorld (
    onMediaClick: (DataWorld) -> Unit,
    provider: Prefs,
    modifier: Modifier,
    viewmodel: VocabularyViewModel
) {
    //val lst  = provider.createlistword()


    val lst = viewmodel.lstwords.value


    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp ),
        contentPadding = PaddingValues(6.dp),
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.02f))

    ){
        items(lst){item->
            GetItemWorld(
                onClick =  {onMediaClick(item)},
                item = item, Modifier.padding(6.dp),
                viewmodel = viewmodel,
                prefs = provider
            )
        }
    }
}