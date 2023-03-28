package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenAccount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LineColor(height:Float) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height.dp)
        .background(MaterialTheme.colors.onSecondary.copy(0.4f) ))
}