package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Provider.Provider
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.Runtime.MyApp

@Composable
fun GeneradorJsonScreen(provide: Provider) {

    MyApp {
        Scaffold {
            Column(
                modifier = Modifier.
                fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,


            ) {

                val textvalueI = remember{ mutableStateOf("") }
                val textvalueC = remember{ mutableStateOf("") }
                val checkvalue = remember{ mutableStateOf(false) }

                
                GetLogo(false,"Niveles")
                texto(txt = "Idioma",value= textvalueI)
                texto(txt = "Categoria", value = textvalueC)
                check(checkvalue = checkvalue)

                boton(textvalueC.value,textvalueI.value,check = checkvalue.value,provide = provide)


              //  GetListVocabulary(onMediaClick = onMediaClick, provider = provider)
            }
        }
    }

}
@Composable
fun check(checkvalue: MutableState<Boolean>) {
    Row( modifier = Modifier.fillMaxWidth()){
        Checkbox(

            checked = checkvalue.value, onCheckedChange ={valor-> checkvalue.value = valor} )
        Text(text = "Sub categoria")
    }
}


@Composable
fun texto(txt: String, value: MutableState<String>) {

    OutlinedTextField(
        value = value.value,
        onValueChange = {
        value.value = it },
        label = { Text(text = txt)},
        modifier = Modifier.fillMaxWidth()
    )

}


@Composable
fun boton(categoria: String, idioma: String, check: Boolean, provide: Provider) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {

                  provide.CreateWord(categoria= categoria,idioma = idioma,subv= check)
        },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        // Inner content including an icon and a text label
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier
                .size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Crear lista")
    }
}