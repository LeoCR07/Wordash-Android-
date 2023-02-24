package com.example.betadiccompose.ui.Foundation.Shared.MyFavorite

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation

@Preview
@Composable
fun EmptyLst() {
    Box(modifier = Modifier
        .width(340.dp)
        .height(140.dp)
        .padding(2.dp)){

        Row(modifier = Modifier
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Local_Animation(
                animacion = R.raw.nofound,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp))
        }
    }
}