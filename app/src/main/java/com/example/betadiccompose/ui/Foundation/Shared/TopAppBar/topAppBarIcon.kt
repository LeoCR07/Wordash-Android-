package com.example.betadiccompose.ui.Foundation.Shared.TopAppBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun topAppBarIcon(icon:Int,value :Int) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Text(
            text = value.toString(),
            fontSize = 20.sp,
            color = MaterialTheme.colors.onBackground,
        )
        Icon(
            painter = painterResource (icon),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp),
            tint = Color.Unspecified
        )
    }

}