package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

@Composable
fun AccountScreen(
    current: String?,
    onclickNav: (ItemsMenu) -> Unit,
    vocalview: VocabularyViewModel,
    navToSeeMyWords: () -> Unit,
    navToSeeMySentes: () -> Unit
) {

    val lstmyfavoritewords by remember {
        mutableStateOf( vocalview.mywords.value)
    }

    val lstmyfavoritesentes by remember {
        mutableStateOf(vocalview.lstfavoritesentes.value)
    }

    MyApp {
        Scaffold(
            topBar = {
                TopApp(opcion = 1, viewModel = vocalview)
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

                        HeaderAccount(vocalview)

                        LineColor(height = 10f)
                        SubTitles("My favorites words",click = navToSeeMyWords )
                        HListWord(lstmyfavoritewords,vocalview = vocalview)

                        LineColor(height = 5f)
                        SubTitles("My favorites Sentes", click = navToSeeMySentes)
                        HListSentes(lstmyfavoritesentes, vocalview)

                        //LineColor(height = 5f)
                        //SubTitles("My favorites Gramar", click = {navToSeeMyGramar()})
                       // HListGramar(lstmyfavoritegramar, vocalview)


                    }


                }
            }
        },
            bottomBar = { navegationinferior(menu, current, onclickNav) }
        )

    }
}