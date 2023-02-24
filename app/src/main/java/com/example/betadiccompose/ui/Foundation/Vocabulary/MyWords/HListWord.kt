package com.example.authentication.ui.Foundation.Account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.ui.Foundation.Shared.MyFavorite.EmptyLst
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun HListWord(
    lstmyfavoritewords: List<DataMyFavoriteWord>,
    vocalview: VocabularyViewModel
) {
    if(lstmyfavoritewords.isNotEmpty()){
        LazyRow(contentPadding = PaddingValues(6.dp)){
            items(lstmyfavoritewords){
                itemWord(it,vocalview)
            }
        }
    }else{
        EmptyLst()
    }

}