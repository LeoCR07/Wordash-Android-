package com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


@Composable
fun ReviewScreen(
    NavToNext: ()->Unit,
    NavToExit: () -> Unit,
    NavToAgain: () -> Unit,
    viewmodel:VocabularyViewModel
) {




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
            FontColor = MaterialTheme.colors.secondaryVariant,
            IsIcon = false,
            color = MaterialTheme.colors.background,
            Outline = true,
            outlineColor = MaterialTheme.colors.secondaryVariant ,
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
            FontColor = MaterialTheme.colors.secondaryVariant,
            color = MaterialTheme.colors.background,
            IsIcon = false,
            Outline = true,
            outlineColor = MaterialTheme.colors.secondaryVariant,
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