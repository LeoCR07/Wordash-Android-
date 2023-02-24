package com.example.betadiccompose.ui.Foundation.Library

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.betadiccompose.Foundation.ScreenVocabulary.Thumb
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataBooks
import com.example.betadiccompose.data.network_database.model.DataVocabulary

@Composable
fun GetItemLibrary(
    onClick: () -> Unit,
    item: DataBooks,
    modifier: Modifier
) {

    Card(
        modifier = modifier
            .width(180.dp)
            .clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
     //   backgroundColor = MaterialTheme.colors.onPrimary,
      //  border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
    ) {

        Column() {
            
            Thumb_2(
                img = item.img)

            title(
                item.name,modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                size = 18.sp,
                weight = FontWeight.Normal
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {

                title("${item.time} /S",modifier = Modifier
                    .padding(8.dp),
                    weight = FontWeight.Normal
                )

                Box(){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center){

                        title(item.stars.toString(),modifier = Modifier
                            .padding(8.dp),
                            weight = FontWeight.Light
                        )
                        Icon(
                            painter = painterResource (  R.drawable.star_on),
                            contentDescription = "BTN",
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape),
                            tint = Color.Unspecified)
                    }
                }



            }



        }
    }
}

