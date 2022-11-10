package com.example.betadiccompose.ui.Foundation.ScreenSubMenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.model.DataSubMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun ListSubMenu(viewmodel: VocabularyViewModel, onclick: (DataSubMenu) -> Unit) {

    val lst = viewmodel.lstsubmenu.value

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp ),
        contentPadding = PaddingValues(6.dp),

    ){

        items(lst){item->
            ItemSubMenu(item) { onclick(item) }
        }

    }

}