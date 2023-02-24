package com.example.betadiccompose.ui.Foundation.Store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R


@Composable
fun Hlist_1(lst_btn_1:List<DataStoreLst>,lst_btn_2:List<DataStoreLst>) {


    LazyRow(){

        item{
            ItemStore_1(
                modifier = Modifier
                    .width(370.dp)
                    .height(180.dp)
                    .padding(3.dp),
                isIcon = true,
                icon = R.drawable.healthcare,
                lst = lst_btn_1,
                titleBtn = "Ver anuncio"
            )

            //Spacer(modifier = Modifier.weight(10.dp))

            ItemStore_1(
                modifier = Modifier
                    .width(370.dp)
                    .height(180.dp)
                    .padding(4.dp),
                icon = R.drawable.comments,
                lst = lst_btn_2,
                titleBtn = "Comentar"
            )
        }
    }
}