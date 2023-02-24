package com.example.betadiccompose.ui.Foundation.Store

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun TextStore(txt: String, textSize: TextUnit, weight: FontWeight, color: Color) {

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize_1 by remember { mutableStateOf(textSize) }


    Text(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        overflow = TextOverflow.Ellipsis,
        color = color,
        fontWeight = weight,
        text = txt,
        fontSize = textSize_1,
        textAlign = TextAlign.Center,
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.LastLineBottom
                )
            )
        ),
        maxLines =4,
        onTextLayout = { textLayoutResult ->
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize_1 = textSize_1.times(TEXT_SCALE_REDUCTION_INTERVAL)
            }
        })

}