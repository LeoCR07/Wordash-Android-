package com.example.betadiccompose.ui.Foundation.ScreenWorld

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun GetItemWorld(
    onClick: () -> Unit,
    item: DataWorld,
    modifier: Modifier,
    viewmodel: VocabularyViewModel ,
    prefs: Prefs
) {

    viewmodel.GetMyFavoriteWords()

    val myfavorite = viewmodel.lstfavoritewords.value
    Card(
        modifier = modifier.clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(6.dp)

    ) {



        Column() {
            // println(item.Img+"")
            ThumbWorld(
                img = item.Img,
                add ={

                var myfavorite =  DataMyFavoriteWord(
                    World_1 = item.World_1,
                    World_2 = item.World_2,
                    Img = item.Img,
                    sonido ="https://d1i3grysbjja6f.cloudfront.net/Sonido/${prefs.GetCategory()}/1/${item.id}.mp3")

                viewmodel.InsertMyFavoriteWord(myfavorite)

                println("Se agrego la palabra ")
            },
            delete = {
                viewmodel.DeleteMyFavoriteWord(item.Img)
                println("Se ha borrado ")
            },
            myfavorite = myfavorite,
            item = item)

            Row() {
                Column() {
                    title(
                        item.World_1,
                        modifier = Modifier
                            .fillMaxWidth()
                          //  .background(MaterialTheme.colors.onSecondary)
                            .padding(2.dp)
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




        }


    }
}
