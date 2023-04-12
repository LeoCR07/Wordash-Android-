package com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
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
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
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
    index: Int,
    lista: List<DataWorld>
) {
    /********************   view model  ************************/
    //var wordAux: DataWorld
    var code by remember {
        mutableStateOf(viewModel.GetCode())
    }
    //wordAux = DataWorld(1,"","DayS","")
   // wordAux =  viewModel.getOneWord(lista)
    //println(wordAux)

    var WordRemember by remember {
        mutableStateOf(viewModel.getOneWord(lista))
    }

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

    var textSize by remember {
        mutableStateOf(29.sp)
    }


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
        contract = SpeechRecognizerContract(viewModel.GetLearnLenguage(),WordRemember.World_1),
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
                text= viewModel.GetSettings().ItsTimeToListenToYouPronounce[code]!!.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }else if(index == 9) {
            Text(
                text = viewModel.GetSettings().LetGoOneMoreTimePronounce[code]!!.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }else if(index == 16){
            Text(
                text = viewModel.GetSettings().ThisWillNotBeEasySay[code]!!.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))



    Local_Animation(
        animacion = R.raw.wave
        ,modifier = Modifier.size(250.dp),
        speed = 0.2f)



    Spacer(modifier = Modifier.height(0.dp))


    Text(
        text = " ${WordRemember.World_1}".replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        },
        fontWeight = FontWeight.Normal,
        fontSize = textSize,
        textAlign = TextAlign.Center,
        maxLines = 2,
        onTextLayout = { textLayoutResult ->
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
            }
        }
    )



    Spacer(modifier = Modifier.height(40.dp))


    if (viewModel.state.text != null) {
        if(CheckUpAudio(
                WordRemember.World_1.trim().lowercase(Locale.getDefault()),
                viewModel.state.text.toString().trim().lowercase(Locale.getDefault()))
        ){
            viewModel.SoundFromLocal(R.raw.rigth)
            viewModel.step++
        }
    }


    BtnSuper(
        title = viewModel.GetSettings().PressToTalk[code]!!,
        color = Color(0xFF23A4DF),
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
        title = viewModel.GetSettings().JumpExercise[code]!!,
        FontColor = MaterialTheme.colors.secondaryVariant,
        IsIcon = false,
        Outline = false,
        color = MaterialTheme.colors.background,
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

private fun CheckUpAudio(word_2:String, audio_1:String): Boolean {

    var valor = false
    var counter = 0

    var word = word_2.replace("[¿?,]".toRegex(), "")

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

        val minimo = (word.length * 0.70).roundToInt()

        if(counter >= minimo){
            valor = true
        }

    }
    return valor

}


class SpeechRecognizerContract(var lenguaje:String,var word:String) : ActivityResultContract<Unit, ArrayList<String>?>() {


    override fun createIntent(context: Context, input: Unit): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH

        )

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE, if("English"==lenguaje) "en-US" else "es-ES"

        )
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,word

        )

        return intent
    }
    override fun parseResult(resultCode: Int, intent: Intent?): ArrayList<String>? {
        if (resultCode != Activity.RESULT_OK){
            return null
        }
        return intent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
    }
}
