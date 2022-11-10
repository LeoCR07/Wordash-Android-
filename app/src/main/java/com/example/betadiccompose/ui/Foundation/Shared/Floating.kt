package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Floating() {
    FloatingActionButton(
        modifier = Modifier
            .size(75.dp),
        onClick = { /* ... */ }) {
        /* FAB content */
        Icon(
            Icons.Outlined.Leaderboard,contentDescription = null,
            modifier = Modifier
                .size(30.dp), tint = Color.White)
    }
}