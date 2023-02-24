package com.example.betadiccompose.ui.Foundation.Game.Sonido

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ClickSonido(click: () -> Unit, value: Int, select: Int) {

    IconToggleButton(
        checked = value == select,
        onCheckedChange = {click()},
        modifier = Modifier
            .size(65.dp))
    {

        val tint by animateColorAsState(
            if (value == select) Color(0xFFEC407A) else Color(0xFFB0BEC5)
        )

        Icon(
            Icons.Rounded.VolumeUp,
            modifier = Modifier.size(150.dp),
            contentDescription = "Radio button icon",
            tint = tint
        )
    }

}
