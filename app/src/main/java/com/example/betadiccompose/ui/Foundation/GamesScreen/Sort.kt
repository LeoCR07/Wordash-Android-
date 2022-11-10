package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.ui.Foundation.Shared.AnimationUrl
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Sort(viewModel: VocabularyViewModel, i: Int, function: () -> Unit) {
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

        AnimationUrl(
            url = "https://dicvocabulary.s3.us-east-2.amazonaws.com/Datos/animaciones/Dias/$i.json",
            isplaying = true)
    }
}