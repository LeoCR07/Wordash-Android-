package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSubMenu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.network_database.model.DataSubMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun ListSubMenu(
    viewmodel: VocabularyViewModel,
    onclick: (DataSubMenu) -> Unit) {

    val lst = viewmodel.lstsubmenu.value

    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp ),
        contentPadding = PaddingValues(6.dp),

    ){
        items(lst){item->
            ItemSubMenu(item) { onclick(item) }
        }
    }
}