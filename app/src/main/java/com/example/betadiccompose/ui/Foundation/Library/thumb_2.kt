package com.example.betadiccompose.ui.Foundation.Library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun Thumb_2(img:String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(170.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {

        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(img)
            .build(),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null)
    }
}