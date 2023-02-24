package com.example.betadiccompose.ui.Foundation.Vocabulary.MySentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample


@Composable
fun GetItemMySentes(
    modifier: Modifier,
    item: DataMyFavoriteSentes,
    viewmodel: VocabularyViewModel,
) {

    viewmodel.getMyFavoriteSentes()
    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize by remember { mutableStateOf(20.sp) }
    var textSize_1 by remember { mutableStateOf(17.sp) }

    var checked by remember{
        mutableStateOf(true)
    }

    Card(
        modifier = modifier.clickable { /*onClick()*/ },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
    ) {



        Column() {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = item.Sentes_1,
                fontWeight = FontWeight.Bold,
                fontSize = textSize,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = MaterialTheme.colors.secondaryVariant,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(6.dp)
                    .height(50.dp),
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
                maxLines = 2,
                fontSize = textSize_1,
                color = MaterialTheme.colors.secondaryVariant,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier

                    .fillMaxWidth()
                    //.background(MaterialTheme.colors.onSecondary)
                    .padding(6.dp)
                    .height(50.dp),
                onTextLayout = { textLayoutResult ->
                    val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                    if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                        textSize_1 = textSize_1.times(TEXT_SCALE_REDUCTION_INTERVAL)
                    }
                }
            )

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

                Spacer(modifier = Modifier.width(20.dp))

                Icon(
                    painter = painterResource ( R.drawable.snail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.width(20.dp))


                IconToggleButtonSample(
                    Modifier
                        .size(35.dp) ,
                    // .height(45.dp),
                    checked = checked,
                    ClickCheck = {

                        checked = it

                        if(it){
                            var myfavorite =  DataMyFavoriteSentes(
                                Sentes_1 = item.Sentes_1,
                                Sentes_2 = item.Sentes_2,
                                sonido = "")

                            viewmodel.insertMyFavoriteSentes(myfavorite)
                        }else{
                            viewmodel.deleteMyFavoriteSentes(item.Sentes_1)
                        }


                    }
                )



            }

            Spacer(modifier = Modifier.height(10.dp))

        }


    }
}