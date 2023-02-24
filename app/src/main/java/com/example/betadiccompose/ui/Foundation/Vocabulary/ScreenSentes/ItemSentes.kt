package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSentes

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
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ItemSentes(
    modifier: Modifier,
    item: DataWorld,
    viewmodel: VocabularyViewModel,
    onClick: () -> Unit
) {

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize by remember { mutableStateOf(20.sp) }
    var textSize_1 by remember { mutableStateOf(17.sp) }

    val myfavorite = viewmodel.lstfavoritesentes.value
    viewmodel.getMyFavoriteSentes()

    var checked by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        for(e in myfavorite){
            if(item.World_1 == e.Sentes_1){
                checked = true
            }
        }
    }



    Card(
        modifier = modifier.clickable {     onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
    ) {



        Column() {

            Spacer(modifier = Modifier.height(40.dp))


            Text(
                text = item.World_1,
                fontWeight = FontWeight.ExtraBold,
                fontSize = textSize,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color= MaterialTheme.colors.secondaryVariant,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
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
                text = item.World_2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.7f),
                fontSize = textSize_1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
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
                        .clickable {
                            onClick()
                        }
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Icon(
                    painter = painterResource ( R.drawable.snail),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            viewmodel.soundSlowerFromUrl(id = item.id)
                            CoroutineScope(Dispatchers.IO).launch {
                                viewmodel.updateExp()
                            }
                        }
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
                                Sentes_1 = item.World_1,
                                Sentes_2 = item.World_2,
                                sonido ="https://d1i3grysbjja6f.cloudfront.net/Sonido/${viewmodel.GetCategoryName()}/${viewmodel.GetLearnLenguage()}/${item.id}.mp3")

                            viewmodel.insertMyFavoriteSentes(myfavorite)
                        }else{
                            viewmodel.deleteMyFavoriteSentes(item.World_1)
                        }


                    }
                )



            }

            Spacer(modifier = Modifier.height(10.dp))

        }


    }
}