package com.example.betadiccompose.ui.Foundation.ScreenNiveles

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.GamesScreen.*
import com.example.betadiccompose.ui.Foundation.GamesScreen.GameOver.GameOverScreen
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.NavToBackDialog
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.SonidoLocal
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.SonidoWrong
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.TopAppBarLevel
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Nivel(
    viewModel: VocabularyViewModel,
    prefs: Prefs,
    context: Context,
    onBack:()->Unit,
    NavToStudy:()->Unit
) {

    //var lst: List<DataWorld> = emptyList()
    val lst : MutableState<List<DataWorld>> = remember { mutableStateOf(listOf()) }
    var index by remember{ mutableStateOf(1) }
    lst.value =  viewModel.lstnivel.value
    println("Nivel : )")

    if(viewModel.step != 0){
        NavToBackDialog(onBack,)
    }


    LaunchedEffect(key1 = true){
        viewModel.step = 1
    }

    //var porcentaje = index / 19f
    viewModel.step
    var porcentaje = viewModel.step / 19f
    porcentaje *= 100

    if(viewModel.isloding_level.value){
        Animation(
            R.raw.animacion,modifier = Modifier
                .fillMaxSize())

    }else {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            if(viewModel.step != 0 && viewModel.step != 19){
                TopAppBarLevel(porcentaje,R.drawable.listening,onBack)
                Spacer(modifier = Modifier.height(40.dp))
            }

            if (lst.value.isNotEmpty()) {
                when (viewModel.step) {
                    0-> {
                        GameOverScreen(
                            NavToAgain = {
                                         viewModel.step = 1
                            },
                            NavToExit = {
                                        onBack()
                            },
                            NavToStudy = {
                                NavToStudy()
                            },
                            context = context)
                    }
                    1,6,13-> Easy(
                       // index = viewModel.step,
                        lista = lst.value,
                        viewModel =  viewModel,
                        prefs = prefs,
                        onMediaClick = { item ,id ->
                            if(id == item.id){
                                SonidoLocal(context)
                                println("es igual")
                                // index++
                                viewModel.step++
                            }else {
                                println("Es diferente")
                                viewModel.step = 0

                            }
                        })
                    3,11,8-> Hard(
                        lista = lst.value,
                        viewModel =  viewModel,
                        prefs = prefs,
                        onMediaClick ={item ,id ->
                            if(id == item.id){
                                SonidoLocal(context)
                                viewModel.step++
                            }else {
                                viewModel.step = 0
                            }

                        })
                    5,9,16-> SpeakToTalk(
                        lista = lst.value,
                        viewModel =  viewModel,
                        context = context,
                        index =  viewModel.step )

                    4,10,15-> Sonido(
                        lista = lst.value,
                        viewModel =  viewModel,
                        prefs = prefs,
                        onMediaClick = { item ,id ->

                            println("Eligida: ${item} --  Respuesta: $id " )

                            if(id == item){
                                SonidoLocal(context)
                                //index++
                                viewModel.step++
                            }else {
                                viewModel.step = 0
                            }
                        },
                        index =  viewModel.step )
                    2,14,18-> WrongWritten(
                        lista = lst.value,
                        viewModel =  viewModel,
                        prefs =  prefs,
                        onMediaClick = {item,id->
                            if(id == item.id){
                                SonidoLocal(context)
                                println("es igual")
                                //index++
                                viewModel.step++
                            }else {
                                viewModel.step = 0
                                println("Es diferente")
                            }

                        })
                    7,12,17-> Sort(
                        viewModel = viewModel,
                        lista = lst.value,
                        prefs = prefs,
                        animation =  0)
                    19->{
                        ReviewScreen(
                            NavToNext = {

                            },
                            NavToAgain = {
                                viewModel.step = 1
                            },
                            NavToExit = {
                                onBack()
                            },
                            context = context
                        )
                    }
                    else -> ReviewScreen(
                        NavToNext = {

                        },
                        NavToExit = {
                            onBack()
                        },
                        NavToAgain = {
                            viewModel.step = 1
                        },
                        context = context
                    )
                }
            }

        }

    }




}


