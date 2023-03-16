package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSubMenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.data.network_database.model.DataSubMenu
import com.example.betadiccompose.Foundation.ScreenVocabulary.Thumb
import com.example.betadiccompose.Foundation.ScreenVocabulary.title

@Composable
fun ItemSubMenu(
    item: DataSubMenu,
    onclick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable (){ onclick() },

        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary))
        {
        Column() {
            Thumb(item.Img)
            title(
                item.name, modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                weight = FontWeight.Bold
            )
        }

    }


}