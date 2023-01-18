package com.example.betadiccompose.ui.screens.Settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ItemSettings(
    icon:Int = 0,
    text:String = "ajustes",NavTo:()->Unit = {}) {
    Card(
        modifier = Modifier
            .clickable { NavTo() }
            .fillMaxWidth()
            .height(75.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F7F7),
        )) {

        Row(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Icon(
                painter = painterResource ( icon),
                contentDescription = "BTN",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                tint = Color.Unspecified)


            Text(
                modifier = Modifier
                    .padding(12.dp, 4.dp, 4.dp, 4.dp),
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }



    }
}