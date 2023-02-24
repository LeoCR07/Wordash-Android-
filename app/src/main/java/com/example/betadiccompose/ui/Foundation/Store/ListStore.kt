package com.example.betadiccompose.ui.Foundation.Store

import android.content.ClipData.Item
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper


@Preview(showBackground = true)
@Composable
fun ListStrore() {


    var textSize_1 by remember { mutableStateOf(25.sp) }
    var textSize_2 by remember { mutableStateOf(18.sp) }


    val lst_btn_1 =listOf(
        DataStoreLst(
            txt = "Recarga tus vidas",
            textSize = textSize_1,
            weight = FontWeight.SemiBold,
            color = MaterialTheme.colors.secondaryVariant
        ),
        DataStoreLst(
            txt = "Proxima vida en 20:40",
            textSize = textSize_2,
            weight = FontWeight.Normal,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        )
    )


    val lst_btn_2 =listOf(
        DataStoreLst(
            txt = "Deja nos tu comentario",
            textSize = textSize_1,
            weight = FontWeight.SemiBold,
            color = MaterialTheme.colors.secondaryVariant
        ),
        DataStoreLst(
            txt = "Gana 25 exp y 25 estrellas",
            textSize = textSize_2,
            weight = FontWeight.Normal,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        )
    )

    val lst_btn_3 =listOf(
        DataStoreLst(
            txt = "Haste premium",
            textSize = textSize_1,
            weight = FontWeight.SemiBold,
            color = MaterialTheme.colors.secondaryVariant
        ),
        DataStoreLst(
            txt = "No mas anuncios",
            textSize = textSize_2,
            weight = FontWeight.Normal,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        ),
        DataStoreLst(
            txt = "Vidas infinitas",
            textSize = textSize_2,
            weight = FontWeight.Normal,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        ),
        DataStoreLst(
            txt = "Acceso libre al contenido",
            textSize = textSize_2,
            weight = FontWeight.Normal,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        ), DataStoreLst(
            txt = "Palabras favoritos sin limite",
            textSize = textSize_2,
            weight = FontWeight.Normal,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        ),
    )


    LaunchedEffect(key1 = true){

    }

    Column() {



        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            contentPadding = PaddingValues(8.dp,0.dp,8.dp,80.dp)){

            item{

                Spacer(modifier = Modifier.height(10.dp))

                ItemStore_1(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .padding(4.dp) ,
                    icon = R.drawable.diamond,
                    lst = lst_btn_3,
                    titleBtn = "3700")

                Spacer(modifier = Modifier.height(10.dp))
                Hlist_1(lst_btn_1,lst_btn_2)
                Spacer(modifier = Modifier.height(10.dp))

                Hlist_2()
                Spacer(modifier = Modifier.height(10.dp))

            }



        }
    }


}
