package com.example.betadiccompose.ui.Foundation.GamesScreen

import android.Manifest
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.Provider.SpeechRecognizerContract
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.SonidoLocal
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.SonidoWrong
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.TopAppBarLevel
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.util.*
import kotlin.math.roundToInt


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeakToTalk(
    viewModel: VocabularyViewModel,
    context: Context,
    index: Int,
    lista: List<DataWorld>
) {
    /********************   view model  ************************/
    var wordAux: DataWorld

    //wordAux = DataWorld(1,"","DayS","")
    wordAux =  viewModel.GetOneWord(lista)
    println(wordAux)
    var WordRemember = remember { wordAux }

    println("/******** Talk to text **********/")
    println("${WordRemember.World_2}")

    /********************   Recognizer  ************************/
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.RECORD_AUDIO
    )
    SideEffect {
        permissionState.launchPermissionRequest()
    }


    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract(),
        onResult = {
            viewModel.changeTextValue(it.toString())
        }
    )
    /********************   Compose  ************************/

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {


        if(index == 5){
            Text(
                text="Es hora de escucharte, pronuncia",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }else if(index == 9) {
            Text(
                text = "Vamos una ves mas, pronuncia",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }else if(index == 16){
            Text(
                text = "Esto no sera nada facil, traduce ",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))


    if(index == 16){
        Animation(animacion = R.raw.translate
            ,modifier = Modifier.size(250.dp))
    }else{
        Animation(animacion = R.raw.wave
            ,modifier = Modifier.size(250.dp))
    }


        Spacer(modifier = Modifier.height(0.dp))


    if(index == 16){
        Text(text = " ${WordRemember.World_2}",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 29.sp,
            textAlign = TextAlign.Center)
    }else{
        Text(text = " ${WordRemember.World_1}",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 29.sp,
            textAlign = TextAlign.Center)
    }



        Spacer(modifier = Modifier.height(40.dp))


        if (viewModel.state.text != null) {
            if(CheckUpAudio(
                    WordRemember.World_1.trim().lowercase(Locale.getDefault()),
                    viewModel.state.text.toString().trim().lowercase(Locale.getDefault()))){
                SonidoLocal(context)
                viewModel.step++
            }
        }


    BtnSuper(
        title = "Presiona para hablar",
        color =Color(0xFF23A4DF),
        FontColor = Color.White,
        IconLocal = true,
        onClick = {
            if (permissionState.status.isGranted) {
                speechRecognizerLauncher.launch(Unit)
            } else
                permissionState.launchPermissionRequest()
        },
        fontSize = 18.sp,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
    )


    Spacer(modifier = Modifier.height(10.dp))

    BtnSuper(
        title = "Saltar ejercicio",
        FontColor = Color.Black,
        IsIcon = false,
        Outline = true,
        outlineColor = Color.LightGray,
        fontSize = 18.sp,
        onClick = {
                  viewModel.step +=1
        },
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
    )

}

private fun CheckUpAudio(word:String, audio_1:String): Boolean {

    var valor = false
    var counter = 0

    val audio = audio_1.substring(1,audio_1.length-1)

    println("Las plbras son $audio y $word")

    if(word == audio){
        valor = true
    }else{

        var amount =
            if(word.length <= audio.length){
                word.length
            }else{
                audio.length
            }

        for (i in 0 until amount){
            if(word[i] == audio[i] ){
                counter++
            }
        }

        val minimo = (word.length * 0.80).roundToInt()

        if(counter >= minimo){
            valor = true
        }

        println("minimo: $minimo")
        println("tus palabras :${counter} -- ${audio}"  +
                "\npalabras corecta ${word.length} -- $word")
    }
    return valor

}