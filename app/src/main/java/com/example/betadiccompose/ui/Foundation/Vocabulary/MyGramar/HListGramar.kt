package com.example.betadiccompose.ui.Foundation.Vocabulary.MyGramar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.MyFavorite.EmptyLst
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun HListGramar(
    lstmyfavoritegramar: List<DataMyFavoriteGramar>,
    vocalview: VocabularyViewModel
) {

    if(lstmyfavoritegramar.isNotEmpty()){
        LazyRow(contentPadding = PaddingValues(6.dp)){

            items(lstmyfavoritegramar){
                itemGramar(it,vocalview)
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }else{
        EmptyLst()
    }


}