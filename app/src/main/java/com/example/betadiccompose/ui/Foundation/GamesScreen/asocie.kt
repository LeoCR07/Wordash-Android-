package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun asocie(viewModel: VocabularyViewModel, function: () -> Unit) {

    val porcentaje = viewModel.Step.value / 20f

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


        Text(text = "asocie", fontSize = 30.sp)

    }
}