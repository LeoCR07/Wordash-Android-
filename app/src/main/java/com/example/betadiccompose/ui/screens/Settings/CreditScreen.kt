package com.example.betadiccompose.ui.screens.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.Runtime.MyApp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun CreditScreen(viewmodel: VocabularyViewModel,  onBack:()->Unit,) {
var code by remember{
    mutableStateOf(viewmodel.GetCode())
}

    MyApp(viewModel = viewmodel, content =  {

        var credits = "Pixabay: Images used in the application.\n" +
                "Lottie: Animations used in the app.\n" +
                "Flaticons: Icons used in the application.\n" +
                "Dustyroom: Sounds used in the application.\n" +
                "\n" +
                "Special mention\n" +
                "Dario Herrera Melendez\n" +
                "Tracy Sanchez\n" +
                "Steven Gonzalez\n" +
                "\n" +
                "Application developer: Leonel Murillo Salazar"

        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = viewmodel.GetSettings().credits[code], navToSettings = onBack)
            },
            content = {

                Column() {
                    Spacer(modifier = Modifier.height(5.dp))
                    Box(modifier = Modifier.fillMaxSize()){
                        Text(
                            text = credits,
                            color = MaterialTheme.colors.secondaryVariant,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }


            }
        )


    })
}