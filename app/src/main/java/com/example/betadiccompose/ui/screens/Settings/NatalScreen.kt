package com.example.betadiccompose.ui.screens.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.LabelledRadioButton
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun OpcionScreen(
    type:Int,
    viewmodel: VocabularyViewModel) {

    var lst = emptyList<String>()
    val lstNatalName = listOf("Español","English","Português","Deutsch","Nederlands","Italiano","Français")
    val lstNatalValue = listOf("Spanish","English","Portuguese","German","Dutch","Italian","French")

    val lstNewValue = listOf("Spanish","English")
    val lstNewName = listOf("Español","English")

    var value by remember {
        mutableStateOf("")
    }

    when(type){
        1-> {
            lst = lstNatalValue
            value = viewmodel.GetLocalLenguage()
        }
        2 -> {
            lst = lstNewValue
            value = viewmodel.GetLearnLenguage()

        }
    }




    MyApp {
        Scaffold(
            topBar = {
                TopApp(opcion = 3, title = "Ajustes", viewModel = viewmodel)
            },
            content = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    if(lst.size == 2){
                        itemsIndexed(lst){i,item ->
                            Spacer(modifier = Modifier.height(5.dp))
                            LabelledRadioButton(
                                label = lstNewName[i],
                                selected = item,
                                onClick = {
                                    value = item
                                    viewmodel.SaveLearnLenguage(item)
                                },
                                value = value)
                        }
                    }else{
                        itemsIndexed(lst){i,item ->
                            Spacer(modifier = Modifier.height(5.dp))
                            LabelledRadioButton(
                                label = lstNatalName[i],
                                selected = item,
                                onClick = {
                                    value = item
                                    viewmodel.SaveLocalLanguage(item)
                                },
                                value = value)
                        }
                    }

                }
                
            })

    }

}