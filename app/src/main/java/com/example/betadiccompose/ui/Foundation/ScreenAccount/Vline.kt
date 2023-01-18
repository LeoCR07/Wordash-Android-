package com.example.authentication.ui.Foundation.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Vline() {
    Box(modifier = Modifier
        .width(1.dp)
        .height(40.dp)
        .background(Color.Black.copy(alpha = 0.4f)))
}
