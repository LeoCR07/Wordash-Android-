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
import org.intellij.lang.annotations.Language

@Composable
fun LanguageScreen(
    onBack:()->Unit,
    viewmodel:VocabularyViewModel) {

    var code by remember{
        mutableStateOf(viewmodel.GetCode())
    }

    MyApp(viewModel = viewmodel, content =  {

        val lst = viewmodel.GetFilesLocalLanguages()

        var value by remember {
            mutableStateOf(viewmodel.GetLocalLenguage())
        }


        val sortedList = lst.sortedBy { it.local }

        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = viewmodel.GetSettings().YourLanguage[code], navToSettings = onBack)
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){

                    items(sortedList) { item ->
                        ItemOpcion(label = item.local, value = value , selected = item.language ,click = {
                            value = item.language
                           viewmodel.SaveLocalLanguage(item.language)
                        })
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        )


    })
}