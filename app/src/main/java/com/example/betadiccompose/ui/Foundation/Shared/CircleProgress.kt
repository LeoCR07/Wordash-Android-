package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.R

@Preview
@Composable
fun CircleProgress() {
    CircularProgressIndicator(color = Color.Blue)
}