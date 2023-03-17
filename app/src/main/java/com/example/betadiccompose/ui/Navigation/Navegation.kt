package com.example.betadiccompose.Foundation.Category.Navegation
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authentication.Presentation.LoginScreen
import com.example.authentication.ui.Presentation.SingUpScreen
import com.example.betadiccompose.Domain.Ads.AdMobInterstital
import com.example.betadiccompose.MainActivity
import com.example.betadiccompose.ui.Foundation.ScreenNiveles.Nivel
import com.example.betadiccompose.ui.Foundation.Shared.currentRoute
import com.example.betadiccompose.ui.Navigation.routes.LoginRoutes
import com.example.betadiccompose.ui.Navigation.routes.MenuRoutes
import com.example.betadiccompose.ui.Navigation.routes.SettingRoute
import com.example.betadiccompose.ui.Navigation.routes.SubRoutes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.*
import com.example.betadiccompose.ui.screens.Settings.OpcionScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Navegation(
    VocaVM: VocabularyViewModel,
    startDestination: String
) {

    val navController = rememberNavController()
    val context = LocalContext.current

    val activity = (LocalContext.current as? Activity)


    VocaVM.getListOfAlllevel()
    VocaVM.getListOfAllCategories()
    VocaVM.getMyFavoriteSentes()
    VocaVM.getMyFavoriteWords()


    NavHost(
        navController = navController,
        // startDestination = startDestination
        //  startDestination = "SORT"
        startDestination = MenuRoutes.play.name
    ) {

        composable(LoginRoutes.Login.name){

            LoginScreen(
                loginViewModel = VocaVM,
                ClickSingUpFacebook = {},
                ClickSingUpMicrosoft = {},
                NavToAccountScreen = {
                    navController.navigate(MenuRoutes.play.name) {
                        launchSingleTop = true

                        popUpTo(route = LoginRoutes.Login.name) {
                            inclusive = true
                        }
                    }
                },
                ClickForgotPassword = {},
                NavToSingUpScreen = {
                    navController.navigate(LoginRoutes.SingUp.name) {
                        launchSingleTop = true
                    }
                })
        }

        composable(LoginRoutes.SingUp.name){
            SingUpScreen(
                loginViewModel = VocaVM,
                onNavToAccount = {
                    navController.navigate(MenuRoutes.play.name) {
                        launchSingleTop = true

                        popUpTo(route = LoginRoutes.SingUp.name) {
                            inclusive = true
                        }
                    }
                },
                ClickSingUp = {})
        }

        composable(MenuRoutes.learn.name) { navback ->
            val current = currentRoute(navController = navController)
            VocaVM.loadSubMenu.value = true

            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED


            VocabularyScreen(

                current = current,
                vocalview = VocaVM,
                onMediaClick = {

                    VocaVM.SavePreferences(it)
                    val indice =  VocaVM.GetKindDocument()



                    if (!VocaVM.IsSub()) {
                        when (indice) {
                            0 -> navController.navigate("WorldScreen")
                            2 -> navController.navigate("GramarScreen")
                            1 -> navController.navigate("SentesScreen")
                        }
                    } else {

                        navController.navigate("SubMenuScreen"){
                        }
                    }


                },
                onclickNav = {
                    navController.navigate(it.route){
                        launchSingleTop = true
                        popUpTo(MenuRoutes.learn.name){
                            inclusive = true
                        }
                    }
                })
        }

        composable(MenuRoutes.play.name) {
            val current = currentRoute(navController = navController)
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

            LaunchedEffect(key1 =true){

                println("palabras: ${VocaVM.GetCounterVoca()}")
                println("play: ${VocaVM.GetCounterGame()}")
            }

            PlayScreen(
                current = current,
                vocalview = VocaVM,
                onMediaClick = {

                    VocaVM.SaveAllSubNameCategory(it.doc)
                    VocaVM.SavePath(it.dir.split("_")[0])

                    println("dirrecion "+it)
                    VocaVM.cleanAllWords()
                    VocaVM.getListOfWordsToPlayForLevel()
                    navController.navigate(SubRoutes.nivel.name)
                },
                onclickNav = {
                    navController.navigate(it.route){
                        launchSingleTop = true
                    }
                },

                onBack = {
                    activity?.finish()
                    //navController.popBackStack()
                }
            )
        }



        composable(MenuRoutes.account.name) {
            val current = currentRoute(navController = navController)

            AccountScreen(
                current = current,
                onclickNav = {
                    navController.navigate(it.route){
                        launchSingleTop = true
                        popUpTo(MenuRoutes.account.name){
                            inclusive = true
                        }
                    }
                },
                vocalview = VocaVM,
                navToSeeMyWords = {
                    navController.navigate("MyWords")
                },
                navToSeeMySentes = {
                    navController.navigate("MySentes")
                })
        }

        composable(MenuRoutes.store.name) {
            val current = currentRoute(navController = navController)

        }

        composable("SubMenuScreen") {
            //
            // VocaVM.loadWords.value = true
            SubMenuScreen(
                viewmodel = VocaVM,
                onMediaClick = {

                    VocaVM.SaveSubNumberCategory(it.id)
                    VocaVM.SaveWordCategoryName(it.name)

                    when(it.doc){
                        0->navController.navigate("WorldScreen")
                        1->navController.navigate("SentesScreen")
                        2 ->navController.navigate("GramarScreen")
                    }

                })

        }

        composable("GramarScreen") {
            VocaVM.loadGramar.value = true

            GramarScreen(onMediaClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    VocaVM.updateExp()
                }
            }, viewmodel = VocaVM)
        }

        composable(SubRoutes.nivel.name) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            Nivel(
                viewModel = VocaVM,
                onBack = {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    navController.popBackStack()
                    //    VocaVM.step = 0
                },
                NavToStudy= {
                    navController.navigate(MenuRoutes.learn.name){
                        popUpTo(SubRoutes.nivel.name){
                            inclusive = true
                        }
                    }
                })
        }

        composable("SentesScreen") {


            LaunchedEffect(key1 =true){
                VocaVM.loadSentes.value = true
                //VocaVM.ShowInterstitalVoca(context)
            }

            SentesScreen(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        VocaVM.updateExp()
                        VocaVM.soundFromUrl(id=it.id)
                    }

                }, viewmodel = VocaVM)
        }

        composable("WorldScreen") {


            LaunchedEffect(key1 =true){
                //VocaVM.ShowInterstitalVoca(context)
                VocaVM.loadWords.value = true
            }

            WordScreen(

                viewmodel = VocaVM,
                onMediaClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        VocaVM.updateExp()
                    }
                    VocaVM.soundFromUrl(id=it.id)
                })
        }

        composable("MyWords"){
            MyWords(
                vocalview = VocaVM
            )
        }

        composable("MySentes"){
            MySentes(
                vocalview = VocaVM
            )
        }

        composable("MyGramar"){
            MyGramar(
                vocalview = VocaVM
            )
        }



        composable(SettingRoute.SettingsScreen.name){
            SettingsScreen(
                viewmodel = VocaVM,
                NavToNatal = {
                    navController.navigate(SettingRoute.SettingNatal.name)
                },
                NavToNuevo = {
                    navController.navigate(SettingRoute.SettingNuevo.name)
                },
                NavToTema = {
                    navController.navigate(SettingRoute.SettingTema.name)
                })
        }

        composable(SettingRoute.SettingNatal.name){
            OpcionScreen(
                viewmodel = VocaVM,
                type = 1
            )
        }

        composable(SettingRoute.SettingNuevo.name){
            OpcionScreen(
                viewmodel = VocaVM,
                type = 2
            )
        }

        composable(SettingRoute.SettingTema.name){
            OpcionScreen(
                viewmodel = VocaVM,
                type = 3
            )
        }




    }

}
