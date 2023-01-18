package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedButtonSample(
    onMediaClick: () -> Unit,
    word: String,
    modifier: Modifier,
    BorderColor: Color,
    fontsize: TextUnit = 18.sp,
) {

    OutlinedButton(
        modifier = modifier,
        onClick = { onMediaClick() },
        border = BorderStroke(0.5.dp,BorderColor)
    )
     { Text(
         text = word,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
         fontSize = fontsize,
         color = Color.Black
     ) }


}