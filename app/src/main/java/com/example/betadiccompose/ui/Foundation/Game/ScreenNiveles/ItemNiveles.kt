package com.example.betadiccompose.ui.Foundation.Game.ScreenNiveles

import android.util.Size
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataNiveles
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.data.network_database.model.DataUser
import com.example.betadiccompose.ui.Foundation.Shared.Url_Animation
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun ItemNiveles(
    viewModel: VocabularyViewModel,
    onClick: (DataNiveles) -> Unit,
    item: DataNiveles,
    modifier: Modifier) {

    var datauser by remember {
        mutableStateOf(DataUser())
    }

    viewModel.getDataUser()
    datauser = viewModel.lstdatauser.value
    var icon:Int

    val interactionSource = remember { MutableInteractionSource() }


    Box(
        modifier = modifier
            .clickable(interactionSource = interactionSource,indication = null)
            {onClick(item)}
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            if(item.id <= datauser.level){
                Url_Animation(
                    url = item.animation,
                    isplaying = true
                )
            }else{
                Url_Animation(
                    url = item.animation,
                    isplaying = false
                )
            }

            if (item.id  < datauser.level) {
                icon = R.drawable.star_on

            } else {
                icon = R.drawable.star_off
            }


            title(
                item.name,
                modifier = Modifier
                    .padding(1.dp),
                weight = FontWeight.ExtraBold
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
                    text = item.stars.toString(),
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    tint = if(icon == R.drawable.star_on) Color.Unspecified else MaterialTheme.colors.secondaryVariant
                )


            }
        }

    }
}


