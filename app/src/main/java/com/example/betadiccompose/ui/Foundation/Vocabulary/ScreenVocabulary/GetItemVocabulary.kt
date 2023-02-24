package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenVocabulary


import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Foundation.ScreenVocabulary.Thumb
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import kotlin.random.Random

@Composable
fun GetItemVocabulary(
    onClick: () -> Unit,
    item: DataVocabulary,
    modifier: Modifier) {

    Card(
        modifier = modifier
            .clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor =MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
        ) {

        Column() {
            Thumb(item.img )
            title(item.name,modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
            weight = FontWeight.Bold
            )
        }
    }
}



