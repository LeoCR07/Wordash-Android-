package com.example.betadiccompose.ui.Foundation.GamesScreen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Button.OutlinedButtonSample
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun Easy (
    viewModel: VocabularyViewModel,
    onMediaClick: (DataWorld,Int,MediaPlayer) -> Unit,
    lista: List<DataWorld>
) {

    var code by remember {
        mutableStateOf(viewModel.GetCode())
    }
    var lstAux: List<DataWorld> = emptyList()
    lstAux =  viewModel.getEasy(lista)


    var lstOrder = remember {
        lstAux
    }


    val lstrandon = RandomText(lstOrder)

    var id = remember {
        lstOrder[0].id
    }

    val mediaPlayer = MediaPlayer().apply {
        println("audio1000")

      //  var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${viewModel.GetLearnLenguage()}/${viewModel.GetPath()}/${id}.mp3"

        setAudioAttributes(

            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()

        )
       // setDataSource(urlAudio)
       // prepare()
    }

    /*********  Sonido ***********/
    LaunchedEffect(key1 = id){
      //  viewModel.soundFromUrl(id= id)
        viewModel.media(id,mediaPlayer)
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(
            onClick = {
                      viewModel.media(id,mediaPlayer)
                      //viewModel.soundFromUrl(id=id)
            },
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(34.dp))
            , colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF03A9F4)
            )){
            Icon(
                Icons.Rounded.VolumeUp,
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp))
        }

        Spacer(modifier = Modifier.height(25.dp))


        Text(
            text= viewModel.GetSettings().ListenCarefully[code]!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn() {
            items(lstrandon) { item ->
                OutlinedButtonSample(
                    onMediaClick = { onMediaClick(item,id,mediaPlayer) },
                    word = item.World_1,
                    modifier = Modifier
                        .width(300.dp)
                        .height(70.dp),
                    BorderColor =Color(0xFF03A9F4),
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }


    }
}

public fun RandomText(lst:List<DataWorld>): MutableList<DataWorld> {

    var lista = lst.toMutableList()

    var randomvalue = 0
    var temp = ""
    var aux:DataWorld

    for (i in (0 until lst.size)){
        randomvalue = (lista.indices).random()
        aux = lista[randomvalue]
        lista[randomvalue] = lista[i]
        lista[i] = aux
    }

    return lista
}