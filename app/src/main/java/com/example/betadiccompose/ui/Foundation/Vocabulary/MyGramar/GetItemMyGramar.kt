package com.example.betadiccompose.ui.Foundation.Vocabulary.MyGramar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample

@Composable
fun GetItemMyGramar(modifier: Modifier, item: DataMyFavoriteGramar, viewmodel:VocabularyViewModel) {

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize_1 by remember { mutableStateOf(25.sp) }
    var textSize_2 by remember { mutableStateOf(18.sp) }

    Card(
        modifier = modifier.clickable { /*onClick()*/ },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
    ) {


        Column(modifier = Modifier.padding(6.dp)) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = item.Gramar_1,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    color = MaterialTheme.colors.secondaryVariant,
                    fontSize = textSize_1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        //.background(MaterialTheme.colors.onSecondary)dp)
                        .height(35.dp)
                        .width(160.dp),
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResult ->
                        val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                        if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                            textSize_1 = textSize_1.times(TEXT_SCALE_REDUCTION_INTERVAL)
                        }
                    }
                )

                Text(
                    text = item.Gramar_2,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    color = MaterialTheme.colors.secondaryVariant,
                    fontSize = textSize_1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .height(35.dp)
                        .width(160.dp),
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResult ->
                        val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                        if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                            textSize_1 = textSize_1.times(TEXT_SCALE_REDUCTION_INTERVAL)
                        }
                    }
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){

                Icon(
                    painter = painterResource ( R.drawable.audio),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

            }

            Spacer(modifier = Modifier.height(5.dp))
            /*****************************/
            Text(
                text = item.Example_1,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.SemiBold,
                fontSize = textSize_2,
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(6.dp)
                    .height(50.dp),
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                    if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                        textSize_1 = textSize_1.times(TEXT_SCALE_REDUCTION_INTERVAL)
                    }
                }
            )
            Text(
                text = item.Example_2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = textSize_2,
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(6.dp)
                    .height(50.dp),
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                    if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                        textSize_1 = textSize_1.times(TEXT_SCALE_REDUCTION_INTERVAL)
                    }
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){

                Icon(
                    painter = painterResource ( R.drawable.audio),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.width(25.dp))

                Icon(
                    painter = painterResource ( R.drawable.snail__2_),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.width(25.dp))

                IconToggleButtonSample(
                    Modifier
                        .size(60.dp),
                    checked = true,
                    ClickCheck = {
                        viewmodel.deleteMyFavoriteGramar(item.Gramar_1)

                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }


    }
}