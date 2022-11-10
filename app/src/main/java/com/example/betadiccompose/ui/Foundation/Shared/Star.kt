package com.example.betadiccompose.ui.Foundation.Shared

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.betadiccompose.Foundation.ScreenVocabulary.title

@Composable
fun Star(animacion:Int,modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center){

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animacion))
        LottieAnimation(
            isPlaying = false,
            composition = composition,
            iterations = LottieConstants.IterateForever)
    }
}