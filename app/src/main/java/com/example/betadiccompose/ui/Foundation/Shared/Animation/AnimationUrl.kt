package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AnimationUrl(url: String, modifier: Modifier = Modifier,isplaying:Boolean = true){
    Box(

        modifier = modifier
            .size(170.dp)
            .padding(0.dp)
        ,
          //  .background(Color.Green),}
        contentAlignment = Alignment.Center){

        val composition by rememberLottieComposition(LottieCompositionSpec.Url(url))


        LottieAnimation(
            composition = composition,
            isPlaying = isplaying,
            iterations = LottieConstants.IterateForever)


    }


}