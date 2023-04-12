package com.example.betadiccompose.ui.Foundation.GamesScreen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Foundation.ScreenVocabulary.TEXT_SCALE_REDUCTION_INTERVAL
import com.example.betadiccompose.Foundation.ScreenVocabulary.title
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.Button.ButtonWithIconSample
import com.example.betadiccompose.ui.Foundation.Game.Sonido.ClickSonido
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*


@Composable
fun Sonido(
    viewModel: VocabularyViewModel,
    onMediaClick: (Int,Int,MediaPlayer,MediaPlayer,MediaPlayer,MediaPlayer,MediaPlayer,MediaPlayer) -> Unit,
    lista: List<DataWorld>) {
    var code by remember {
        mutableStateOf(viewModel.GetCode())
    }

    var idAux = 0
    var lstAux: List<DataWorld> = emptyList()

    lstAux =  viewModel.getSoundChoose(lista)

    var lstOrder = remember {
        lstAux
    }

    idAux = lstOrder[0].id

    val lstrandon = remember {
        RandomText(lstOrder)
    }

    var id = remember {
        idAux
    }

    var textSize = 30.sp
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

       //BackgroundImg("https://d1i3grysbjja6f.cloudfront.net/IMG/${prefs.GetCategory()}/${id}.jpg")

        Text(
            text= viewModel.GetSettings().SelectTheCorrectAudio[code]!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )


        Local_Animation(
            animacion = R.raw.dancing,
            modifier = Modifier
                .size(200.dp),
            speed = 0.6f
        )

        Text(
            modifier = Modifier
                .padding(6.dp),
            textAlign = TextAlign.Center,
            text= "${lstOrder [0].World_2}".replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            fontWeight = FontWeight.Normal,
            fontSize = textSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            onTextLayout = { textLayoutResult ->
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            }
        )


        var select by remember { mutableStateOf(-1) }
        Spacer(modifier = Modifier.height(1.dp))
        var opc by remember { mutableStateOf(0) }

        val mediaPlayer_1 = MediaPlayer().apply {
            println("audio1000")

            //var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${lstrandon[0]}.mp3"

            setAudioAttributes(

                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()

            )
          //  setDataSource(urlAudio)
           // prepare()
        }
        val mediaPlayer_2 = MediaPlayer().apply {
            println("audio1000")

           // var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${lstrandon[1]}.mp3"

            setAudioAttributes(

                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()

            )
          //  setDataSource(urlAudio)
           // prepare()
        }
        val mediaPlayer_3 = MediaPlayer().apply {
            println("audio1000")

           // var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${lstrandon[3]}.mp3"

            setAudioAttributes(

                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()

            )
          //  setDataSource(urlAudio)
           // prepare()
        }
        val mediaPlayer_4 = MediaPlayer().apply {
            println("audio1000")

            //var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${lstrandon[4]}.mp3"

            setAudioAttributes(

                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()

            )
           // setDataSource(urlAudio)
           // prepare()
        }
        val mediaPlayer_5 = MediaPlayer().apply {
            println("audio1000")

         //   var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${lstrandon[5]}.mp3"

            setAudioAttributes(

                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()

            )
           // setDataSource(urlAudio)
           // prepare()
        }
        val mediaPlayer_6 = MediaPlayer().apply {
            println("audio1000")

            //var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${lstrandon[6]}.mp3"

            setAudioAttributes(

                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()

            )
           // setDataSource(urlAudio)
           // prepare()
        }



        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            ClickSonido(value = 0,select = select, click = {
                select = 0
                opc = lstrandon[0].id
                viewModel.media(lstrandon[0].id,mediaPlayer_1)
                //viewModel.soundFromUrl(id=lstrandon[0].id)
            })
            ClickSonido(value = 1,select = select, click = {
                select = 1
                opc = lstrandon[1].id
                viewModel.media(lstrandon[1].id,mediaPlayer_2)
            // viewModel.soundFromUrl(id=lstrandon[1].id)
            })
            ClickSonido(value = 2,select = select, click = {
                select = 2
                opc = lstrandon[2].id
                viewModel.media(lstrandon[2].id,mediaPlayer_3)
                //viewModel.soundFromUrl(id=lstrandon[2].id)
            })
        }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            ClickSonido(value = 3,select = select, click = {
                select = 3
                opc = lstrandon[3].id
                viewModel.media(lstrandon[3].id,mediaPlayer_4)
                //viewModel.soundFromUrl(id=lstrandon[3].id)

            })
            ClickSonido(value = 4,select = select, click = {
                select = 4
                opc = lstrandon[4].id
                viewModel.media(lstrandon[4].id,mediaPlayer_5)
                //viewModel.soundFromUrl(id=lstrandon[4].id)

            })
            ClickSonido(value = 5,select = select, click = {
                select = 5
                opc = lstrandon[5].id
                viewModel.media(lstrandon[5].id,mediaPlayer_6)
                //viewModel.soundFromUrl(id=lstrandon[5].id)
            })
        }

        Spacer(modifier = Modifier.height(10.dp))



        if(select != -1){
            ButtonWithIconSample(
                modifier = Modifier
                    .width(220.dp)
                    .height(60.dp),
                onMediaClick = { onMediaClick(
                    opc,
                    id,
                    mediaPlayer_1,
                    mediaPlayer_2,
                mediaPlayer_3,
                mediaPlayer_4,
                mediaPlayer_5,
                mediaPlayer_6)})
        }

    }
}
