package com.example.betadiccompose.Foundation.ScreenMenu

import androidx.compose.foundation.layout.*


import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.material.theme.IconToggleButtonSample


//@Preview(showBackground = true)
@Composable
fun GetLogo (icon:Boolean,titulo:String) {
    Icons.Default.Android
        Row(
            modifier = Modifier
                .padding(6.dp,6.dp,6.dp,0.dp)
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.Start
       ) {

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .align(Alignment.Bottom)
                    .weight(1f)
            ){
                Icon(
                    Icons.Default.Clear,contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(30.dp))
            }



            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .align(Alignment.Bottom)
                    .weight(1f)
            ){
               // IconToggleButtonSample(myfavorite = myfavorite, item = item)
            }



        }


    }



