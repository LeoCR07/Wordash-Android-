package com.example.betadiccompose.ui.Foundation.Store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper


@Composable
fun ItemStore_1(modifier: Modifier,icon:Int,isIcon: Boolean = false ,lst:List<DataStoreLst>,titleBtn:String) {


    Card(modifier = modifier,
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Box(modifier = Modifier
                .weight(3f)
                .fillMaxHeight()){
                Icon(
                    painter = painterResource ( icon),
                    contentDescription = "BTN",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    tint = Color.Unspecified)
            }
            Column(modifier = Modifier
                .weight(7f)
                .fillMaxHeight()
                .padding(1.dp, 0.dp, 6.dp, 4.dp)){

                for(e in lst){
                    TextStore(
                        txt = e.txt,
                        textSize = e.textSize,
                        weight = e.weight,
                        color = e.color)
                }


                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)){

                    BtnSuper(
                        icon = if(isIcon) R.drawable.thunder else  R.drawable.thunder,
                        title = titleBtn,
                        FontColor = Color.White ,
                        color = Color(0xFF2196F3),
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp))
                }
            }


        }
    }
}