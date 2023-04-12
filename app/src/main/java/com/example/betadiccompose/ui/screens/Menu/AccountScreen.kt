package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.example.authentication.ui.Foundation.Account.HListWord
import com.example.authentication.ui.Foundation.Account.HeaderAccount
import com.example.authentication.ui.Foundation.Account.SubTitles
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.Foundation.Vocabulary.MySentes.HListSentes
import com.example.betadiccompose.ui.Foundation.Vocabulary.ScreenAccount.LineColor
import com.example.betadiccompose.ui.Navigation.ItemsMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*

@Composable
fun AccountScreen(
    current: String?,
    onclickNav: (ItemsMenu) -> Unit,
    vocalview: VocabularyViewModel,
    navToSeeMyWords: () -> Unit,
    navToSeeMySentes: () -> Unit,
    navToSettings:() ->Unit,
    navToLogin: () ->Unit
) {

    val lstmyfavoritewords =  vocalview.mywords.value
    val lstmyfavoritesentes = vocalview.lstfavoritesentes.value

    var code by remember {
        mutableStateOf(vocalview.GetCode())
    }

    MyApp(viewModel = vocalview, content = {
        Scaffold(
            topBar = {
                TopApp(
                    opcion = 1,
                    viewModel = vocalview,
                navToSettings = navToSettings)
            },

        content = {

            vocalview.getMyFavoriteWords()
            vocalview.getMyFavoriteSentes()


            Column() {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    contentPadding = PaddingValues(0.dp,0.dp,0.dp,80.dp)
                ){

                    item{

                        HeaderAccount(vocalview,navToLogin)

                        LineColor(height = 3f)
                        SubTitles(vocalview.GetSettings().MyFavoriteWords[code]!!.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()

                        },click = navToSeeMyWords ,vocalview = vocalview)
                        HListWord(lstmyfavoritewords,vocalview = vocalview)

                        LineColor(height = 3f)
                        SubTitles(vocalview.GetSettings().MyFavoritePhrases [code]!!.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }, click = navToSeeMySentes,vocalview = vocalview)
                        HListSentes(lstmyfavoritesentes, vocalview)

                    }


                }
            }
        },
            bottomBar = { navegationinferior(menu, current, onclickNav) }
        )

    })
}