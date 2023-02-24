package com.example.authentication.ui.Presentation.Login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BtnLogin(
    title: String,
    color: Color = Color.White,
    icon: Int = 0,
    IsIcon: Boolean = true,
    Outline: Boolean = false,
    FontColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .height(50.dp)
            //.width(300.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .padding(15.dp,0.dp,15.dp,0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.Unspecified
        ),

        border = if(Outline) BorderStroke(0.5.dp, Color.Black)  else null
    ){

        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically) {

            if(IsIcon){
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp))
                Spacer(modifier = Modifier.width(10.dp))
            }

            Text(
                color = FontColor,
                text = "$title")
        }

    }
}
