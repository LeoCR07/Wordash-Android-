package com.example.betadiccompose.ui.Foundation.ScreenNiveles

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.GamesScreen.*
import com.example.betadiccompose.ui.Foundation.Shared.Animation
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.SonidoLocal
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.SonidoWrong
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Nivel(viewModel: VocabularyViewModel, prefs: Prefs, context: Context) {

    //var lst: List<DataWorld> = emptyList()
    val lst : MutableState<List<DataWorld>> = remember { mutableStateOf(listOf()) }
    var index by remember{ mutableStateOf(0) }
    lst.value =  viewModel.lstnivel.value
    println("Nivel : )")

    if(viewModel.isloding_level.value){
        Animation(
            R.raw.animacion,modifier = Modifier
            .fillMaxSize())

    }else {

        if (lst.value.isNotEmpty()) {
            when (index) {
                0,2,12-> Easy(
                    index = index,
                    lista = lst.value,
                    viewModel =  viewModel,
                    prefs = prefs,
                    onMediaClick = { item ,id ->

                        if(id == item.id){
                            SonidoLocal(context)
                            println("es igual")
                            index++
                        }else {
                            SonidoWrong(context)
                            println("Es diferente")
                        }
                    })
                8,15,-> Hard(viewModel,0) { viewModel.Step.value += 1}
                9,16 -> Hard(viewModel,1) { viewModel.Step.value += 1}
                4,11 -> SpeakToTalk(viewModel = viewModel) { viewModel.Step.value += 1}
                3,5,13-> Sonido( index = index,
                    lista = lst.value,
                    viewModel =  viewModel,
                    prefs = prefs,
                    onMediaClick = { item ,id ->

                        println("Eligida: ${item} --  Respuesta: $id " )

                        if(id == id){
                            SonidoLocal(context)
                            index++
                        }else {
                            SonidoWrong(context)
                        }
                    })
                1,10,14-> WrongWritten(
                    index = index,
                    lista = lst.value,
                    viewModel =  viewModel,
                    prefs =  prefs,
                    onMediaClick = {item,id->
                        if(id == item.id){
                            SonidoLocal(context)
                            println("es igual")
                            index++
                        }else {
                            SonidoWrong(context)
                            println("Es diferente")
                        }

                    })
                6,7-> Sort(viewModel,2) { viewModel.Step.value += 1}
                17-> asocie(viewModel) { viewModel.Step.value += 1}
                else -> Hard(viewModel,2) { viewModel.Step.value += 1}
            }


            //ActualizarLienzo(index,id,lsteasy,viewModel,prefs)
        }


    }



}


