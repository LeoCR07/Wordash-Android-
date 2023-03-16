package com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.GamesScreen.RandomText
import com.example.betadiccompose.ui.Foundation.Shared.Button.OutlinedButtonSample
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel



@Composable
fun Hard (
    viewModel: VocabularyViewModel,
    onMediaClick: (DataWorld, Int,MediaPlayer) -> Unit,
    lista: List<DataWorld>,
) {
    var idAux = 0
    var lstAux: List<DataWorld> = emptyList()


    lstAux =  viewModel.getEasy(lista)


    var lstOrder = remember {
        lstAux
        // viewModel.GetEasy(lista)
    }

    idAux = lstOrder[0].id

    val lstrandon = RandomText(lstOrder)
    var id = remember {
        idAux
    }

    println("/******** Hard **********/")
    for (e in lstrandon){
        println("${e.id}: ${e.World_1}")
    }

    /*
    var   urlAudio by remember {
        mutableStateOf("https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${id}.mp3")
    }*/
    val mediaPlayer = MediaPlayer().apply {
        println("audio1000")

       // var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${id}.mp3"

        setAudioAttributes(

            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()

        )
        //setDataSource(urlAudio)
      ///  prepare()
    }

    /*********  Sonido ***********/
    LaunchedEffect(key1 = true){
        viewModel.media(id,mediaPlayer)
        //viewModel.soundFromUrl(id=id)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                viewModel.media(id,mediaPlayer)
               // viewModel.soundFromUrl(id=id)
            },
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(34.dp))
            , colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFFF55252)
            )){
            Icon(
                Icons.Rounded.VolumeUp,
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp))
        }

        Spacer(modifier = Modifier.height(25.dp))


        Text(
            text="Como se dice ???",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn() {
            items(lstrandon) { item ->
                OutlinedButtonSample(
                    onMediaClick = {
                        onMediaClick(item,id,mediaPlayer)
                                   },
                    word = item.World_2,
                    modifier = Modifier
                        .width(300.dp)
                        .height(70.dp),
                    BorderColor = Color(0xFFF55252),
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }




    /***********************/



}


