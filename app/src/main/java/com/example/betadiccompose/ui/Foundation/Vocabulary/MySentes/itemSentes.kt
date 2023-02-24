package com.example.betadiccompose.ui.Foundation.Vocabulary.MySentes

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes
import com.example.betadiccompose.ui.Foundation.Shared.MiniCross
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun itemSentes(item: DataMyFavoriteSentes, vocalview: VocabularyViewModel) {

    var hide by remember{ mutableStateOf(true) }

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize by remember { mutableStateOf(12.sp) }

    if(hide){
        Card(
            modifier = Modifier
                .clickable {
                    println("Audio")
                }
                .width(170.dp)
                .height(140.dp)
                .padding(2.dp)
          ,
            elevation = 4.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = MaterialTheme.colors.onPrimary,
            border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)

        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ){
                    MiniCross(modifier = Modifier
                        .clickable {
                            hide = false
                            // vocalview.DeleteMyFavoriteWord(item.Img)
                        }
                        .padding(0.dp, 3.dp, 3.dp, 0.dp)
                        .align(alignment = Alignment.TopEnd)
                    )
                }

                Text(
                    text = item.Sentes_1,
                    fontSize = textSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    color = MaterialTheme.colors.secondaryVariant,
                    onTextLayout = { textLayoutResult ->
                        val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                        if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                            textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                        }
                    }
                )
                Text(
                    text = item.Sentes_2,
                    textAlign = TextAlign.Center,
                    fontSize = textSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    color = MaterialTheme.colors.secondaryVariant,
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