package com.example.authentication.ui.Foundation.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.ui.Foundation.Shared.Sonido
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun itemWord(item: DataMyFavoriteWord, vocalview: VocabularyViewModel) {

    var hide by remember{ mutableStateOf(true) }

    if(hide){
        Card(
            modifier = Modifier
                .clickable {
                    Sonido(url = item.sonido,vocalview = vocalview)
                    println("Audio")
                }
                .size(150.dp)
                .padding(4.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(12.dp)

        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(Color.Red)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        model = item.Img,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,

                    )

                    Box(
                        modifier = Modifier
                            .clickable {
                                hide = false
                                vocalview.DeleteMyFavoriteWord(item.Img)
                            }
                            // println("Eliminar de favoritos") }
                            .align(alignment = Alignment.TopEnd)
                            .padding(0.dp, 3.dp, 3.dp, 0.dp),
                    ) {
                        androidx.compose.material.Icon(
                            Icons.Default.Circle,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp),
                            tint = Color.White
                        )

                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                        ) {
                            androidx.compose.material.Icon(
                                Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp),
                                tint = Color.Red
                            )
                        }

                    }

                }

                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(55.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = item.World_1)
                    Text(text = item.World_2)
                }

            }
        }
    }
}

