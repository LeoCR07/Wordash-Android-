package com.example.authentication.ui.Foundation.Account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.ui.Foundation.Shared.MiniCross
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun itemWord(item: DataMyFavoriteWord, vocalview: VocabularyViewModel) {

    var hide by remember{ mutableStateOf(true) }
    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize by remember { mutableStateOf(12.sp) }

    if(hide){
        Card(
            modifier = Modifier
                .clickable {
                    vocalview.soundFromUrl(uri=item.sonido)
                }
                .size(160.dp)
                .padding(4.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = MaterialTheme.colors.onPrimary,
            border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
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

                    MiniCross(modifier = Modifier
                        .clickable {
                            hide = false
                            vocalview.deleteMyFavoriteWord(item.Img)
                        }
                        .padding(0.dp, 3.dp, 3.dp, 0.dp)
                        .align(alignment = Alignment.TopEnd))

                }

                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(55.dp)
                        .padding(6.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.World_1,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = MaterialTheme.colors.secondaryVariant,
                        onTextLayout = { textLayoutResult ->
                            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                                textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                            }
                        }
                    )
                    Text(
                        text = item.World_2,
                        maxLines = 1,
                        color =  MaterialTheme.colors.secondaryVariant,
                        onTextLayout = { textLayoutResult ->
                            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                                textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                            }
                        })
                }

            }
        }
    }
}

