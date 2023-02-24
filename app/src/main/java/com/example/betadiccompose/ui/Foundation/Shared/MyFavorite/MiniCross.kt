package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiniCross(modifier:Modifier) {
    Box(
        modifier = modifier,
    ) {
        Icon(
            Icons.Default.Circle,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
            tint = Color.White
        )

        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp),
                tint = Color.Red
            )
        }

    }

}