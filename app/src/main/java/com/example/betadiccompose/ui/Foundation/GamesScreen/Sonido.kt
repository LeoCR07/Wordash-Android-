package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Sonido(viewModel: VocabularyViewModel, onMediaClick: () -> Unit) {
    val porcentaje = viewModel.Step.value / 18f

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

        GetLogo(icon = true, titulo = "Days")
        NameGame(titulo = "Days")
        LinearProgressIndicator(
            modifier = Modifier
                .height(4.dp)
                .width(340.dp),
            progress = porcentaje
        )

        Animation(
            animacion = R.raw.sonido,
            modifier = Modifier
                .size(250.dp)
        )

        Text(text = "Palabra", fontSize = 30.sp)
        var select by remember { mutableStateOf("") }

        Row {
            Boton(value = "1",select = select, click = {
                select = "1"
            })
            Boton(value = "2",select = select, click = {
                select = "2"
            })
            Boton(value = "3",select = select, click = {
                select = "3"
            })

        }

        Row {
            Boton(value = "4",select = select, click = {
                select = "4"
            })
            Boton(value = "5",select = select, click = {
                select = "5"
            })
            Boton(value = "6",select = select, click = {
                select = "6"
            })
        }




    }
}

@Composable
private fun Boton(click: () -> Unit, value: String, select: String) {

    IconToggleButton(
        checked = value == select,
        onCheckedChange = {click()})
    {

        val tint by animateColorAsState(
            if (value == select) Color(0xFFEC407A) else Color(0xFFB0BEC5)
        )

        Icon(
            Icons.Rounded.VolumeUp,
            modifier =Modifier.size(150.dp),
            contentDescription = "Radio button icon",
            tint = tint
        )
    }
}

