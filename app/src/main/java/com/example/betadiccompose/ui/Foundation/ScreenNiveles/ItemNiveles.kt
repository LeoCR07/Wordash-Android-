package com.example.betadiccompose.ui.Foundation.ScreenNiveles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.Domain.model.DataNiveles
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.ui.Foundation.Shared.AnimationUrl
import com.example.betadiccompose.ui.Foundation.Shared.Star

@Composable
fun ItemNiveles(onClick: () -> Unit, item: DataNiveles, modifier: Modifier) {

    Box(
    //    elevation = 8.dp
        //border = BorderStroke(1.dp, Color.Blue)
        //shape = RoundedCornerShape(8.dp),
        modifier = modifier.clickable{onClick()}
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //println(item.Img+"")
            AnimationUrl(
                url = item.animation,
            //    modifier = modifier.clickable{onClick()}
            )

            title(

                item.name,
                modifier = Modifier
                    .padding(1.dp))

            Star(
                R.raw.star,
                modifier = Modifier
                    .size(60.dp))


            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                  //  .background(Color.Red),
                //verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.Center
            ){




            }
        }

    }
}
