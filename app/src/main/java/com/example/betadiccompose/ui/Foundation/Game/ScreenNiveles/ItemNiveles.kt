package com.example.betadiccompose.ui.Foundation.Game.ScreenNiveles

import android.util.Size
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
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
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.Url_Animation
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*


@Composable
fun ItemNiveles(
    viewModel: VocabularyViewModel,
    onClick: (DataNiveles) -> Unit,
    item: DataNiveles,
    modifier: Modifier) {

    var datauser = viewModel.lstdatauser.value

    LaunchedEffect(key1 =true){
        viewModel.getDataUser()
    }


    var icon:Int
    var level = datauser.level

    val interactionSource = remember { MutableInteractionSource() }

    val calendar = Calendar.getInstance()
    val hora = calendar.get(Calendar.HOUR_OF_DAY)

    var animation :Int = R.raw.whitecoin
    var color = MaterialTheme.colors.secondaryVariant.copy(0.7f)

     if(viewModel.GetTheme() == 2) {
         animation = R.raw.whitecoin
     }else if(viewModel.GetTheme() == 1){
         animation =R.raw.all
     }else if(viewModel.GetTheme() == 0){
         if (hora>=18) {
             animation =R.raw.all
         }else{
             animation = R.raw.whitecoin
         }
     }



    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {


        if(item.id <= level){

            Local_Animation(
                animacion = R.raw.king,
                isPlaying = true,
                modifier = modifier
                    .size(120.dp)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        onClick(item)
                    })


            /*
            Url_Animation(
                url = item.animation,
                isplaying = true,
                modifier =  modifier
                    .clickable(interactionSource = interactionSource,indication = null) {
                        onClick(item)
                    }
            )
            */

        }else{

            Local_Animation(
                animacion = animation,
                isPlaying = true,
                modifier = modifier
                    .size(120.dp)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        viewModel.SoundFromLocal(R.raw.goldenking)
                    })

        }

        if (item.id  < level) {
            icon = R.drawable.crown
            color = MaterialTheme.colors.secondaryVariant
        } else {
            color = MaterialTheme.colors.secondaryVariant.copy(0.7f)
            icon = R.drawable.nocrown
        }





        Column(
            modifier = modifier,
            //    .padding(start = 60.dp),
           // .offset( y =(if (item.id  < level )-28.dp else 0.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top){

            title(
                item.name,
                modifier = Modifier,
                color = color,
                weight = FontWeight.ExtraBold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Text(
                    modifier = Modifier,
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
                    tint = if(icon == R.drawable.crown) Color.Unspecified else MaterialTheme.colors.secondaryVariant
                )


            }

        }




    }


}

