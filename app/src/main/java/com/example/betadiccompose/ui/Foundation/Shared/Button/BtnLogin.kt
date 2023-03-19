package com.example.betadiccompose.ui.Foundation.Shared.Button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R


@Composable
fun BtnLogin(
    isIcon:Boolean = true,
    icono :Int,
    text:String,
    OnClick:()->Unit ) {
    Button(
        modifier = Modifier
            .width(300.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(BorderStroke(0.5.dp, Color.LightGray), RoundedCornerShape(20.dp)),
        onClick = { OnClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = Color.Unspecified
        )
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {

            if(isIcon){
                Icon(
                    painter = painterResource(icono),
                    contentDescription = "BTN",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .weight(2f),
                    tint = Color.Unspecified)
            }

            Spacer(modifier = Modifier
                .width(10.dp)
                .weight(0.1f))

            Text(text = text,modifier = Modifier.weight(6f))

        }


    }
}