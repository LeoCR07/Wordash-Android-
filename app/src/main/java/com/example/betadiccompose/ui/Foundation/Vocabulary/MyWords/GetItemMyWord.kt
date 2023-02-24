package com.example.betadiccompose.ui.Foundation.Vocabulary.MyWords

import android.content.Context
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
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenWorld.ThumbWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import compose.material.theme.IconToggleButtonSample

@Composable
fun GetItemMyWord(
    onClick: () -> Unit,
    item: DataMyFavoriteWord,
    modifier: Modifier,
    viewmodel: VocabularyViewModel,
    context: Context
) {

    var checked by remember{
        mutableStateOf(true)
    }
    var hide by remember{ mutableStateOf(true) }

    if(hide){
        Card(
            modifier = modifier.clickable { onClick() },
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
                            .clickable { }
                            .size(25.dp)
                            .clip(CircleShape),
                        tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f) )

                    Icon(
                        painter = painterResource (R.drawable.snail__2_),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable { }
                            .size(45.dp)
                            .clip(CircleShape),
                        tint = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f) )


                    IconToggleButtonSample(
                        Modifier
                            .height(55.dp),
                        checked = checked,
                        ClickCheck = {

                            checked = it
                            hide = false
                            viewmodel.SoundFromLocal(R.raw.favoritedelete)
                            viewmodel.deleteMyFavoriteWord(item.Img)

                            /*
                            if(it){
                                viewmodel.SoundFromLocal(R.raw.fav)
                                var myfavorite =  DataMyFavoriteWord(
                                    World_1 = item.World_1,
                                    World_2 = item.World_2,
                                    Img = item.Img,
                                    sonido = item.sonido)

                                viewmodel.insertMyFavoriteWord(myfavorite)
                            }else{
                                viewmodel.SoundFromLocal(R.raw.favoritedelete)
                                viewmodel.deleteMyFavoriteWord(item.Img)
                            }
    */
                        }
                    )
                }



            }


        }
    }

}
