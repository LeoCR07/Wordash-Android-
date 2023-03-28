package com.example.authentication.ui.Foundation.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun HeaderAccount(vocalview: VocabularyViewModel) {


    LaunchedEffect(key1 = true){
        vocalview.getDataUser()
    }




    Box(modifier = Modifier
        .background(MaterialTheme.colors.onSecondary.copy(0.4f))
        .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))){

        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
                .height(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){


            Icon(
                painter = painterResource(id = R.drawable.account_on),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp),
                tint = Color.Unspecified)

            /*
            AsyncImage(
                model = UserPhoto,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape))*/


            Spacer(modifier = Modifier
                .height(5.dp))


            Text(
                text = "Hello, ${vocalview.lstdatauser.value.name}",
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                color = MaterialTheme.colors.secondaryVariant,
                text = vocalview.lstdatauser.value.email)

            Spacer(modifier = Modifier
                .height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ColumDetail(number = vocalview.lstdatauser.value.crowns, icon = R.drawable.crown )
                Vline()
                ColumDetail(number = vocalview.lstdatauser.value.level, icon = R.drawable.level )
                Vline()
                ColumDetail(number = vocalview.lstdatauser.value.exp, icon = R.drawable.exp)
                Vline()
                ColumDetail(number = vocalview.lstdatauser.value.lives, icon = R.drawable.heart)

            }

            Spacer(modifier = Modifier
                .height(15.dp))

            /*Button(onClick = { /*TODO*/ }) {
                Icon( Icons.Rounded.Save, contentDescription =  null)
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "Guardar")

            }*/

        }




    }

}