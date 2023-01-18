package com.example.betadiccompose.Foundation.Category.Navegation
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Domain.Provider.Provider
import com.example.betadiccompose.ui.Foundation.ScreenNiveles.Nivel
import com.example.betadiccompose.ui.Foundation.Shared.Sonido
import com.example.betadiccompose.ui.Foundation.Shared.currentRoute
import com.example.betadiccompose.ui.Navigation.routes.MenuRoutes
import com.example.betadiccompose.ui.Navigation.routes.SettingRoute
import com.example.betadiccompose.ui.Navigation.routes.SubRoutes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.*
import com.example.betadiccompose.ui.screens.Settings.OpcionScreen


@Composable
fun Navegation(provider: Provider, prefs: Prefs, VocaVM: VocabularyViewModel) {

    val navController = rememberNavController()
    val context = LocalContext.current

    val activity = (LocalContext.current as? Activity)
    NavHost(
        navController = navController,
        startDestination = MenuRoutes.play.name
        //startDestination = com.example.betadiccompose.ui.Foundation.Category.Navegation.NavData.Menu.route
    ) {

        composable(MenuRoutes.learn.name) { navback ->

            val current = currentRoute(navController = navController)
            VocaVM.getList()

            VocabularyScreen(

                current = current,
                navController = navController,
                vocalview = VocaVM,
                provider = provider,
                onMediaClick = {

                    provider.SaveIndex(it)
                    val indice = provider.getkindocument()

                    if (!provider.getsubmenu()) {
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
            //GeneradorJsonScreen(provider)
            val current = currentRoute(navController = navController)
            PlayScreen(
                current = current,
                vocalview = VocaVM,
                onMediaClick = {
                    println(it.dir)
                    VocaVM.cleanAllWords()

                    prefs.SaveIndexGame(it.inicio,it.fin)
                    prefs.SavePath(it.dir)

                    VocaVM.getlevel()

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
            onBack = {
                navController.popBackStack()
            })
        }

        composable(MenuRoutes.store.name) {
            val current = currentRoute(navController = navController)

            StoreScreen(
                viewmodel = VocaVM,
                current = current,
                onclickNav = {
                    navController.navigate(it.route){
                        launchSingleTop = true
                        popUpTo(MenuRoutes.store.name){
                            inclusive = true
                        }
                    }
                },
                navToSettings = {
                    navController.navigate("SettingsScreen")
                })
        }

        composable("SubMenuScreen") {

            SubMenuScreen(
                viewmodel = VocaVM,
                onMediaClick = {
                    prefs.SaveNumberCategory(it.id)
                    prefs.SaveNameCategory(it.name)

                    when(it.doc){
                        0->navController.navigate("WorldScreen")
                        1->navController.navigate("SentesScreen")
                        2 ->navController.navigate("GramarScreen")
                    }

            })

        }

        composable("GramarScreen") {
            GramarScreen(onMediaClick = {}, viewmodel = VocaVM)
        }

        composable(SubRoutes.nivel.name) {

            Nivel(
                viewModel = VocaVM,
                prefs = prefs,
                context = context,
                onBack = {
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
            SentesScreen(onMediaClick = {}, viewmodel = VocaVM)
        }

        composable("WorldScreen") {
            WorldScreen(
                viewmodel = VocaVM,
                provider = prefs,
                onMediaClick = {
                    Sonido(it.id, provider.getCategory(), vocalview = VocaVM)
                })
        }

        composable("MyWords"){
            SeeMoreMyWords(
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