package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.Settings.ItemSettings

@Composable
fun SettingsScreen(
    NavToNatal: () -> Unit ,
    NavToNuevo: () -> Unit,
    NavToTema: () -> Unit ,
    viewmodel: VocabularyViewModel,
) {

    MyApp {
        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = "Ajustes")
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        Spacer(modifier = Modifier.height(6.dp))
                        ItemSettings( R.drawable.languages,"Idioma natal", NavTo = NavToNatal,)
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings( R.drawable.languages,"Idioma para aprender",NavTo = NavToNuevo,)
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings( R.drawable.theme,"Tema de aplicacion",NavTo = NavToTema,)
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings( R.drawable.people,"Creditos",NavTo = NavToNatal,)

                    }
                }
            }
            )

    }
}


