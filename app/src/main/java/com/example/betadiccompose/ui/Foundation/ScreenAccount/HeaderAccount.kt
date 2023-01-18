package com.example.authentication.ui.Foundation.Account

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.betadiccompose.R

@Composable
fun HeaderAccount(
    UserName:String,
    UserEmail:String,
    UserPhoto: Uri?,
    Stars: Int,
    Level:Int,
    Exp:Int) {

    Box(modifier = Modifier
        .background(Color(0xFFE7E7E7))
        .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))){

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){


            AsyncImage(
                model = UserPhoto,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape))


            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Hello, $UserName",
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = "$UserEmail")

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ColumDetail(number = Stars, icon = R.drawable.star_on )
                Vline()
                ColumDetail(number = 4, icon = R.drawable.level )
                Vline()
                ColumDetail(number = 1000, icon = R.drawable.exp)

            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = { /*TODO*/ }) {
                Icon( Icons.Rounded.Save, contentDescription =  null)
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "Guardar")

            }
        }




    }

}