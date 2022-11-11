package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.network.model.DataWorld


@Composable
fun ButtonWithIconSample(modifier: Modifier = Modifier, onMediaClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onMediaClick() }) {
        Icon(
            Icons.Filled.ThumbUp,
            contentDescription = "Localized description",
            modifier = Modifier.size(40.dp)
        )
       // Spacer(Modifier.size(ButtonDefaults.IconSpacing))
       // Text("ok", style = MaterialTheme.typography.labelLarge)
    }
}