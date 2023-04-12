package com.example.betadiccompose.Foundation.ScreenVocabulary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun title(text: String, weight: FontWeight = FontWeight.Normal, modifier: Modifier, size:TextUnit = 15.sp
          , color : Color = MaterialTheme.colors.secondaryVariant) {

    var textSize = size

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Text(
            text = text,
        fontWeight = weight,
            color = color,
            fontSize = textSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            onTextLayout = { textLayoutResult ->
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            }
        )
    }

}