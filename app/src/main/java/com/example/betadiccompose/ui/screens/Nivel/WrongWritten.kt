package com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.Game_Provider.Prefs
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.GamesScreen.RandomText
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.Button.ElevatedButtonSample
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun WrongWritten(
    viewModel: VocabularyViewModel,
    onMediaClick: (DataWorld,Int) -> Unit,
    lista: List<DataWorld>,
) {

    var idAux = 0
    var lstAux: List<DataWorld> = emptyList()


    lstAux =  viewModel.getWrongWritten(lista)


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
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text="¿Cual es la opción bien escrita?",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 29.sp,
            textAlign = TextAlign.Center
        )

        Local_Animation(animacion = R.raw.searching,
            modifier = Modifier.size(200.dp))

        Spacer(modifier = Modifier.height(0.dp))

        LazyColumn() {
            items(lstrandon) { item ->
                ElevatedButtonSample(
                    modifier = Modifier
                        .width(300.dp)
                        .height(60.dp),
                    onMediaClick = { onMediaClick(item,id)  },
                    word = item.World_1
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }


}