package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ButtonSample() {
    Button(onClick = { /* Do something! */ })
    { Text("Button Sample", style = MaterialTheme.typography.labelLarge) }
}