package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.example.betadiccompose.R


@Composable
fun Animation(animacion:Int,modifier: Modifier = Modifier,speed :Float = 1f) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animacion))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        speed = speed,
        iterations = LottieConstants.IterateForever)

}