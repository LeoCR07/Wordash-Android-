package com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun ReviewScreen(
    NavToNext: ()->Unit,
    NavToExit: () -> Unit,
    NavToAgain: () -> Unit,
    viewmodel:VocabularyViewModel
) {

    LaunchedEffect(key1 = true ){
        viewmodel.SoundFromLocal(R.raw.victory)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Felicitaciones has superado el reto",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Local_Animation(
            animacion = R.raw.trophy,
            modifier = Modifier
                .size(400.dp))

        Spacer(modifier = Modifier.height(40.dp))

        BtnSuper(
            title = "Siguiente nivel",
            FontColor = Color.White,
            IsIcon = false,
            fontSize = 18.sp,
            color = Color(0xFF68A226),
            onClick = {
                NavToNext()
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        BtnSuper(
            title = "Jugar de nuevo",
            FontColor = Color.Black,
            IsIcon = true,
            icon = R.drawable.play_on,
            Outline = true,
            outlineColor = Color.LightGray,
            fontSize = 15.sp,
            onClick = {
                NavToAgain()
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        BtnSuper(
            title = "Salir",
            FontColor = Color.Black,
            IsIcon = true,
            icon = R.drawable.sign_out,
            Outline = true,
            outlineColor = Color.LightGray,
            fontSize = 15.sp,
            onClick = {
                NavToExit()
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
        )

    }
}