package com.example.betadiccompose.ui.Foundation.Shared


import android.graphics.Color.toArgb
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*

@Composable
fun Url_Animation(url: String, modifier: Modifier = Modifier, isplaying:Boolean = true){
    Box(
        contentAlignment = Alignment.Center){

       //val color by derivedStateOf { Color.LightGray }


        val disabled = "https://dicvocabulary.s3.us-east-2.amazonaws.com/Animaciones/disabled/0.json"

        val composition = rememberLottieComposition(LottieCompositionSpec.Url(url))

        val progress by animateLottieCompositionAsState(

            composition = composition.value,
            isPlaying = isplaying,
            iterations = LottieConstants.IterateForever,
            speed = 0.5f
        )



        LottieAnimation(
            composition = composition.value,
            progress = progress,
            modifier = modifier
                .size(160.dp)
                .padding(0.dp))
    }
}