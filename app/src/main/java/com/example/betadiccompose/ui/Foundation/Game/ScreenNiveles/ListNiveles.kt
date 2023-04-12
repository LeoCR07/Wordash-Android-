package com.example.betadiccompose.ui.Foundation.Game.ScreenNiveles

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataNiveles
import com.example.betadiccompose.ui.Foundation.Shared.DialogLenguage
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.PopUpNoLives
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun ListNiveles(
    viewmodel: VocabularyViewModel,
    modifier: Modifier,
    onMediaClick: (DataNiveles) -> Unit)  {

    val lst = viewmodel.lstniveles.value

    var opendialog = remember { mutableStateOf(false) }


    var end by remember { mutableStateOf(0.dp) }
    var start by remember { mutableStateOf(0.dp) }

    LazyColumn(
        contentPadding = PaddingValues(6.dp),
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.00f))
    ){


        lst.forEachIndexed { index, e ->
            item{

                if(index % 2 == 0){
                    end = 40.dp
                    start = 0.dp
                } else {
                    end = 0.dp
                    start = 40.dp
                }


                ItemNiveles(
                    viewModel = viewmodel,
                    onClick =  {
                        if(viewmodel.lstdatauser.value.lives == 0){
                            opendialog.value = true
                        }else{
                            onMediaClick(e)
                        }
                    },
                    item = e,
                    Modifier
                        .fillMaxWidth()
                        .padding(start = start,end = end)
                )


                }


        }



        /*
        items(20){
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f).background(Color.Red)) {
                    Icon(
                        painter = painterResource(R.drawable.crown),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp),
                    )
                }
                Box(modifier = Modifier.weight(1f).background(Color.Cyan)) {
                    Icon(
                        painter = painterResource(R.drawable.crown),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Box(modifier = Modifier.weight(1f).background(Color.Red)) {
                    Icon(
                        painter = painterResource(R.drawable.crown),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Box(modifier = Modifier.weight(2f).background(Color.Blue)) {
                    Icon(
                        painter = painterResource(R.drawable.crown),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Box(modifier = Modifier.weight(1f).background(Color.Yellow)) {
                    Icon(
                        painter = painterResource(R.drawable.crown),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }

        }
        */


    }



    /*
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
                         },
                    item = e,Modifier.padding(6.dp))
            }
        }


    }

    */

    PopUpNoLives(
        viewModel = viewmodel,
        show = opendialog.value,
        dimisissDialog =  {opendialog.value = false})
}