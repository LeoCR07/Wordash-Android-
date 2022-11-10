package com.example.betadiccompose.ui.Foundation.ScreenWorld

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.data.network.model.DataWorld
import compose.material.theme.IconToggleButtonSample

@Composable
fun GetItemWorld(onClick: () -> Unit, item: DataWorld, modifier: Modifier) {

    Card(
        modifier = modifier.clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(6.dp)
    ) {

        Column() {
            // println(item.Img+"")
            ThumbWorld(item.Img)

            Row() {
                Column() {
                    title(
                        item.World_1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.onSecondary)
                            .padding(2.dp)
                    )
                    title(
                        item.World_2,
                        modifier=
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.onSecondary)
                            .padding(2.dp)
                    )
                }
            }




        }


    }
}
