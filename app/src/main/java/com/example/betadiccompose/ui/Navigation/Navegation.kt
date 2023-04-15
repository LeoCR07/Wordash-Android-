package com.example.betadiccompose.Foundation.Category.Navegation
import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authentication.ui.Presentation.SingUpScreen
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.ScreenNiveles.Nivel
import com.example.betadiccompose.ui.Foundation.Shared.currentRoute
import com.example.betadiccompose.ui.Navigation.routes.LoginRoutes
import com.example.betadiccompose.ui.Navigation.routes.MenuRoutes
import com.example.betadiccompose.ui.Navigation.routes.SettingRoute
import com.example.betadiccompose.ui.Navigation.routes.SubRoutes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.*
import com.example.betadiccompose.ui.screens.Menu.LoginScreen
import com.example.betadiccompose.ui.screens.Menu.OldLoginScreen
import com.example.betadiccompose.ui.screens.Menu.RegisterScreen
import com.example.betadiccompose.ui.screens.Menu.SelectLanguage
import com.example.betadiccompose.ui.screens.Settings.CreditScreen
import com.example.betadiccompose.ui.screens.Settings.LanguageScreen

import com.example.betadiccompose.ui.screens.Settings.NewScreen
import com.example.betadiccompose.ui.screens.Settings.ThemeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun Navegation(
    VocaVM: VocabularyViewModel,
    startDestination: String,
    window: Window

) {

    val navController = rememberNavController()
    val context = LocalContext.current

    val activity = (LocalContext.current as? Activity)


   // VocaVM.getDataUser()
    VocaVM.getListOfAlllevel()
    VocaVM.getListOfAllCategories()
    VocaVM.getMyFavoriteSentes()
    VocaVM.getMyFavoriteWords()


   // context.setTheme(R.style.Theme_BetaDicCompose)

    LaunchedEffect(key1 = true ){


        delay(500)


        val calendar = Calendar.getInstance()
        val hora = calendar.get(Calendar.HOUR_OF_DAY)

        if(VocaVM.GetTheme() == 2){
            window.setBackgroundDrawableResource(R.color.light)
        }else if (VocaVM.GetTheme() == 1){
            window.setBackgroundDrawableResource(R.color.dark)
        }else if(VocaVM.GetTheme() == 0){
            if (hora>=18) {
                window.setBackgroundDrawableResource(R.color.dark)
            }else{
                window.setBackgroundDrawableResource(R.color.light)
            }
        }



    }



    NavHost(
        navController = navController,
        startDestination = startDestination
        //  startDestination = "SORT"
       // startDestination = MenuRoutes.play.name
    ) {


        composable(LoginRoutes.first.name){
            SelectLanguage(viewmodel = VocaVM, NavToLogin = {
                navController.navigate(LoginRoutes.home.name)
            })
        }

        composable(LoginRoutes.home.name){
            LoginScreen(
                viewmodel = VocaVM,
                NavToMainScreen = {
                    navController.navigate(MenuRoutes.learn.name) {
                        launchSingleTop = true

                        popUpTo(route = LoginRoutes.home.name) {
                            inclusive = true
                        }

                    }
                },
                NavToSingUpScreen = {
                    navController.navigate(LoginRoutes.SingUp.name) {
                        launchSingleTop = true
                    }
                },
            RegisterScreen = {
                navController.navigate(LoginRoutes.login.name) {
                    launchSingleTop = true
                }
            })
        }


        composable(LoginRoutes.login.name){

        RegisterScreen(
            viewmodel = VocaVM,
            NavToMainScreen = {
                navController.navigate(MenuRoutes.learn.name) {
                    launchSingleTop = true

                    popUpTo(route = LoginRoutes.home.name) {
                        inclusive = true
                    }

                }
            },
            onNavToAccount = {
                navController.navigate(MenuRoutes.learn.name) {
                    launchSingleTop = true

                    popUpTo(route = LoginRoutes.SingUp.name) {
                        inclusive = true
                    }
                }
            })
        }

        composable(LoginRoutes.SingUp.name){
            SingUpScreen(
                viewmodel = VocaVM,
                NavToMainScreen = {
                    navController.navigate(MenuRoutes.learn.name) {
                        launchSingleTop = true

                        popUpTo(route = LoginRoutes.home.name) {
                            inclusive = true
                        }


                    }
                },
                onNavToAccount = {
                    navController.navigate(MenuRoutes.learn.name) {
                        launchSingleTop = true

                        popUpTo(route = LoginRoutes.SingUp.name) {
                            inclusive = true
                        }
                    }
                })
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
                        /*
                        popUpTo(MenuRoutes.learn.name){
                            inclusive = true
                        }*/
                    }
                },
                onBack =  {
                    activity?.finish()
                    //navController.popBackStack()
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
                    VocaVM.cleanAllWords()
                    VocaVM.SetIndexLevelCurrent(it.id)

                    VocaVM.getListOfWordsToPlayForLevel()
                    navController.navigate(SubRoutes.nivel.name)
                },
                onclickNav = {
                    navController.navigate(it.route){
                        launchSingleTop = true

                        popUpTo(MenuRoutes.play.name){
                            inclusive = true
                        }
                    }
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
                },
                navToSettings = {
                    navController.navigate(SettingRoute.SettingsScreen.name)
                },
                navToLogin = {
                    navController.navigate(LoginRoutes.oldlogin.name)

                })
        }


        composable(LoginRoutes.oldlogin.name){

            OldLoginScreen(
                viewmodel = VocaVM,
                NavToMainScreen = {
                    navController.navigate(MenuRoutes.learn.name) {
                        launchSingleTop = true

                        popUpTo(route = LoginRoutes.home.name) {
                            inclusive = true
                        }

                    }
                },
                NavToSingUpScreen = {
                    navController.navigate(LoginRoutes.SingUp.name) {
                        launchSingleTop = true
                    }
                },
                RegisterScreen = {
                    navController.navigate(LoginRoutes.login.name) {
                        launchSingleTop = true
                    }
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



        composable(SettingRoute.SettingsScreen.name){
            SettingsScreen(
                viewmodel = VocaVM,
                onBack = {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    navController.popBackStack()
                    //    VocaVM.step = 0
                },
                NavToLanguage = {
                    navController.navigate(SettingRoute.SettingLanguage.name)
                },
                NavToNew = {
                    navController.navigate(SettingRoute.SettingNew.name)
                },
                NavToTheme = {
                    navController.navigate(SettingRoute.SettingTheme.name)
                },
                SignOut = {
                    VocaVM.SingOut()
                    activity?.finish()
                },
                NavToCredits = {
                    navController.navigate(SettingRoute.SettingCretits.name)
                })
        }

        composable(SettingRoute.SettingLanguage.name){
            LanguageScreen(
                viewmodel =  VocaVM,
                onBack = {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                navController.popBackStack()
                //    VocaVM.step = 0
            })
        }

        composable(SettingRoute.SettingNew.name){
            NewScreen(
                viewmodel = VocaVM,
                onBack = {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    navController.popBackStack()
                    //    VocaVM.step = 0
                })
        }

        composable(SettingRoute.SettingTheme.name){
            ThemeScreen(
                onBack = {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    navController.popBackStack()
                    //    VocaVM.step = 0
                },
                viewmodel = VocaVM,
                click = {
                val calendar = Calendar.getInstance()
                val hora = calendar.get(Calendar.HOUR_OF_DAY)

                if(VocaVM.GetTheme() == 2){
                    window.setBackgroundDrawableResource(R.color.light)
                }else if (VocaVM.GetTheme() == 1){
                    window.setBackgroundDrawableResource(R.color.dark)
                }else if(VocaVM.GetTheme() == 0){
                    if (hora>=18) {
                        window.setBackgroundDrawableResource(R.color.dark)
                    }else{
                        window.setBackgroundDrawableResource(R.color.light)
                    }
                }
            })
        }

        composable(SettingRoute.SettingCretits.name){
            CreditScreen(
                viewmodel = VocaVM,
                onBack = {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    navController.popBackStack()
                    //    VocaVM.step = 0
                })
        }



    }

}
