package com.example.betadiccompose.ui.Foundation.Library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.data.network_database.model.DataBooks
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun GetListLibrary (
    onMediaClick: (DataBooks) -> Unit,
    modifier: Modifier,
    viewmodel: VocabularyViewModel
) {
    val lst = viewmodel.lstBooks.value


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(0.dp,0.dp,0.dp,80.dp)){
        item {

            Text(
                text = "Rutina",
                modifier =Modifier
                    .padding(10.dp,13.dp,0.dp,1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold

            )

            LazyRow(modifier = Modifier.padding(6.dp)){

                lst.forEach {
                    if(it.cats == "Rutina"){

                        item {
                            GetItemLibrary(onClick =  {onMediaClick(it)},item = it, Modifier.padding(6.dp))
                        }

                    }
                }
            }

            Text(
                text = "Terror",
                modifier =Modifier
                    .padding(10.dp,13.dp,0.dp,1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold

            )

            LazyRow(modifier = Modifier.padding(6.dp)){
                lst.forEach {
                    if(it.cats == "Terror"){

                        item {
                            GetItemLibrary(onClick =  {onMediaClick(it)},item = it, Modifier.padding(6.dp))
                        }

                    }
                }
            }

            Text(
                text = "Aventura",
                modifier =Modifier
                    .padding(10.dp,13.dp,0.dp,1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold

            )

            LazyRow(modifier = Modifier.padding(6.dp)){
                lst.forEach {
                    if(it.cats == "Aventura"){

                        item {
                            GetItemLibrary(onClick =  {onMediaClick(it)},item = it, Modifier.padding(6.dp))
                        }

                    }
                }
            }

            Text(
                text = "Misterio",
                modifier =Modifier
                    .padding(10.dp,13.dp,0.dp,1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold

            )

            LazyRow(modifier = Modifier.padding(6.dp)){
                lst.forEach {
                    if(it.cats == "Misterio"){

                        item {
                            GetItemLibrary(onClick =  {onMediaClick(it)},item = it, Modifier.padding(6.dp))
                        }

                    }
                }
            }


            Text(
                text = "Ciencia Ficción",
                modifier =Modifier
                    .padding(10.dp,13.dp,0.dp,1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold

            )

            LazyRow(modifier = Modifier.padding(6.dp)){
                lst.forEach {
                    if(it.cats == "Ciencia ficción"){

                        item {
                            GetItemLibrary(onClick =  {onMediaClick(it)},item = it, Modifier.padding(6.dp))
                        }

                    }
                }
            }


            Text(
                text = "Fantancia",
                modifier =Modifier
                    .padding(10.dp,13.dp,0.dp,1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold

            )

            LazyRow(modifier = Modifier.padding(6.dp)){
                lst.forEach {
                    if(it.cats == "Fantancia"){

                        item {
                            GetItemLibrary(onClick =  {onMediaClick(it)},item = it, Modifier.padding(6.dp))
                        }

                    }
                }
            }
        }
    }


}


@Composable
internal fun lista(modifier: Modifier, onMediaClick: (DataVocabulary) -> Unit, lst: List<DataVocabulary>) {

}

