package com.example.betadiccompose.ui.Foundation.ScreenVocabulary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Foundation.ScreenVocabulary.Thumb
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.data.network.model.DataVocabulary

@Composable
fun GetItemVocabulary(onClick: () -> Unit, item: DataVocabulary, modifier: Modifier) {

    Card(
        modifier = modifier.clickable { onClick() },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        ) {

        Column() {
             //println(item.Img+"")
            Thumb(item.img )
            title(item.name,modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onSecondary)
                .padding(13.dp))
           // title(item.category_2,FontWeight.Normal)

        }

    }
}



