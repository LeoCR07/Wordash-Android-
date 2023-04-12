package com.example.betadiccompose.ui.screens.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Runtime.MyApp

import com.example.betadiccompose.ui.Foundation.Settings.ItemOpcion
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun NewScreen(
    onBack:()->Unit,
    viewmodel:VocabularyViewModel) {

    var code by remember{
        mutableStateOf(viewmodel.GetCode())
    }
    MyApp(viewModel = viewmodel, content =  {
        val lst = listOf(
            viewmodel.GetSettings().Spanish[code]!!,
            viewmodel.GetSettings().English[code]!!)

        val lstValue = listOf("Spanish","English")

        var value by remember {
            mutableStateOf(viewmodel.GetLearnLenguage())
        }

        println("Lenguaje nuevo ${value}")

        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = viewmodel.GetSettings().YourNextLanguage[code], navToSettings = onBack)
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){

                    lst.forEachIndexed { i, item ->
                        item {
                            ItemOpcion(label = item , value = value  , selected = lstValue[i] ,click = {
                                value = lstValue[i]
                                viewmodel.saveLearnLenguage(lstValue[i])
                            })
                            Spacer(modifier = Modifier.height(5.dp))
                        }

                    }

                }
            }
        )


    })
}