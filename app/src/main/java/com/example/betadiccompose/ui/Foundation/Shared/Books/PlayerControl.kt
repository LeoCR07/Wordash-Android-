package com.example.betadiccompose.ui.Foundation.Shared.Books

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R

@Composable
fun PlayerControls(modifier: Modifier = Modifier) {
    //black overlay across the video player
    Box(modifier = modifier.background(Color.Black.copy(alpha = 0.6f))) {
        Row(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //replay button
            IconButton(modifier = Modifier.size(40.dp), onClick = {}) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id =  R.drawable.pause),
                    contentDescription = "Replay 5 seconds"
                )
            }

            //pause/play toggle button
            IconButton(modifier = Modifier.size(40.dp), onClick = {}) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id =R.drawable.pause),
                    contentDescription = "Play/Pause"
                )
            }

            //forward button
            IconButton(modifier = Modifier.size(40.dp), onClick = {}) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id =  R.drawable.pause),
                    contentDescription = "Forward 10 seconds"
                )
            }
        }
    }
}