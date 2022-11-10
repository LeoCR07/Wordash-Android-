package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.betadiccompose.data.network.model.DataWorld

@Composable
fun OutlinedButtonSample(onMediaClick: () -> Unit, lsteasy: DataWorld, modifier: Modifier) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onMediaClick() })
    { Text(lsteasy.World_1,
        style = MaterialTheme.typography.labelLarge) }


}