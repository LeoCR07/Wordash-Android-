package com.example.betadiccompose.ui.Foundation.Shared.Vocabulary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CircleProgress() {

    Box(modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator(
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.align(Alignment.Center))
    }

}