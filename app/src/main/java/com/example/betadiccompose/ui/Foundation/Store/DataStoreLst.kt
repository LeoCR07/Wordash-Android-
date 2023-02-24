package com.example.betadiccompose.ui.Foundation.Store

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

data class DataStoreLst(
    var txt:String,
    var textSize: TextUnit,
    var weight:FontWeight,
    var color:Color
)
