package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenWorld

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GetItemWorld(
    onClick: () -> Unit,
    item: DataWorld,
    modifier: Modifier,
    viewmodel: VocabularyViewModel
) {

    var checked by remember{ mutableStateOf(false)}
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

    LaunchedEffect(key1 = true){

        val myfavorite = viewmodel.mywords.value
        viewmodel.getMyFavoriteWords()

        for(e in myfavorite){
            if(item.World_1== e.World_1){
                checked = true
            }
        }
    }


    Card(
        modifier = modifier.clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(6.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary)
    ) {

        Column() {
            // println(item.Img+"")
            ThumbWorld(
                img = item.Img)

            Row() {
                Column() {
                    title(
                        item.World_1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        weight = FontWeight.SemiBold
                    )
                    title(
                        item.World_2,
                        modifier=
                        Modifier
                            .fillMaxWidth()
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
                            onClick()

                            CoroutineScope(Dispatchers.Default).launch {
                                delay(400)
                                isClicked_1 = false
                            }

                        }
                        .scale(scale_1)
                        .size(25.dp)
                        .clip(CircleShape),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f) )

                Icon(
                    painter = painterResource (R.drawable.snail),
                    contentDescription = "BTN",
                    modifier = Modifier
                        .clickable (interactionSource = interactionSource,indication = null){
                            isClicked_2 = true

                            viewmodel.soundSlowerFromUrl(id = item.id)
                            CoroutineScope(Dispatchers.IO).launch {
                                viewmodel.updateExp()
                            }

                            CoroutineScope(Dispatchers.Default).launch {
                                delay(400)
                                isClicked_2  = false
                            }
                        }
                        .size(45.dp)
                        .scale(scale_2)
                        .clip(CircleShape),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f) )


                IconToggleButtonSample(
                    Modifier
                        .height(55.dp),
                    checked = checked,
                    ClickCheck = {
                        checked = it


                        if(it){
                            viewmodel.SoundFromLocal(R.raw.fav)
                            var myfavorite =  DataMyFavoriteWord(
                                World_1 = item.World_1,
                                World_2 = item.World_2,
                                Img = item.Img,
                                sonido ="https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewmodel.GetLearnLenguage()}/${viewmodel.GetPath()}/${item.id}.mp3")

                             viewmodel.insertMyFavoriteWord(myfavorite)

                        }else{
                            viewmodel.SoundFromLocal(R.raw.favoritedelete)
                           viewmodel.deleteMyFavoriteWord(item.World_1)
                        }

                    }
                )
            }



        }


    }
}

