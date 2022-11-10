package com.example.betadiccompose.Runtime

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.betadiccompose.ui.theme.BetaDicComposeTheme

@Composable
public fun MyApp(content:@Composable () -> Unit) {

    BetaDicComposeTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.secondary
        ) {
            content()

        }
    }

}