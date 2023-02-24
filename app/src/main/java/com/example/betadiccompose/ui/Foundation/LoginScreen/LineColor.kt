package com.example.authentication.ui.Presentation.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LineColor(height:Float) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height.dp)
        .background(Color(0xFFE7E7E7)))
}