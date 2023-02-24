package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.WifiTetheringErrorRounded
import androidx.compose.material.icons.rounded.SignalWifiConnectedNoInternet4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R

@Preview
@Composable
fun NoIntenet() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)){
        Icon(
            Icons.Rounded.SignalWifiConnectedNoInternet4,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(60.dp),
            tint = Color.Unspecified
        )
    }

}