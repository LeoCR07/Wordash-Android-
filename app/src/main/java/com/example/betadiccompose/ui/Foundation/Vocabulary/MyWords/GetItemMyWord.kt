package com.example.betadiccompose.ui.Foundation.Vocabulary.MyWords

import android.content.Context
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenWorld.ThumbWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GetItemMyWord(
    item: DataMyFavoriteWord,
    modifier: Modifier,
    viewmodel: VocabularyViewModel
) {
   // val url = item.sonido.replace("???",viewmodel.GetLearnLenguage())
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

    val interactionSource = remember { MutableInteractionSource() }

        Card(
            modifier = modifier.clickable { viewmodel.SoundFromLink(item.sonido) },
            elevation = 4.dp,
            shape = RoundedCornerShape(6.dp),
            backgroundColor = MaterialTheme.colors.onPrimary,
            border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
        ) {

            Column() {
                ThumbWorld(
                    img = item.Img)

                Row() {
                    Column() {
                        title(
                            item.World_1,
                            modifier = Modifier
                                .fillMaxWidth()
                                //  .background(MaterialTheme.colors.onSecondary)
                                .padding(2.dp),
                            weight = FontWeight.SemiBold
                        )
                        title(
                            item.World_2,
                            modifier=
                            Modifier
                                .fillMaxWidth()
                                //   .background(MaterialTheme.colors.onSecondary)
                                .padding(2.dp)

                        )
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(45.dp)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource (  R.drawable.audio),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable(interactionSource = interactionSource,indication = null) {
                                isClicked_1 = true
                                viewmodel.SoundFromLink(item.sonido)

                                CoroutineScope(Dispatchers.Default).launch {
                                    delay(400)
                                    isClicked_1 = false
                                }

                            }
                            .size(25.dp)
                            .scale(scale_1)
                            .clip(CircleShape),
                        tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f) )

                    Icon(
                        painter = painterResource (R.drawable.snail),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable(interactionSource = interactionSource,indication = null) {
                                isClicked_2 = true
                                viewmodel.soundSlowerFromLink(item.sonido)

                                CoroutineScope(Dispatchers.Default).launch {
                                    delay(400)
                                    isClicked_2 = false
                                }

                            }


                            .size(45.dp)
                            .scale(scale_2)
                            .clip(CircleShape),
                        tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f) )


                    IconToggleButtonSample(
                        Modifier
                            .height(55.dp),
                        checked = true,
                        ClickCheck = {

                            viewmodel.SoundFromLocal(R.raw.fav)

                            viewmodel.deleteMyFavoriteWord(item.World_1)

                            viewmodel.mywords.value =
                                viewmodel.mywords.value.filter { it.World_1 != item.World_1 }

                        }
                    )
                }
            }
        }
    }



