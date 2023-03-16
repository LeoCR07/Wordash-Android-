package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun customProgressBar(porcentaje: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var progress = porcentaje

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .height(20.dp)
                .background(MaterialTheme.colors.secondary)
                .width(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .height(20.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFF4CAF50),
                                Color(0xFF8BC34A),
                                Color(0xFFCDDC39)
                            )
                        )
                    )
                    .width(300.dp * progress / 100)
            )

        }

    }

}