package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*


@Composable
fun Local_Animation(animacion:Int, modifier: Modifier = Modifier, speed :Float = 1f,isPlaying: Boolean = true) {

   val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animacion))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever)

}