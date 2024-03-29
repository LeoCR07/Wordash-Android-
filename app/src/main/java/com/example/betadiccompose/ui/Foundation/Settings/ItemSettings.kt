package com.example.betadiccompose.ui.screens.Settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.ui.Foundation.Shared.ExitDialog


@Composable
fun ItemSettings(
    icon:Int = 0,
    text:String ,
    NavTo:()->Unit = {}) {


    Card(
        modifier = Modifier
            .clickable { NavTo() }
            .fillMaxWidth()
            .height(75.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.onSecondary.copy(0.4f),
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
                    .size(25.dp),
                tint = Color.Unspecified)


            Text(
                modifier = Modifier
                    .padding(12.dp, 4.dp, 4.dp, 4.dp),
                text = text,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
        }



    }
}