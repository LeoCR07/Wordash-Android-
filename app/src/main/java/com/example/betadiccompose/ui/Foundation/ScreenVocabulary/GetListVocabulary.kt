@file:OptIn(ExperimentalFoundationApi::class)

package com.example.betadiccompose.ui.Foundation.ScreenVocabulary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Provider.Provider
import com.example.betadiccompose.data.network.model.DataCategory
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun GetListVocabulary (
    onMediaClick: (DataVocabulary) -> Unit,
    provider: Provider,
    modifier: Modifier,
    viewmodel: VocabularyViewModel
) {

    val lst = viewmodel.rquote.value


    /*
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier
    ){

        for (e in lst){
            item(){
                GetItemVocabulary(onClick =  {onMediaClick(e)},item = e,Modifier.padding(6.dp))
            }
        }
    }
    */




    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp ),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.01f))
    ){
       items( lst){item->
            GetItemVocabulary(onClick =  {onMediaClick(item)},item = item,Modifier.padding(6.dp))
        }
    }

}