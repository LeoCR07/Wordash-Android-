package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.ExitDialog
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.Settings.ItemSettings
import java.util.*

@Composable
fun SettingsScreen(
    SignOut: () -> Unit,
    NavToLanguage: () -> Unit,
    NavToNew: () -> Unit,
    NavToTheme: () -> Unit,
    NavToCredits: () -> Unit,
    viewmodel: VocabularyViewModel,
    onBack: () -> Unit,
) {

    var code by remember {
        mutableStateOf(viewmodel.GetCode())
    }

    MyApp(viewModel = viewmodel, content = {

        Scaffold(
            topBar = {
                TopApp(viewModel = viewmodel , opcion = 3, title = viewmodel.GetSettings().Settings[code], navToSettings = onBack)
            },
            content = {


                var showAlertDialog by remember { mutableStateOf(false) }

                if(showAlertDialog){
                    ExitDialog(
                        hideAlertDialog = { showAlertDialog = false },
                        showAlertDialog = { showAlertDialog = true },
                        texto = viewmodel.GetSettings().AreYouSureYouWantToCloseTheSession[code]!!,
                        onBack = {
                            SignOut()
                        },
                        viewmodel = viewmodel
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
                        ItemSettings(
                            R.drawable.languages_1,
                            viewmodel.GetSettings().YourLanguage[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            NavTo = NavToLanguage,
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings(
                            if(viewmodel.GetLearnLenguage() == "English" ) R.drawable.flag_states else R.drawable.flag_spain,
                            viewmodel.GetSettings().YourNextLanguage[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            NavTo = NavToNew,)
                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings(
                            R.drawable.themes,
                            viewmodel.GetSettings().ApplicationTheme[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            NavTo = NavToTheme,)

                        Spacer(modifier = Modifier.height(6.dp))

                        ItemSettings(
                            R.drawable.people,
                            viewmodel.GetSettings().credits[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            NavTo = NavToCredits,)

                        Spacer(modifier = Modifier.height(6.dp))
                        ItemSettings(
                            R.drawable.signout,
                            viewmodel.GetSettings().SignOff[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            NavTo = {
                                showAlertDialog = true
                            },)

                    }
                }
            }
            )

    })
}


