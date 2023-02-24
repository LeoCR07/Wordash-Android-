package com.example.betadiccompose.ui.Foundation.Shared.Store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MusicBar(porcentaje: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var progress = porcentaje

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .height(7.dp)
                .padding(0.dp,0.dp,0.dp)
                .background(Color.LightGray.copy(alpha = 0.4f))
                .width(350.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .height(20.dp)
                    .background(
                        Color.White
                    )
                    .width(350.dp * progress / 100)
            )

        }

    }

}