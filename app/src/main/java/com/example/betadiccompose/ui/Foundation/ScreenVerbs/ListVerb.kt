package com.example.betadiccompose.ui.Foundation.ScreenVerbs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.Foundation.ScreenSentes.ItemSentes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun ListVerb(viewmodel: VocabularyViewModel) {

    viewmodel.getGramar()
    val lst = viewmodel.lstgramar.value

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),

        ){
        items(lst){item->
            ItemVerb(item = item, modifier = Modifier.padding(6.dp))
         //   ItemSentes(item = item, modifier = Modifier.padding(6.dp))
        }
    }
}