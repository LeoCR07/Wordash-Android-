package com.example.betadiccompose.ui.screens

import android.net.Uri
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
import com.example.betadiccompose.ui.Foundation.ScreenAccount.LineColor
import com.example.betadiccompose.ui.Foundation.Shared.NavToBackDialog
import com.example.betadiccompose.ui.Foundation.Shared.TopApp
import com.example.betadiccompose.ui.Foundation.Shared.navegationinferior
import com.example.betadiccompose.ui.Navigation.ItemsMenu
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun AccountScreen(
    current: String?,
    onclickNav: (ItemsMenu) -> Unit,
    vocalview: VocabularyViewModel,
    navToSeeMyWords: () -> Unit,
    onBack: () -> Unit,
) {


   vocalview.GetMyFavoriteWords()
    val lstmyfavoritewords by remember {
        mutableStateOf(vocalview.lstfavoritewords.value)
    }

    MyApp {
        Scaffold(
            topBar = {
                TopApp(opcion = 1, viewModel = vocalview)
            },

        content = {


            Column() {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    contentPadding = PaddingValues(0.dp,0.dp,0.dp,80.dp)
                ){

                    item{

                        HeaderAccount(
                            UserName = "Lionel Messi"/*loginViewModel.username*/ ,
                            UserEmail = "elmessias@gmail.com"/*loginViewModel.username*/,
                            UserPhoto =  Uri.parse("https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg")/*loginViewModel.username*/,
                            Stars = 9/*loginViewModel.username*/,
                            Level = 0,
                            Exp = 0)

                        LineColor(height = 10f)
                        SubTitles("My favorites words",click = navToSeeMyWords )
                        HListWord(lstmyfavoritewords, vocalview = vocalview)
                        LineColor(height = 5f)
                        SubTitles("My favorites Sentes", click = {})
                        HListWord(lstmyfavoritewords, vocalview)
                        LineColor(height = 5f)
                        SubTitles("My favorites Gramar", click = {})
                        HListWord(lstmyfavoritewords, vocalview)


                    }




                    /*


                     */

                }
            }
        },
            bottomBar = { navegationinferior(menu, current, onclickNav) }
        )

    }
}