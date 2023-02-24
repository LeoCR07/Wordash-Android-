package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BtnSuper(
    title: String,
    color: Color = Color.White,
    icon: Int = 0,
    IsIcon: Boolean = true,
    Outline: Boolean = false,
    FontColor: Color,
    onClick: () -> Unit,
    IconLocal : Boolean = false,
    outlineColor: Color = Color.Black,
    modifier: Modifier = Modifier,
    fontSize :TextUnit = 12.sp
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.Unspecified
        ),

        border = if(Outline) BorderStroke(0.5.dp, outlineColor)  else null
    ){

        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically) {

            if(IsIcon){

                if(IconLocal){
                    Icon(
                        Icons.Rounded.Mic,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                        , tint = Color.White)

                    Spacer(modifier = Modifier.width(10.dp))
                }else{
                    Icon(
                        painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                }

            }

            Text(
                fontSize = fontSize,
                color = FontColor,
                text = "$title")
        }

    }
}
