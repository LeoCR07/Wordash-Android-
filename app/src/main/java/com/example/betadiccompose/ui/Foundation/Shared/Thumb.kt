package com.example.betadiccompose.Foundation.ScreenVocabulary

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataVocabulary

@Composable
fun Thumb(img:String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
        .height(130.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {

        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(img)
            //.placeholder(R.drawable.ic_baseline_image_24)
            .build(),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null)
    }
}