package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ElevatedButtonAsocie(onMediaClick: () -> Unit, word: String, modifier: Modifier = Modifier) {

    val containerColor = 0xFFF8D39D
    val containerColorSelect = 0xFFF3B356

    var selected by remember { mutableStateOf(false) }
    var BtnColor  =  if (selected) 0xFFF3B356 else 0xFFF8D39D

        ElevatedButton(
        modifier = modifier,
        onClick = {
                  selected = true
        },
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = Color(0xFFFFFFFF),
            containerColor = Color(BtnColor)
            ))

    { Text(text = word,
        style = MaterialTheme.typography.labelLarge) }
}