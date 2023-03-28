package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.ExitDialog
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.Settings.ItemSettings

@Composable
fun SettingsScreen(
    SignOut:() ->Unit,
    NavToLanguage: () -> Unit,
    NavToNew: () -> Unit,
    NavToTheme: () -> Unit,
    NavToCredits :() ->Unit,
    viewmodel: VocabularyViewModel,
) {

    MyApp(viewModel = viewmodel, content = {

        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = "Ajustes")
            },
            content = {


                var showAlertDialog by remember { mutableStateOf(false) }

                if(showAlertDialog){
                    ExitDialog(
                        hideAlertDialog = { showAlertDialog = false },
                        showAlertDialog = { showAlertDialog = true },
                        texto = "Estas seguro que  desea cerrar sesion ?",
                        onBack = {
                            SignOut()
                        }
                    )
                }


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    item {

                        Spacer(modifier = Modifier.height(6.dp))
                        ItemSettings( R.drawable.languages_1,"Idioma natal", NavTo = NavToLanguage,)
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings( if(viewmodel.GetLearnLenguage() == "English" ) R.drawable.flag_states else R.drawable.flag_spain,
                            "Idioma para aprender",NavTo = NavToNew,)
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings( R.drawable.themes,"Tema de aplicacion",NavTo = NavToTheme,)
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings( R.drawable.people,"Creditos",NavTo = NavToCredits,)

                        Spacer(modifier = Modifier.height(6.dp))
                        ItemSettings(
                            R.drawable.signout,
                            "Cerrar session",
                            NavTo = {
                                showAlertDialog = true
                            },)

                    }
                }
            }
            )

    })
}


