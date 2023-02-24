package com.example.betadiccompose.ui.Foundation.Vocabulary.MyGramar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar
import com.example.betadiccompose.ui.Foundation.Shared.MiniCross
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun itemGramar(item: DataMyFavoriteGramar, vocalview: VocabularyViewModel) {

    var hide by remember{ mutableStateOf(true) }
    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize by remember { mutableStateOf(18.sp) }

    if(hide){
        Card(
            modifier = Modifier
                .clickable {
                    //Sonido(url = item.sonido,vocalview = vocalview)
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

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        text = item.Gramar_1,
                        color = MaterialTheme.colors.secondaryVariant,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = textSize,
                        style = LocalTextStyle.current.merge(
                            TextStyle(
                                lineHeight = 2.5.em,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                ),
                                lineHeightStyle = LineHeightStyle(
                                    alignment = LineHeightStyle.Alignment.Center,
                                    trim = LineHeightStyle.Trim.LastLineBottom
                                )
                            )
                        ),
                        maxLines = 1,
                        onTextLayout = { textLayoutResult ->
                            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                                textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                            }
                        })


                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .align(Alignment.End),
                        text = item.Gramar_2,
                        color = MaterialTheme.colors.secondaryVariant,
                        textAlign = TextAlign.Center,
                        fontSize = textSize,
                        style = LocalTextStyle.current.merge(
                            TextStyle(
                                lineHeight = 2.5.em,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                ),
                                lineHeightStyle = LineHeightStyle(
                                    alignment = LineHeightStyle.Alignment.Center,
                                    trim = LineHeightStyle.Trim.LastLineBottom
                                )
                            )
                        ),
                        maxLines = 1,
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