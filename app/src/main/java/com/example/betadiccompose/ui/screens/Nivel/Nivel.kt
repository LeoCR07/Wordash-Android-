package com.example.betadiccompose.ui.Foundation.ScreenNiveles

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.Game_Provider.Prefs
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.GamesScreen.*
import com.example.betadiccompose.ui.Foundation.Game.GameOver.GameOverScreen
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.NavToBackDialog
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.secondTopAppBarLevel
import com.example.betadiccompose.ui.Foundation.Shared.NoIntenet
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.Hard
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.ReviewScreen
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.SpeakToTalk
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.WrongWritten
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun Nivel(
    viewModel: VocabularyViewModel,
    onBack:()->Unit,
    NavToStudy:()->Unit
) {

    val lst: MutableState<List<DataWorld>> = remember { mutableStateOf(listOf()) }
    lst.value = viewModel.lstnivel.value


    if (viewModel.step != 0) {
        NavToBackDialog(onBack,)
    }


    LaunchedEffect(key1 = true) {
        viewModel.step = 1
    }

    //var porcentaje = index / 19f
    viewModel.step
    var porcentaje = viewModel.step / 19f
    porcentaje *= 100

    if (viewModel.isloding_level.value) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Local_Animation(
                R.raw.animacion, modifier = Modifier
                    .size(400.dp)
            )

            Text(
                text = "El nivel esta cargando",
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )


        }

    } else {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (viewModel.step != 0 && viewModel.step != 19) {
                secondTopAppBarLevel(porcentaje, onBack)
                Spacer(modifier = Modifier.height(40.dp))
            }

            if (lst.value.isNotEmpty()) {
                when (viewModel.step) {
                    0 -> {
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
                            viewmodel = viewModel
                        )
                    }
                    1, 6, 13 -> Easy(

                        lista = lst.value,
                        viewModel = viewModel,
                        onMediaClick = { item, id ->
                            if (id == item.id) {
                                viewModel.SoundFromLocal(R.raw.rigth)
                                viewModel.step++
                            } else {
                                viewModel.step = 0

                            }
                        })
                    3, 11, 8 -> Hard(
                        lista = lst.value,
                        viewModel = viewModel,
                        onMediaClick = { item, id ->
                            if (id == item.id) {
                                viewModel.SoundFromLocal(R.raw.rigth)
                                viewModel.step++
                            } else {
                                viewModel.step = 0
                            }

                        })
                    5, 9, 16 -> SpeakToTalk(
                        lista = lst.value,
                        viewModel = viewModel,
                        index = viewModel.step
                    )

                    4, 10, 15 -> Sonido(
                        lista = lst.value,
                        viewModel = viewModel,
                        onMediaClick = { item, id ->

                            if (id == item) {
                                viewModel.SoundFromLocal(R.raw.rigth)
                                viewModel.step++
                            } else {
                                viewModel.step = 0
                            }
                        },
                        index = viewModel.step
                    )
                    2, 14, 18 -> WrongWritten(
                        lista = lst.value,
                        viewModel = viewModel,
                        onMediaClick = { item, id ->
                            if (id == item.id) {
                                viewModel.SoundFromLocal(R.raw.rigth)
                                viewModel.step++
                            } else {
                                viewModel.step = 0
                            }

                        })
                    7, 12, 17 -> Sort(
                        viewModel = viewModel,
                        lista = lst.value
                    )
                    19 -> {
                        ReviewScreen(
                            NavToNext = {

                            },
                            NavToAgain = {
                                viewModel.step = 1
                            },
                            NavToExit = {
                                onBack()
                            },
                            viewmodel = viewModel
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
                        viewmodel = viewModel
                    )
                }
            }

        }
    }


    /*
    if(!viewModel.IsConnected()){
        NoIntenet()
    }else {
    }*/

}


