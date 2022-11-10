package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.Shared.Button.ElevatedButtonSample
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun WrongWritten(
    viewModel: VocabularyViewModel,
    prefs: Prefs,
    onMediaClick: (DataWorld,Int) -> Unit,
    lista: List<DataWorld>,
    index: Int
) {

    var porcentaje = index / 18f
    var idAux = 0
    var lstAux: List<DataWorld> = emptyList()


    lstAux =  viewModel.GetWrongWritten(lista)


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

        Spacer(modifier = Modifier.height(45.dp))

    LazyColumn() {
        items(lstrandon) { item ->
            ElevatedButtonSample(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp),
                onMediaClick = { onMediaClick(item,id) },
                lsteasy = item
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

    }

        /*
    val porcentaje = index / 18f
    var lsteasy: List<DataWorld> = emptyList()

    var lst: List<DataWorld> = emptyList()
    lst =  viewModel.lstnivel.value

    var id = 0

    if (lst.isNotEmpty()) {

        lsteasy = remember { viewModel.GetEasy(lst) }
        id = remember { viewModel.getCurrentIdGame() }



    }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

        GetLogo(icon = true, titulo = "Days")
        NameGame(titulo = "Days")
        LinearProgressIndicator(
            modifier = Modifier
                .height(4.dp)
                .width(340.dp),
            progress = porcentaje
        )

        Spacer(modifier = Modifier.height(45.dp))

        LazyColumn(){
            items(lsteasy){ item ->
                ElevatedButtonSample(
                    modifier = Modifier
                        .width(300.dp)
                        .height(60.dp),
                    onMediaClick= { onMediaClick(item)},
                    lsteasy =  item)

                Spacer(modifier = Modifier.height(10.dp))
            }

        }

        /*
        ElevatedButtonSample(modifier = Modifier
            .width(300.dp)
            .height(60.dp),
            texto = lsteasy[0].World_1,
            click = onMediaClick(lsteasy[0]))
        Spacer(modifier = Modifier.height(10.dp))

        ElevatedButtonSample(
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
            texto =  lstWritten[1].World_1,
            click = click
        )
        Spacer(modifier = Modifier.height(10.dp))

        ElevatedButtonSample(
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
            texto = lstWritten[2].World_1,
            click = click
        )
        Spacer(modifier = Modifier.height(10.dp))

        ElevatedButtonSample(
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
            texto = lstWritten[3].World_1,
            click = click
        )

        */
    }*/
    }
}