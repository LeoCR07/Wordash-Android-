package com.example.betadiccompose.ui.screens.Menu

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.betadiccompose.R
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource

@Composable
fun MusicPlayer() {

    val lyric_1 = "Padre Nuestro,\n" +
            "que estás en el cielo,\n" +
            "santificado sea tu nombre;\n" +
            "venga a nosotros tu reino;\n" +
            "hágase tu voluntad,\n" +
            "en la tierra como en el cielo.\n" +
            "Danos hoy nuestro pan de cada día;\n" +
            "perdona nuestras ofensas,\n" +
            "como también nosotros\n" +
            "perdonamos a los que nos ofenden;\n" +
            "no nos dejes caer en la tentación,\n" +
            "y líbranos del mal Amé"

    val lyric_2 = "Our Father, \n" +
            "who art in heaven,\n" +
            "Hallowed be thy name.\n" +
            "Thy Kingdom come.\n" +
            "Thy will be done\n " +
            "on earth as it is in heaven.\n" +
            "Give us this day our daily bread.\n" +
            "And forgive us our trespasses, \n" +
            "as we forgive \n" +
            "those who trespass against us.\n" +
            "And lead us not into temptation,\n" +
            "But deliver us from evil Amen"


    val lst_1 = lyric_1.split("\n")
    val lst_2 = lyric_2.split("\n")
    val range = listOf(0f,660f,1748f,3641f,5340f,6390f,7608f,9743f,11193f,12317f,14281f,15873f,17445f)

    var play by remember { mutableStateOf(true) }
    var lyric_Color :Color
    val size = lst_1.size.toFloat()       //81

    //Exo()

    val contex = LocalContext.current
    val url = "https://dicvocabulary.s3.us-east-2.amazonaws.com/Track420.mp3"

    val exoPlayer = remember {
        ExoPlayer
            .Builder(contex)
            .build()
            .apply {
                val defaultDataSource = DefaultDataSource.Factory(contex)
                val datasourceFactory : DataSource.Factory =
                    DefaultDataSource.Factory (contex,defaultDataSource)
                val sorce = ProgressiveMediaSource.Factory(datasourceFactory)
                    .createMediaSource(MediaItem.fromUri(url))

                setMediaSource(sorce)
                prepare()
            }
    }



    LaunchedEffect(key1 =true){
        exoPlayer.playWhenReady = true
    }
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
    println("CurrentTimeLine: ${exoPlayer.clock}")

    DisposableEffect(true) {
        onDispose { exoPlayer.release() }
    }


    var valor by remember { mutableStateOf(0) }
    var VerticalState = rememberLazyListState(valor,size.toInt())
    var sliderPosition by remember { mutableStateOf(valor.toFloat()) }

    var isScrolling by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D6F9C))){

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
                    .weight(8.5f),
             state = VerticalState){

                lst_1.forEachIndexed{ index, i ->


                    item {


                        if(exoPlayer.currentPosition >= range[index].toLong()){
                            lyric_Color = Color.White

                        }else{
                            lyric_Color = Color(0xFF181E22)
                        }

                        Text(
                            text = i,
                            textAlign = TextAlign.Center,
                            color = lyric_Color,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .fillMaxWidth()

                        )
                        Text(
                            text = lst_2[index],
                            textAlign = TextAlign.Center,
                            color = lyric_Color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(20.dp))
                     }

                }

                sliderPosition = VerticalState.firstVisibleItemIndex.toFloat()
                println(sliderPosition.toString())

                /*
                if(! VerticalState.isScrollInProgress){

                }*/

                /*
                CoroutineScope(Dispatchers.Main).launch {
                    VerticalState.scrollToItem(valor)
                }*/




            }

            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxSize()){

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally){

                    Text(
                        text ="${exoPlayer.currentPosition}",
                        color = Color.White,
                        fontSize = 20.sp)
                    
                    Icon(
                        painter = painterResource(id =  if(play)R.drawable.pause else R.drawable.play),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable {


                                if (play) {
                                    exoPlayer.playWhenReady = false;
                                    exoPlayer.playbackState;

                                } else {
                                    exoPlayer.playWhenReady = true
                                    exoPlayer.playbackState;
                                }
                                play = !play

                            }
                            .size(85.dp),
                        tint = Color(0xFFFAFAFA))
                }



            }

            println()

        }

    }




}

@Composable
fun Exo() {



}