package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun OutlinedButtonSample(
    onMediaClick: () -> Unit,
    word: String,
    modifier: Modifier,
    BorderColor: Color,
    fontsize: TextUnit = 18.sp,
) {

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

    var textSize by remember {
        mutableStateOf(fontsize)
    }


    OutlinedButton(
        modifier = modifier,
        onClick = { onMediaClick() },
        border = BorderStroke(0.5.dp,BorderColor)
    )
    { Text(
        text = word,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Normal,
        fontSize = fontsize,
        textAlign = TextAlign.Center,
        color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
        maxLines = 2,
        onTextLayout = { textLayoutResult ->
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
            }
        }
    )}



}