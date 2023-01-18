package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.foundation.background
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.data.network.model.DataWorld


@Composable
fun FilledTonalButtonSample(onMediaClick: () -> Unit, word: String, modifier: Modifier) {

    FilledTonalButton(
        modifier = modifier,
        onClick = { onMediaClick()}) {
        Text(
            text = word,
            style = MaterialTheme.typography.labelLarge) }
}