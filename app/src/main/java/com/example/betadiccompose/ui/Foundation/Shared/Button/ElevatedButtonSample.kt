package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ElevatedButtonSample(onMediaClick: () -> Unit, word: String, modifier: Modifier =Modifier) {

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

    var textSize by remember {
        mutableStateOf(18.sp)
    }

    ElevatedButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = androidx.compose.material.MaterialTheme.colors.onSecondary,
            contentColor = Color.Unspecified
        ),
        onClick = { onMediaClick() })
    {
        Text(
            text = word,
            fontWeight = FontWeight.Normal,
            fontSize = textSize,
            textAlign = TextAlign.Center,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 2,
            onTextLayout = { textLayoutResult ->
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            }
            )}
}