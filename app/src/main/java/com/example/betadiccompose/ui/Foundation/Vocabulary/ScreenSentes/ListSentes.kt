package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSentes

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
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun ListSentes(
    viewmodel: VocabularyViewModel,
    onClick: (DataWorld) -> Unit
) {
    val lst = viewmodel.lstWords.value

    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(6.dp),
        modifier = Modifier
            .padding(6.dp)
            .background(Color.Black.copy(alpha = 0.02f))
    ){
        items(lst){item->
            ItemSentes(
                item = item,
                modifier = Modifier.padding(6.dp),
                viewmodel = viewmodel,
            ) { onClick(item) }
        }
    }
}
