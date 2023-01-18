package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ButtonSample(item: String, modifier: Modifier, onClick: () -> Unit) {
    val BtnColor = 0xFFAB47BC

    Button(
        modifier =  modifier
            .clip(RoundedCornerShape(6.dp)),
        onClick = { onClick()}
        )
    { Text(
        item,
        style = MaterialTheme.typography.labelLarge,
        fontSize =  19.sp,)}
}