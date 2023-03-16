package com.example.betadiccompose.ui.Foundation.ScreenNiveles

import android.app.Activity
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network_database.model.DataWorld
import com.example.betadiccompose.ui.Foundation.GamesScreen.*
import com.example.betadiccompose.ui.Foundation.Game.GameOver.GameOverScreen
import com.example.betadiccompose.ui.Foundation.MyBanner
import com.example.betadiccompose.ui.Foundation.MyTheme
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.example.betadiccompose.ui.Foundation.Shared.NavToBackDialog
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.PopUpNoLives
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.secondTopAppBarLevel
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.LoadingLevel
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.Hard
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.ReviewScreen
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.SpeakToTalk
import com.example.betadiccompose.ui.Foundation.Vocabulary.GamesScreen.WrongWritten
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun Nivel(
    viewModel: VocabularyViewModel,
    onBack:()->Unit,
    NavToStudy:()->Unit
) {
    val context = LocalContext.current

    MyTheme {
        val lst: MutableState<List<DataWorld>> = remember { mutableStateOf(listOf()) }
        lst.value = viewModel.lstnivel.value

        var opendialog = remember { mutableStateOf(false) }

        Scaffold(
            modifier = Modifier.background(MaterialTheme.colors.onPrimary),
            bottomBar = {
                MyBanner()
            }

        ) {

            if (viewModel.step != 0) {
                NavToBackDialog(onBack,)
            }


            LaunchedEffect(key1 = true) {
                viewModel.step = 1
            }

            //var porcentaje = index / 19f
            viewModel.step
            var porcentaje = viewModel.step / 20f
            porcentaje *= 100


            if (viewModel.isloding_level.value) {
                LoadingLevel()
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (viewModel.step != 0 && viewModel.step != 20) {
                        secondTopAppBarLevel(porcentaje, onBack)
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    if (lst.value.isNotEmpty()) {
                        when (viewModel.step) {
                            0 -> {


                                LaunchedEffect(key1 =true){
                                    CoroutineScope(Dispatchers.IO).launch {


                                        viewModel.getDataUser()
                                        viewModel.lessLives()

                                        if(viewModel.getLives() == 0){

                                            val currentDate = Calendar.getInstance().time
                                            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                            val formattedDate = dateFormat.format(currentDate)

                                            viewModel.SetFirstTime(formattedDate)

                                        }
                                    }

                                }


                                GameOverScreen(
                                    NavToAgain = {
                                        CoroutineScope(Dispatchers.IO).launch {

                                            if(viewModel.getLives() > 0){
                                                viewModel.step = 1
                                            }else{
                                                println("fecha ${viewModel.GetFisrtTime()}")
                                                opendialog.value = true
                                            }
                                        }
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
                            1, 6, 17 -> Easy(
                                lista = lst.value,
                                viewModel = viewModel,
                                onMediaClick = { item, id,media->

                                    if (id == item.id) {
                                        media.release()
                                        viewModel.SoundFromLocal(R.raw.rigth)
                                        viewModel.step++
                                    } else {
                                        viewModel.step = 0

                                    }
                                })
                            3, 11, 8, 13 -> Hard(
                                lista = lst.value,
                                viewModel = viewModel,
                                onMediaClick = { item, id ,media->
                                    if (id == item.id) {
                                        media.release()
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
                                onMediaClick = { item,id,m1,m2,m3,m4,m5,m6 ->


                                    if (id == item) {
                                        m1.release()
                                        m2.release()
                                        m3.release()
                                        m4.release()
                                        m5.release()
                                        m6.release()
                                        viewModel.SoundFromLocal(R.raw.rigth)
                                        viewModel.step++
                                    } else {
                                        viewModel.step = 0
                                    }
                                },

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

                            7, 12, 19 -> Sort(
                                viewModel = viewModel,
                                lista = lst.value
                            )
                            20 -> {


                                LaunchedEffect(key1 = true ){
                                    viewModel.ShowInterstitalLevel(context)
                                }


                                ReviewScreen(

                                    NavToNext = {

                                    },
                                    NavToExit = {
                                        onBack()
                                    },
                                    NavToAgain = {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            if(viewModel.getLives() > 0){
                                                viewModel.step = 1
                                            }else{

                                                opendialog.value = true

                                            }
                                        }

                                    },
                                    viewmodel = viewModel
                                )
                            }
                            else ->{

                            }




                        }
                    }

                }
            }


        }

        PopUpNoLives(
            show = opendialog.value,
            dimisissDialog = {opendialog.value = false},
            viewModel = viewModel,
            time = false
        )

    }


}

