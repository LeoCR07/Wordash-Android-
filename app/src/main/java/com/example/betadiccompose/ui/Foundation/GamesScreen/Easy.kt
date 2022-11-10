package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Button.OutlinedButtonSample
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun Easy (
    viewModel: VocabularyViewModel,
    prefs: Prefs,
    onMediaClick: (DataWorld,Int) -> Unit,
    index: Int,
    lista: List<DataWorld>
) {
    var porcentaje = index / 18f
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

    println("/******** Easy **********/")
    for (e in lstrandon){
        println("${e.id}: ${e.World_1}")
    }

    /*********  Sonido ***********/
    LaunchedEffect(key1 = true){
        com.example.betadiccompose.ui.Foundation.Shared.Sonido(id,prefs.GetCategory())
    }

    Column(
        modifier =Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

        GetLogo(icon = true, titulo = "Days")
        NameGame(titulo = "Days")
        LinearProgressIndicator(
            modifier = Modifier
                .height(4.dp)
                .width(340.dp),
            progress = porcentaje,
        )

        fondo("https://d1i3grysbjja6f.cloudfront.net/IMG/${prefs.GetCategory()}/${id}.jpg")

        Icon(
            Icons.Rounded.VolumeUp,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    println("Sonido boton")
                    com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                        id,
                        prefs.GetCategory()
                    )
                })


        LazyColumn() {
            items(lstrandon) { item ->
                OutlinedButtonSample(
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp),
                    onMediaClick = { onMediaClick(item,id) },
                    lsteasy = item
                )
                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }


}

@Composable
fun fondo(url:String) {

    Box(
        modifier = Modifier
            .height(220.dp)
            .width(280.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center

    ) {

        AsyncImage(
            model = url,
            modifier = Modifier
                .fillMaxSize(),

            contentScale = ContentScale.Crop,
            contentDescription = null
        )


    }

}

public fun RandomText(lst:List<DataWorld>): MutableList<DataWorld> {

    var lista = lst.toMutableList()
    //var lista = emptyList<DataWorld>()

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
