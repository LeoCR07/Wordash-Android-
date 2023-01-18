package com.example.betadiccompose.ui.Foundation.Shared.Button

import android.view.MotionEvent


import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter

@Composable
fun FilledTonalButtonAsocie(onMediaClick: () -> Unit, word: String, modifier: Modifier) {

    var selected by remember { mutableStateOf(false) }
    var BtnColor  =  if (selected) 0xFF0466B3 else 0xFF7DC2F8

    //var BtnColor = 0xFF0466B3
    FilledTonalButton(
        modifier = modifier,
        onClick = {
            selected = !selected
                  },
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = Color(0xFF0466B3),
            containerColor = Color(BtnColor)
        )) {
        Text(
            text = word,
            style = MaterialTheme.typography.labelLarge) }
}