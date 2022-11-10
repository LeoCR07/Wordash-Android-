package com.example.betadiccompose.Foundation.Menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.data.network.model.DataMenu

@Composable
fun GetItemMenu(onClick: () -> Unit, item: DataMenu) {

    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .clickable { onClick() },
        elevation = 8.dp,

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // .background(Color.Green)
                .height(140.dp),
        ) {

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
                //       .background(Color.LightGray)
            ){
                Icon(
                    Icons.Default.Build, contentDescription = null,
                    modifier = Modifier.size(42.dp))
            }

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    // .background(MaterialTheme.colors.secondary)
                    .padding(2.dp)

            ){
                Text(text = item.Menu_1,
                    fontSize = 23.sp)
            }


            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    // .background(MaterialTheme.colors.secondary)
                    .padding(2.dp)

            ){
                Text(text = item.Menu_1,
                    fontSize = 23.sp)
            }

        }
    }


}
