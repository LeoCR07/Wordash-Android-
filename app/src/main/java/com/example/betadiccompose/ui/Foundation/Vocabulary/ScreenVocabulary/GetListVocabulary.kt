@file:OptIn(ExperimentalFoundationApi::class)

package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenVocabulary

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.betadiccompose.Domain.Game_Provider.Local_Provider
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun GetListVocabulary (
    onMediaClick: (DataVocabulary) -> Unit,
    modifier: Modifier,
    viewmodel: VocabularyViewModel
) {
    val lst = viewmodel.lstVocabulary.value

    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp ),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.01f))
    ){
       items( lst){item->
            GetItemVocabulary(onClick =  {onMediaClick(item)},item = item,Modifier.padding(6.dp))
        }
    }

}