package com.example.betadiccompose.ui.Foundation.ScreenWorld

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material.icons.twotone.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import compose.material.theme.IconToggleButtonSample

@Composable
fun ThumbWorld(img:String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(110.dp)
            .fillMaxWidth()
        ,
        contentAlignment = Alignment.Center

    ) {

        AsyncImage(model = img,
            modifier = Modifier
                .fillMaxSize(),

            contentScale = ContentScale.Crop,
            contentDescription = null)

        if(false){
            Box(
                contentAlignment = Alignment.Center
            ){
                Icon(
                    Icons.Rounded.VolumeUp,contentDescription = null,
                    modifier = Modifier
                        .size(30.dp), tint = Color.White)
            }
        }

        Box(
            modifier = modifier
                .height(110.dp)
                .fillMaxWidth()
            ,
            contentAlignment = Alignment.TopEnd ){
            IconToggleButtonSample(
                Modifier
                    .height(55.dp),
            )
        }


    }


}