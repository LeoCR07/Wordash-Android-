package com.example.betadiccompose.ui.Foundation.Vocabulary.MySentes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes
import com.example.betadiccompose.ui.Foundation.Shared.MyFavorite.EmptyLst
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun HListSentes(
    lstmyfavoritesentes: List<DataMyFavoriteSentes>,
    vocalview: VocabularyViewModel
) {
    if(lstmyfavoritesentes.isNotEmpty()){
        LazyRow(contentPadding = PaddingValues(6.dp)){

            items(lstmyfavoritesentes){
                itemSentes(it,vocalview)
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }else{
        EmptyLst()
    }
}