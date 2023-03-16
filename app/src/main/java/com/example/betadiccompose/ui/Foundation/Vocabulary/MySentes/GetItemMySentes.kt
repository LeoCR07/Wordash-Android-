package com.example.betadiccompose.ui.Foundation.Vocabulary.MySentes

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun GetItemMySentes(
    modifier: Modifier,
    item: DataMyFavoriteSentes,
    viewmodel: VocabularyViewModel,
) {

    //viewmodel.getMyFavoriteSentes()
    //val url = item.sonido.replace("???",viewmodel.GetLearnLenguage())

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
    var textSize by remember { mutableStateOf(20.sp) }
    var textSize_1 by remember { mutableStateOf(17.sp) }

    var hide by remember{ mutableStateOf(true) }
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


    if(hide){
        Card(
            modifier = modifier.clickable { viewmodel.SoundFromLink(item.sonido)  },
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
                            .clickable(interactionSource = interactionSource,indication = null) {

                                isClicked_1 = true
                                CoroutineScope(Dispatchers.Default).launch {
                                    delay(400)
                                    isClicked_1 = false
                                }
                                viewmodel.SoundFromLink(item.sonido)  }
                            .size(40.dp)
                            .padding(bottom = 5.dp)
                            .scale(scale_1),
                        tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Icon(
                        painter = painterResource ( R.drawable.snail__2_),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable (interactionSource = interactionSource,indication = null){
                                isClicked_2 = true

                                CoroutineScope(Dispatchers.Default).launch {
                                    delay(400)
                                    isClicked_2 = false
                                }
                                viewmodel.soundSlowerFromLink(item.sonido) }
                            .size(40.dp)
                            .padding(bottom = 5.dp)
                            .scale(scale_2),
                        tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))


                    IconToggleButtonSample(
                        Modifier
                            .size(35.dp) ,
                        // .height(45.dp),
                        checked = true,
                        ClickCheck = {

                            hide = false
                            viewmodel.SoundFromLocal(R.raw.fav)

                            viewmodel.deleteMyFavoriteSentes(item.Sentes_1)

                            viewmodel.lstfavoritesentes.value =
                                viewmodel.lstfavoritesentes.value.filter {
                                    it.Sentes_1 != item.Sentes_1
                                }


                        }
                    )



                }

                Spacer(modifier = Modifier.height(10.dp))

            }


        }
    }


}