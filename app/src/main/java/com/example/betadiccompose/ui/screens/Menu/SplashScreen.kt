package com.example.betadiccompose.ui.screens.Menu

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation

@Preview
@Composable
fun SplashScreen() {
    Box(modifier = Modifier.background(Color.Red).fillMaxSize()){
        Local_Animation(animacion =  R.raw.dancing)

    }

}