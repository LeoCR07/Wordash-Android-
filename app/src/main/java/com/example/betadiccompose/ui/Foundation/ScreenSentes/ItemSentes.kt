package com.example.betadiccompose.ui.Foundation.ScreenSentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.model.DataSentes
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataWorld
import compose.material.theme.IconToggleButtonSample


@Composable
fun ItemSentes(modifier: Modifier, item: DataWorld) {

    Card(
        modifier = modifier.clickable { /*onClick()*/ },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.5.dp, Color.LightGray)
    ) {

        Box(
            modifier = modifier
                // .clickable { }
                .height(110.dp)
                .fillMaxWidth(),

            contentAlignment = Alignment.TopEnd ){


            IconToggleButtonSample(
                Modifier
                    .height(55.dp),
                checked = false,
                ClickCheck = {

                    /*
                    checked = it
                    if(it){
                        add()
                    }else{
                        delete()
                    }
                    */

                }
            )
        }

        Column() {

            Spacer(modifier = Modifier.height(40.dp))


            Text(
                text = item.World_1,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(4.dp)
                    .height(35.dp)
            )
            Text(
                text = item.World_2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(4.dp)
                    .height(35.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){

                Icon(
                    painter = painterResource ( R.drawable.audio),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(20.dp))

                Icon(
                    painter = painterResource ( R.drawable.snail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint =  Color.Unspecified,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

        }


    }
}