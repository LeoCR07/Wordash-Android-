package com.example.betadiccompose.ui.Foundation.Shared


import androidx.compose.foundation.background
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
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*

@Composable
fun AnimationUrl(url: String, modifier: Modifier = Modifier,isplaying:Boolean = true){
    Box(
      /*  modifier = modifier
            .size(170.dp)
            .padding(0.dp),*/
        contentAlignment = Alignment.Center){

        val color by derivedStateOf { Color.LightGray }


        val disabled = "https://dicvocabulary.s3.us-east-2.amazonaws.com/Animaciones/disabled/0.json"

        val composition = rememberLottieComposition(LottieCompositionSpec.Url(url))

        val progress by animateLottieCompositionAsState(
            composition = composition.value,
            isPlaying = isplaying,
            iterations = LottieConstants.IterateForever
        )


        val dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                property = LottieProperty.OPACITY,
                keyPath = arrayOf("calendar"),
                 value = 10),

            rememberLottieDynamicProperty(
                    property = LottieProperty.COLOR,
                keyPath = arrayOf("calendar"),
            value = color.toArgb())

        )

        LottieAnimation(
            composition = composition.value,
            progress = progress,
            dynamicProperties = dynamicProperties,
            modifier = modifier
                .size(170.dp)
                .padding(0.dp))
    }
}