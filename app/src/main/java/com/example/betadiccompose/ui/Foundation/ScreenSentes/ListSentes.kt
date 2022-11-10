package com.example.betadiccompose.ui.Foundation.ScreenSentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun ListSentes(viewmodel: VocabularyViewModel) {
    viewmodel.getSentes()
    val lst = viewmodel.lstsentes.value

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),

    ){
        items(lst){item->
            ItemSentes(item = item, modifier = Modifier.padding(6.dp))
        }
    }
}
