package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.data.network.model.DataWorld


@Composable
fun ElevatedButtonSample(onMediaClick: () -> Unit, word: String, modifier: Modifier =Modifier) {
    ElevatedButton(
        modifier = modifier,
        onClick = { onMediaClick() })
    { Text(
        text = word,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        style = MaterialTheme.typography.labelLarge) }
}