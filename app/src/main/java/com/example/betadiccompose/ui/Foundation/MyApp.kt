package com.example.betadiccompose.Runtime

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.betadiccompose.ui.theme.BetaDicComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
public fun MyApp(content:@Composable () -> Unit) {


    BetaDicComposeTheme() {

        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(color = Color.White)
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
        //    color = MaterialTheme.colors.secondary
        color = Color.Red
        ) {
            content()

        }
    }

}