package com.example.authentication.ui.Foundation.Account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun HListWord(
    lstmyfavoritewords: List<DataMyFavoriteWord>,
    vocalview: VocabularyViewModel
) {

    LazyRow(contentPadding = PaddingValues(6.dp)){

     //   println("hay palabras guardadas ${lstmyfavoritewords.value.size }")

        items(lstmyfavoritewords){
            itemWord(it,vocalview)
        }
    }
}