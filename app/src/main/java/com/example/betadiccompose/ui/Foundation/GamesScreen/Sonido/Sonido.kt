package com.example.betadiccompose.ui.Foundation.GamesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.GamesScreen.Sonido.ClickSonido
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.Button.ButtonWithIconSample
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Sonido(
    viewModel: VocabularyViewModel,
    prefs: Prefs,
    onMediaClick: (Int,Int) -> Unit,
    lista: List<DataWorld>,
    index :Int) {

    var idAux = 0
    var lstAux: List<DataWorld> = emptyList()

    lstAux =  viewModel.GetSoundChoose(lista)

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

    println("/******** Easy **********/")
    for (e in lstrandon){
        println("${e.id}: ${e.World_1}")
    }



    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

       //BackgroundImg("https://d1i3grysbjja6f.cloudfront.net/IMG/${prefs.GetCategory()}/${id}.jpg")

        Text(
            text="Selecciona el audio correcto",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )


        Animation(
            animacion = R.raw.dancing,
            modifier = Modifier
                .size(200.dp),
            speed = 0.6f
        )


        if(index == 3){
            Text(
                text="Como se dice ${lstOrder [0].World_1}",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
            //  Text(text = "${lstOrder [0].World_1}", fontSize = 30.sp)
        }else{
            Text(
                text="${lstOrder [0].World_2}",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
        }

        var select by remember { mutableStateOf(-1) }
        Spacer(modifier = Modifier.height(1.dp))

        var opc by remember { mutableStateOf(0) }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            ClickSonido(value = 0,select = select, click = {
                select = 0
                opc = lstrandon[0].id
                com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                    lstrandon[0].id,
                    prefs.GetCategory(),
                    vocalview = viewModel
                )
            })
            ClickSonido(value = 1,select = select, click = {
                select = 1
                opc = lstrandon[1].id
                com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                    lstrandon[1].id,
                    prefs.GetCategory(),
                    vocalview = viewModel
                )
            })
            ClickSonido(value = 2,select = select, click = {
                select = 2
                opc = lstrandon[2].id
                com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                    lstrandon[2].id,
                    prefs.GetCategory(),
                    vocalview = viewModel
                )
            })
        }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            ClickSonido(value = 3,select = select, click = {
                select = 3
                opc = lstrandon[3].id
                com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                    lstrandon[3].id,
                    prefs.GetCategory(),
                    vocalview = viewModel
                )
            })
            ClickSonido(value = 4,select = select, click = {
                select = 4
                opc = lstrandon[4].id
                com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                    lstrandon[4].id,
                    prefs.GetCategory(),
                    vocalview = viewModel
                )
            })
            ClickSonido(value = 5,select = select, click = {
                select = 5
                opc = lstrandon[5].id
                com.example.betadiccompose.ui.Foundation.Shared.Sonido(
                    lstrandon[5].id,
                    prefs.GetCategory(),
                    vocalview = viewModel
                )
            })
        }

        Spacer(modifier = Modifier.height(10.dp))

        if(select != -1){
            ButtonWithIconSample(
                modifier = Modifier
                    .width(220.dp)
                    .height(60.dp),
                onMediaClick = { onMediaClick(opc,id) })
        }

    }
}