package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenSentes

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ItemSentes(
    modifier: Modifier,
    item: DataWorld,
    viewmodel: VocabularyViewModel,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    var isClicked_1 by remember { mutableStateOf(false) }
    var isClicked_2 by remember { mutableStateOf(false) }


    val scale_1 by animateFloatAsState(
        if (isClicked_1) 1.1f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    val scale_2 by animateFloatAsState(
        if (isClicked_2) 1.1f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )



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
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = modifier.clickable {     onClick() },
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
                        .clickable(interactionSource = interactionSource, indication = null) {
                            isClicked_1 = true
                            onClick()
                            CoroutineScope(Dispatchers.Default).launch {
                                delay(400)
                                isClicked_1 = false
                            }

                        }
                        .scale(scale_1)
                        .size(40.dp)
                        .padding(bottom = 5.dp),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Icon(
                    painter = painterResource ( R.drawable.snail),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(interactionSource = interactionSource, indication = null) {
                            viewmodel.soundSlowerFromUrl(id = item.id)

                            isClicked_2 = true

                            CoroutineScope(Dispatchers.Default).launch {
                                delay(400)
                                isClicked_2 = false
                            }

                            CoroutineScope(Dispatchers.IO).launch {
                                viewmodel.updateExp()
                            }
                        }
                        .size(40.dp)
                        .scale(scale_2)
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

                            viewmodel.SoundFromLocal(R.raw.fav)

                            var myfavorite =  DataMyFavoriteSentes(
                                Sentes_1 = item.World_1,
                                Sentes_2 = item.World_2,
                                sonido ="https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewmodel.GetLearnLenguage()}/${viewmodel.GetPath()}/${item.id}.mp3")

                            viewmodel.insertMyFavoriteSentes(myfavorite)
                        }else{
                            viewmodel.SoundFromLocal(R.raw.favoritedelete)
                            viewmodel.deleteMyFavoriteSentes(item.World_1)
                        }


                    }
                )



            }

            Spacer(modifier = Modifier.height(10.dp))

        }


    }
}