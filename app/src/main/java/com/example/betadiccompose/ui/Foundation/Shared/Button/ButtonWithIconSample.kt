package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonWithIconSample(modifier: Modifier = Modifier, onMediaClick: () -> Unit) {

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF23A4DF),
            contentColor = Color.Unspecified
        ),
        onClick = { onMediaClick() }) {
        Icon(
            Icons.Filled.ThumbUp,
            contentDescription = "Localized description",
            modifier = Modifier.size(40.dp),
            tint = androidx.compose.material.MaterialTheme.colors.background
        )
    }
}