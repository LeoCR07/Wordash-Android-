package com.example.betadiccompose.ui.Foundation.Shared.TopAppBar

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun topAppBarIcon(icon: Int, value: Int, codigo: Color) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Text(
            text = value.toString(),
            fontSize = 19.sp,
            fontWeight = FontWeight.Light,
            color = codigo
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            painter = painterResource (icon),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp),
            tint = Color.Unspecified
        )
    }

}