package com.example.betadiccompose.ui.screens.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Runtime.MyApp

import com.example.betadiccompose.ui.Foundation.Settings.ItemOpcion
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*


@Composable
fun ThemeScreen(
    onBack:()->Unit,
    viewmodel:VocabularyViewModel,click:()->Unit) {
    var code by remember{
        mutableStateOf(viewmodel.GetCode())
    }

    MyApp(viewModel = viewmodel, content = {

        val lst = listOf(
            viewmodel.GetSettings().DayNight[code]!!,
            viewmodel.GetSettings().Night[code]!!,
            viewmodel.GetSettings().day[code])!!

        var value by remember {
            mutableStateOf(viewmodel.GetTheme())
        }

        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = viewmodel.GetSettings().ApplicationTheme[code], navToSettings = onBack)
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){

                    lst.forEachIndexed { index, item ->
                        
                        item{
                            ItemOpcion(
                                label = item!!.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                },
                                value = value.toString() , 
                                click = { 
                                    value = index 
                                    viewmodel.SetTheme(index)
                                    click()
                                        }, 
                                selected = index.toString() )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                    
                }
            }
        )


    })
}