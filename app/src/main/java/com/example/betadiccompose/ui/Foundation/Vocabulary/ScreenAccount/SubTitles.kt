package com.example.authentication.ui.Foundation.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*

@Composable
fun SubTitles (
    text :String,
    click :()->Unit,
    vocalview:VocabularyViewModel
) {
    val interactionSource = remember { MutableInteractionSource() }

    var code by remember {
        mutableStateOf(vocalview.GetCode())
    }

    Spacer(modifier = Modifier.height(10.dp))
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Text(
            text = text,
            color = Color(0xFF304E83),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(10.dp,0.dp,0.dp))
        Text(
            text = vocalview.GetSettings().SeeMore[code]!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            color = Color(0xFF389AC7),
            modifier = Modifier
                .clickable(interactionSource = interactionSource,indication = null) { click() }
                .padding(0.dp,0.dp,10.dp))

    }

}