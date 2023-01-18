package com.example.betadiccompose.ui.Foundation.ScreenNiveles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.Domain.model.DataNiveles
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.data.network.model.DataUser
import com.example.betadiccompose.ui.Foundation.Shared.AnimationUrl
import com.example.betadiccompose.ui.Foundation.Shared.Star
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun ItemNiveles(viewModel: VocabularyViewModel,onClick: () -> Unit, item: DataNiveles, modifier: Modifier) {

    var datauser by remember {
        mutableStateOf(DataUser())
    }

    viewModel.GetDataUser()

    datauser = viewModel.lstdatauser.value


    var icon:Int




    Box(
        modifier = modifier.clickable{onClick()}
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            if (item.id  <= datauser.level) {

                AnimationUrl(
                    url = item.animation,
                    isplaying = true
                )

                icon = R.drawable.star_on

            } else {
                AnimationUrl(
                    url = "https://dicvocabulary.s3.us-east-2.amazonaws.com/Animaciones/disabled/0.json",
                    isplaying = false
                )
                icon = R.drawable.star_off
            }





            title(

                item.name,
                modifier = Modifier
                    .padding(1.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                //  .background(Color.Red),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Text(
                    text = "5",
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    tint = Color.Unspecified
                )


            }
        }

    }
}
