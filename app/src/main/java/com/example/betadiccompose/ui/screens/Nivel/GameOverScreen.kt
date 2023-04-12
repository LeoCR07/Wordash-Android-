package com.example.betadiccompose.ui.Foundation.Game.GameOver

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*


@Composable
fun GameOverScreen(
    NavToExit:()->Unit,
    NavToAgain:()->Unit,
    NavToStudy:()->Unit,
    viewmodel:VocabularyViewModel
) {

    var code by remember {
        mutableStateOf(viewmodel.GetCode())
    }

    LaunchedEffect(key1 = true){
        viewmodel.SoundFromLocal(R.raw.ulost)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = viewmodel.GetSettings().YouNeedToPracticeMore[code]!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Local_Animation(
            animacion =R.raw.gameover,
            modifier = Modifier.size(400.dp) )

        Spacer(modifier = Modifier.height(30.dp))
        BtnSuper(
            title = viewmodel.GetSettings().GoToStudy[code]!!,
            FontColor = Color.White,
            IsIcon = false,
            fontSize = 18.sp,
            color = Color(0xFF2196F3),
            onClick = {
                NavToStudy()
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        BtnSuper(
            title = viewmodel.GetSettings().PlayAgain[code]!!,
            color = MaterialTheme.colors.background,
            FontColor = MaterialTheme.colors.secondaryVariant,
            IsIcon = false,
            Outline = true,
            outlineColor = MaterialTheme.colors.secondaryVariant,
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
            title = viewmodel.GetSettings().GoOut[code]!!,
            color = MaterialTheme.colors.background,
            FontColor = MaterialTheme.colors.secondaryVariant,
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