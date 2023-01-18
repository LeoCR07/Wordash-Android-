package com.example.betadiccompose.ui.Foundation.ScreenWorld

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network.model.DataWorld
import compose.material.theme.IconToggleButtonSample


@Composable
fun ThumbWorld(
    img: String = "null",
    modifier: Modifier = Modifier,
    add: () -> Unit,
    delete: () -> Unit,
    myfavorite: List<DataMyFavoriteWord>,
    item: DataWorld
) {

    var checked by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        for(e in myfavorite){
            if(item.Img== e.Img){
                checked = true
            }
        }
    }


    Box(
        modifier = modifier
            .height(110.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {

        AsyncImage(model = img,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null)


        Box(
            modifier = modifier
               // .clickable { }
                .height(110.dp)
                .fillMaxWidth(),

            contentAlignment = Alignment.TopEnd ){


            IconToggleButtonSample(
                Modifier
                    .height(55.dp),
                checked = checked,
                ClickCheck = {

                    checked = it
                    if(it){
                        add()
                    }else{
                        delete()
                    }

                }
            )
        }


    }

}