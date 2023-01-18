package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Button.OutlinedButtonSample
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun Easy (
    viewModel: VocabularyViewModel,
    prefs: Prefs,
    onMediaClick: (DataWorld,Int) -> Unit,
    lista: List<DataWorld>
) {
    var idAux = 0
    var lstAux: List<DataWorld> = emptyList()


    lstAux =  viewModel.GetEasy(lista)


    var lstOrder = remember {
        lstAux
        // viewModel.GetEasy(lista)
    }

    idAux = lstOrder[0].id

    val lstrandon = RandomText(lstOrder)
    var id = remember {
        idAux
    }


    /*********  Sonido ***********/
    LaunchedEffect(key1 = id){
        //com.example.betadiccompose.ui.Foundation.Shared.Sonido(id,prefs.GetCategory())
        viewModel.Sonido(id,prefs.GetCategory())
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(
            onClick = {
                com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                    id,
                    prefs.GetCategory(),
                    vocalview = viewModel
                )
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
            text="Escucha con atención",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn() {
            items(lstrandon) { item ->
                OutlinedButtonSample(
                    onMediaClick = { onMediaClick(item,id) },
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


fun RandomText(lst:List<DataWorld>): MutableList<DataWorld> {

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
