package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenVerbs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import com.example.betadiccompose.data.network_database.model.DataGramar
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ItemVerb(
    modifier: Modifier,
    item: DataGramar,
    viewmodel: VocabularyViewModel,
    onMediaClick: () -> Unit
) {

    val myfavorite = viewmodel.lstfavoritegramar.value
    viewmodel.getMyFavoriteGramar()
    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize_1 by remember { mutableStateOf(25.sp) }
    var textSize_2 by remember { mutableStateOf(18.sp) }


    var checked by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        for(e in myfavorite){
            if(item.gramar_1 == e.Gramar_1){
                checked = true
            }
        }
    }

    Card(
        modifier = modifier.clickable { onMediaClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
    ) {

        Column() {

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = item.gramar_1,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    color = MaterialTheme.colors.secondaryVariant,
                    fontSize = textSize_1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        //.background(MaterialTheme.colors.onSecondary)
                        .padding(4.dp)
                        .width(160.dp)
                        .height(35.dp)

                )
                Text(
                    text = item.gramar_2,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.7f),
                    fontSize = textSize_1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .width(160.dp)
                        .padding(4.dp)
                        .height(35.dp),
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
                        .clickable {  }
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))
            /*****************************/
            Text(
                text = item.example_1,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.SemiBold,
                fontSize = textSize_2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .height(35.dp),
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                    if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                        textSize_2 = textSize_2.times(TEXT_SCALE_REDUCTION_INTERVAL)
                    }
                }
            )
            Text(
                text = item.example_2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                fontSize = textSize_2,
                color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .height(35.dp),
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                    if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                        textSize_2 = textSize_2.times(TEXT_SCALE_REDUCTION_INTERVAL)
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

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
                    painter = painterResource ( R.drawable.snail),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewmodel.updateExp()
                            }
                        }
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.width(25.dp))

                IconToggleButtonSample(
                    Modifier
                        .size(60.dp),
                    checked = checked,
                    ClickCheck = {

                        checked = it
                        if(it){
                            var myfavorite =  DataMyFavoriteGramar(
                                Gramar_1 = item.gramar_1,
                                Gramar_2 = item.gramar_2,
                                Example_1 = item.example_1,
                                Example_2 = item.example_2
                            )

                            viewmodel.insertMyFavoriteGramar(myfavorite)
                        }else{
                            viewmodel.deleteMyFavoriteGramar(item.gramar_1)
                        }

                    }
                )


            }

            Spacer(modifier = Modifier.height(10.dp))



        }


    }
}