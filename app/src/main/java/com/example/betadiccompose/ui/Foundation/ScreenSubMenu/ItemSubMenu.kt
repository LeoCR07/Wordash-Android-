package com.example.betadiccompose.ui.Foundation.ScreenSubMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.betadiccompose.Domain.model.DataSubMenu
import com.example.betadiccompose.Foundation.ScreenVocabulary.Thumb
import com.example.betadiccompose.Foundation.ScreenVocabulary.title

@Composable
fun ItemSubMenu(item: DataSubMenu, onclick: () -> Unit) {

    Card(
        modifier = Modifier
            .clickable { onclick() }
            .padding(6.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column() {
            Thumb(item.Img)
            title(
                item.name, modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.onSecondary)
                    .padding(13.dp)
            )
        }

    }


}