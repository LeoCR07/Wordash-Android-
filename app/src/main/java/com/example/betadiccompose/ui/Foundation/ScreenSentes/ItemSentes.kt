package com.example.betadiccompose.ui.Foundation.ScreenSentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.model.DataSentes
import com.example.betadiccompose.Foundation.ScreenVocabulary.title


@Composable
fun ItemSentes(modifier: Modifier, item: DataSentes) {

    Card(
        modifier = modifier.clickable { /*onClick()*/ },
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
    ) {

        Column() {

            title(
                text = item.sentes_1,
                modifier = Modifier
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(4.dp)
                    .height(60.dp)
            )
            title(
                text = item.sentes_2,
                modifier=
                Modifier
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(4.dp)
                    .height(60.dp)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                   // .background(MaterialTheme.colors.onSecondary)
                    .fillMaxWidth()
            ){
                Icon(
                    Icons.Rounded.VolumeUp,contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(bottom = 5.dp)
                    )
            }

        }


    }
}