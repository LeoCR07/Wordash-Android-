package com.example.betadiccompose.ui.Foundation.GamesScreen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Foundation.ScreenVocabulary.TEXT_SCALE_REDUCTION_INTERVAL
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Sort(
    viewModel: VocabularyViewModel,
    lista: List<DataWorld>
){

    /********************   dato  ************************/

    val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

    var textSize_2 by remember {
        mutableStateOf(28.sp)
    }


    var word: DataWorld = remember {
        viewModel.getOneWord(lista)
    }


    var contador by remember{ mutableStateOf(0) }

    var temp by remember {
        mutableStateOf(word.World_1.trim().toLowerCase())
    }

    val mediaPlayer = MediaPlayer().apply {
        println("audio1000")

        setAudioAttributes(

            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    /*********  Sonido ***********/
    if(viewModel.step != 19){
        LaunchedEffect(key1 = true){
           // viewModel.soundFromUrl(id= word.id)
            viewModel.media(word.id,mediaPlayer)
        }
    }
    println("titulo ${temp}")

    var wordRandom by remember {
        mutableStateOf(RandomTextos(temp))
    }

    var frasesRandom by remember {
        mutableStateOf(RandomFrases(temp))
    }



    /********************   compose  ************************/
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {


        if(viewModel.step != 19){
            Button(
                onClick = {
                    viewModel.media(word.id,mediaPlayer)
                          //viewModel.soundFromUrl(id = word.id)
                },
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(34.dp))
                , colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color(0xFF0CB1B1)
                )){
                Icon(
                    Icons.Rounded.VolumeUp,
                    contentDescription = null,
                    modifier = Modifier
                        .size(65.dp))
            }


            Spacer(modifier = Modifier.height(25.dp))
        }


        if(viewModel.step == 19){

            androidx.compose.material.Text(
                text = "Traduce y escribe",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )

            Local_Animation(
                animacion = R.raw.translate
                ,modifier = Modifier.size(200.dp),
                speed = 0.2f)

            androidx.compose.material.Text(
                text = word.World_2,
                fontWeight = FontWeight.SemiBold,
                fontSize = textSize_2,
                textAlign = TextAlign.Center,
                maxLines = 1,
                onTextLayout = { textLayoutResult ->
                    val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                    if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                        textSize_2 = textSize_2.times(TEXT_SCALE_REDUCTION_INTERVAL)
                    }
                }

            )
        }


        if(viewModel.step !=19){
            androidx.compose.material.Text(
                text = "Escribe lo que escuches",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        var textSize = 25.sp
        var result by remember{ mutableStateOf("") }


        Box(modifier =
        Modifier
            .fillMaxWidth()
            .height(50.dp)){

            Text(
                modifier = Modifier
                    .fillMaxSize(),
                text = result,
                textAlign = TextAlign.Center,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = textSize,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                    if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                        textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                    }
                })
        }

        Spacer(modifier = Modifier.height(10.dp))


        if(temp.length >13){

            LazyHorizontalGrid(
                rows = GridCells.Adaptive(60.dp ),
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier
                    .height(300.dp)
            ){
                frasesRandom.forEachIndexed { i, c ->
                    item() {
                        var BtnEnabled by rememberSaveable  { mutableStateOf(true) }

                        if (contador == 0){
                            BtnEnabled = true
                        }

                        Button(
                            onClick = {

                                val endIndex = if (contador+2 <= temp.length) contador+2 else temp.length
                                val twoChars = temp.substring(contador, endIndex)

                                println("temp ${temp[contador]}")
                                println("twoChars ${twoChars}")
                                println("c ${c}")

                                if(/*temp[contador]+""*/ twoChars == c){
                                    viewModel.SoundFromLocal(R.raw.dm)
                                    result+=c
                                    contador+=2
                                    BtnEnabled = false

                                }else{
                                    viewModel.SoundFromLocal(R.raw.back)
                                    result = ""
                                    contador = 0

                                }

                                if(result == temp){
                                    viewModel.step++
                                    mediaPlayer.release()

                                    if(viewModel.step!=20){
                                        viewModel.SoundFromLocal(R.raw.rigth)
                                    }

                                }


                            },
                            enabled = BtnEnabled ,
                            modifier = Modifier
                                .padding(6.dp)
                                .height(60.dp)
                                .width(120.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color(0xFFF5F5F5).copy(1f) ,
                                backgroundColor = Color(0xFF0CB1B1)
                            ),
                            content = {
                                androidx.compose.material.Text(
                                    color = Color.White,
                                    text = c.toString(),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 25.sp)
                            }
                        )
                    }

                }
            }



        }else{
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(60.dp ),
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier
                    .height(300.dp)
            ){
                wordRandom.forEachIndexed { i, c ->

                    item() {

                        var BtnEnabled by rememberSaveable  { mutableStateOf(true) }

                        if (contador == 0){
                            BtnEnabled = true
                        }


                        Button(
                            onClick = {

                                if(temp[contador] == c){
                                    viewModel.SoundFromLocal(R.raw.dm)
                                    result+=c
                                    contador++
                                    BtnEnabled = false

                                }else{
                                    viewModel.SoundFromLocal(R.raw.back)
                                    result = ""
                                    contador = 0

                                }

                                if(result == temp){
                                    viewModel.step++
                                    mediaPlayer.release()

                                    if(viewModel.step!=20){
                                        viewModel.SoundFromLocal(R.raw.rigth)
                                    }

                                }


                            },
                            enabled = BtnEnabled ,
                            modifier = Modifier
                                .padding(6.dp)
                                .height(60.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color(0xFFF5F5F5).copy(1f) ,
                                backgroundColor = Color(0xFF0CB1B1)
                            ),
                            content = {
                                androidx.compose.material.Text(
                                    color = Color.White,
                                    text = c.toString(),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 25.sp)
                            }
                        )
                    }

                }
            }
        }



    }
}

fun RandomFrases(lst:String):MutableList<String> {

    var randomvalue :Int
    var aux:String
    val listaDeCadenas = ArrayList<String>()


    for (i in 0 until lst.length step 2) {
        val endIndex = if (i+2 <= lst.length) i+2 else lst.length
        val twoChars = lst.substring(i, endIndex)
        listaDeCadenas.add(twoChars)
    }

    var lista = listaDeCadenas.toMutableList()

    for (i in (0 until listaDeCadenas.size)){

        randomvalue = (lista.indices).random()
        aux = lista[randomvalue]
        lista[randomvalue] = lista[i]
        lista[i] = aux
    }

    return lista

}

fun RandomTextos(lst:String):MutableList<Char> {

    var lista = lst.toMutableList()
    var randomvalue :Int
    var aux:Char

    for (i in (0 until lst.length)){

        randomvalue = (lista.indices).random()
        aux = lista[randomvalue]
        lista[randomvalue] = lista[i]
        lista[i] = aux
    }

    return lista

}
