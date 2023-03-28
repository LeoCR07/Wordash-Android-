package com.example.betadiccompose.ui.Foundation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.ui.theme.splash
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MySplash(content:@Composable () -> Unit) {
    splash(){
        val systemUiController = rememberSystemUiController()
        val col = MaterialTheme.colors.onPrimary

        SideEffect {
            systemUiController.setStatusBarColor(col)
            systemUiController.setSystemBarsColor(col)
        }


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Red
        ) {
            content()
        }


    }
}