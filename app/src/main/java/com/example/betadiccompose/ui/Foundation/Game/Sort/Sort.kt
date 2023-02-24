package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.Game_Provider.Prefs
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Game.Sort.handsentence
import com.example.betadiccompose.ui.Foundation.Game.Sort.handwriting
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Sort(
    viewModel: VocabularyViewModel,
    lista: List<DataWorld>
){

    /********************   datos  ************************/
    var wordAux: DataWorld

    wordAux =  viewModel.getOneWord(lista)
    println(wordAux)
   // var WordRemember = remember { wordAux }

    var WordRemember = remember { wordAux }
    var id = remember { WordRemember.id }

    var palabra = WordRemember.World_1

    /*********  Sonido ***********/
    LaunchedEffect(key1 = true){
        viewModel.soundFromUrl(id=id)
    }


    /********************   compose  ************************/
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {


        Button(
            onClick = {
                viewModel.soundFromUrl(id=id)
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


        androidx.compose.material.Text(
            text = "Escribe lo que escuches",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )


        Spacer(modifier = Modifier.height(12.dp))

        var kind by remember{ mutableStateOf(true) }
        var result by remember{ mutableStateOf("") }
        var contador by remember{ mutableStateOf(0) }

        var lstId : ArrayList<Int> = remember {
            ArrayList<Int>()
        }
       // var cindex by remember{ mutableStateOf(0) }

        Text(
            text = result,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        var lst:ArrayList<String> = ArrayList<String>()
        var lstCompleta : MutableState<List<String>> = remember {
            mutableStateOf(listOf())
        }

        var lstSentes : MutableState<List<String>> = remember {
            mutableStateOf(listOf())
        }

        var lista:ArrayList<String> = remember {
            ArrayList()
        }


        LaunchedEffect(key1 = true ){
            if(CountSpaces(palabra)){
                for(e in palabra){
                    lst.add(e.toString())
                }
                lstCompleta.value = RandomTextos(lst).toList()

            }else{
                kind = false
                var temp :String = ""

                for (i in 0 until palabra.length){
                    temp+=palabra[i]
                    if(palabra[i] == ' ' || palabra.length-1 == i){
                        temp = temp.trimStart().trimEnd()
                        lista.add(temp)
                        temp = " "
                    }
                }
                lstCompleta.value= RandomTextos(lista).toList()
            }
        }

        if(kind){
            handwriting(
                lstId = lstId,
                lstCompleta = lstCompleta,
                onclick = ({item,index->
                    if (palabra.length != contador) {
                        if (item == palabra[contador].toString()) {
                            //add(item)
                            viewModel.SoundFromLocal(R.raw.dm)
                            result += item
                            contador++ //3
                            lstId.add(index)
                            //viewModel.getSound(R.raw.writing)

                            println("letra correcta")

                        } else {
                           // reset()
                            result = ""
                            lstId.clear()
                            println("letra incorrecta")
                            contador = 0 //5
                            viewModel.SoundFromLocal(R.raw.back)
                        }
                    }

                    if (palabra.length == contador) {
                        println("palabra completa")
                        viewModel.step++
                        viewModel.SoundFromLocal(R.raw.rigth)
                    }

                }))

        }else{

            handsentence(
                lstId = lstId,
                lstCompleta = lstCompleta,
                onclick = ({item,index->
                    //palabra.substring(0,9)
                    println("lista: ${lista[contador]}")

                    println(" palabra: ${item } ")

                    if(lista[contador]==item){
                        contador++
                        lstId.add(index)
                        result += item+" "
                        viewModel.SoundFromLocal(R.raw.dm)
                    }else{
                        result = ""
                        contador = 0
                        lstId.clear()
                        viewModel.SoundFromLocal(R.raw.back)
                    }

                    //igual
                    if(result.trim() ==palabra.trim()){
                        viewModel.step++
                        viewModel.SoundFromLocal(R.raw.rigth)
                    }


                }))
        }


    }
}


private fun CountSpaces(palabra:String):Boolean{
    var valor = true
    var contador = 0

    for(e in palabra){
        if(e == ' '){
            contador++
        }
    }

    if(contador > 1 || contador == 1 && palabra.length >= 10){
        valor = false
    }

    return valor
}

fun RandomTextos(lst:List<String>): List<String> {

    var lista = lst.toMutableList()
    var randomvalue :Int
    var temp:String
    var aux:String

    for (i in (0 until lst.size)){

        randomvalue = (lista.indices).random()
        aux = lista[randomvalue]
        lista[randomvalue] = lista[i]
        lista[i] = aux
    }


    return lista

}
