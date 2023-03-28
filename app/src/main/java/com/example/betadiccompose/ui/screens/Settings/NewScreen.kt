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
fun NewScreen(viewmodel:VocabularyViewModel) {
    MyApp(viewModel = viewmodel, content =  {
        val lst = listOf("Spanish","English")

        var value by remember {
            mutableStateOf(viewmodel.GetLearnLenguage())
        }

        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = "Ajustes")
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){

                    items(lst) { item ->
                        ItemOpcion(label = item , value = value  , selected = item ,click = {                    value = item
                            value = item
                            viewmodel.saveLearnLenguage(item)
                        })
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        )


    })
}