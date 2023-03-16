package com.example.betadiccompose.ui.Foundation.Game.ScreenNiveles

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.network_database.model.DataNiveles
import com.example.betadiccompose.ui.Foundation.Shared.DialogLenguage
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.PopUpNoLives
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

@Composable
fun ListNiveles(
    viewmodel: VocabularyViewModel,
    modifier: Modifier,
    onMediaClick: (DataNiveles) -> Unit)  {

    val lst = viewmodel.lstniveles.value

    var opendialog = remember { mutableStateOf(false) }


    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp ),
        contentPadding = PaddingValues(6.dp),
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.00f))

    ){

        lst.forEachIndexed { index, e ->
            val span = if(e.id % 3 == 0){
                GridItemSpan(2)
            }else{
                GridItemSpan(1)
            }

            item(span = {span}) {
                ItemNiveles(
                    viewModel = viewmodel,
                    onClick =  {

                        if(viewmodel.lstdatauser.value.lives == 0){
                            opendialog.value = true
                        }else{
                            onMediaClick(e)
                        }

                        /*
                        if(it.id == viewmodel.lstdatauser.value.level){
                        }


                         */},
                    item = e,Modifier.padding(6.dp))
            }
        }


    }

    PopUpNoLives(
        viewModel = viewmodel,
        show = opendialog.value,
        dimisissDialog =  {opendialog.value = false})
}