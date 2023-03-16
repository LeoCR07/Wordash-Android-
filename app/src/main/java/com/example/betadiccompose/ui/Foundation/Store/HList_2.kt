package com.example.betadiccompose.ui.Foundation.Store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper

@Composable
fun Hlist_2() {


    LazyRow(){

        items(6) {

            Card(
                modifier = Modifier
                    .size(200.dp)
                    .padding(6.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(6.dp),
                backgroundColor = MaterialTheme.colors.onPrimary,
                border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
            ) {

                Column() {

                    title(
                        "Pack 1",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(2.dp),
                        weight = FontWeight.SemiBold
                    )

                

                    Row(

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier =Modifier.fillMaxWidth()) {
                        title(
                            "500",
                            modifier = Modifier
                                .height(40.dp)
                                .padding(2.dp),
                            size = 30.sp,
                            weight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource (R.drawable.star_on),
                            contentDescription = null,
                            modifier = Modifier

                                .clickable {}
                                .size(60.dp),
                            tint = Color.Unspecified,
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(20.dp))

                    BtnSuper(
                        icon =  R.drawable.thunder,
                        title = "1000",
                        FontColor = Color.White ,
                        color = Color(0xFF2196F3),
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(8.dp))


                }


            }
        }
    }
}