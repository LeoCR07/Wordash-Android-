package com.example.betadiccompose.ui.Foundation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.example.betadiccompose.ui.theme.BetaDicComposeTheme
import com.example.betadiccompose.ui.theme.Starting
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
public fun MyThemeSplash(content:@Composable () -> Unit) {


    Starting() {
        val systemUiController = rememberSystemUiController()
        val col = MaterialTheme.colors.onPrimary

        SideEffect {
            systemUiController.setStatusBarColor(col)
            systemUiController.setSystemBarsColor(col)

        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()

        }
    }
}