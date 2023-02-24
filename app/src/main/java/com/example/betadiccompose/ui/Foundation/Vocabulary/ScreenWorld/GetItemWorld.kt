package com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenWorld

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Game_Provider.Prefs
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.MyFavorite.LocalMusic
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GetItemWorld(
    onClick: () -> Unit,
    item: DataWorld,
    modifier: Modifier,
    viewmodel: VocabularyViewModel
) {

    viewmodel.getMyFavoriteWords()

    var checked by remember{ mutableStateOf(false)}

    LaunchedEffect(key1 = true){


        val myfavorite = viewmodel.lstfavoritewords.value
        //var checked = false

        for(e in myfavorite){
            if(item.Img== e.Img){
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
                        .clickable {
                            onClick()
                        }
                        .size(25.dp)
                        .clip(CircleShape),
                    tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f) )

                Icon(
                    painter = painterResource (R.drawable.snail),
                    contentDescription = "BTN",
                    modifier = Modifier
                        .clickable {
                            viewmodel.soundSlowerFromUrl(id = item.id)
                            CoroutineScope(Dispatchers.IO).launch {
                                viewmodel.updateExp()
                            }
                        }
                        .size(45.dp)
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
                                sonido ="https://d1i3grysbjja6f.cloudfront.net/Sonido/${viewmodel.GetCategoryName()}/${viewmodel.GetLearnLenguage()}/${item.id}.mp3")

                            viewmodel.insertMyFavoriteWord(myfavorite)
                        }else{
                            viewmodel.SoundFromLocal(R.raw.favoritedelete)
                            viewmodel.deleteMyFavoriteWord(item.Img)
                        }

                    }
                )
            }



        }


    }
}

