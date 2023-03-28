package com.example.betadiccompose.ui.Foundation.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.Foundation.Shared.LabelledRadioButton


@Composable
fun ItemOpcion(label:String, value:String, click:()->Unit, selected:String) {

    LabelledRadioButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colors.onSecondary.copy(0.4f)),
        label = label,
        selected = selected,
        onClick = {
            click()
        },
        value = value,
        colors = RadioButtonDefaults.colors(
            unselectedColor = Color.Gray,
            selectedColor = MaterialTheme.colors.onSurface,
            disabledColor = Color.Red
        ))


}