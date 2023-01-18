package com.example.betadiccompose.ui.Foundation.Shared.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun BackgroundImg(url:String) {

    Box(
        modifier = Modifier
            .height(220.dp)
            .width(280.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center

    ) {

        AsyncImage(
            model = url,
            modifier = Modifier
                .fillMaxSize(),

            contentScale = ContentScale.Crop,
            contentDescription = null
        )


    }

}