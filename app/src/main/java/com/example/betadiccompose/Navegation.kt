package com.example.betadiccompose.Foundation.Category.Navegation
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
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.example.betadiccompose.ui.screens.*


@Composable
fun Navegation(provider: Provider, prefs: Prefs, VocaVM: VocabularyViewModel) {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = com.example.betadiccompose.ui.Foundation.Category.Navegation.NavData.Menu.route) {


        composable(com.example.betadiccompose.ui.Foundation.Category.Navegation.NavData.Menu.route) {
            MenuScreen(provider = provider,onMediaClick = {

                if(it.Id == 0){
                    navController.navigate( "vocabulary")
                }else if (it.Id == 1){
                    navController.navigate( "levels")
                }else if(it.Id == 2){
                    navController.navigate( "N2")
                }else if(it.Id == 3){

                }

            })
        }

        composable(
            route = com.example.betadiccompose.ui.Foundation.Category.Navegation.NavData.Category.route,
            arguments = com.example.betadiccompose.ui.Foundation.Category.Navegation.NavData.Category.args

        ){ backStackEntry ->
            val id =
                backStackEntry.arguments?.getInt(com.example.betadiccompose.ui.Foundation.Category.Navegation.NavArgs.MediaId.Key)
            requireNotNull(id)

            navController.popBackStack()

          //  CategoryScreen(id = id, onUpClick = {
            //    navController.popBackStack() })
        }


        composable("vocabulary"){ navback ->

            val current = currentRoute(navController = navController )


           VocabularyScreen(current = current,navController = navController,vocalview = VocaVM,provider= provider, onMediaClick = {

               provider.SaveIndex(it)
               val indice = provider.getkindocument()

                if(!provider.getsubmenu()){
                    when(indice){
                        0-> navController.navigate( "WorldScreen")
                        1-> navController.navigate( "GramarScreen")
                        2-> navController.navigate( "SentesScreen")
                    }
                }else{
                    navController.navigate( "SubMenuScreen")
                }



           }, onclickNav = {
               navController.navigate(it.route)



})
}

        composable("levels"){
        //GeneradorJsonScreen(provider)
        val current = currentRoute(navController = navController )
        NivelesScreen(current = current,vocalview = VocaVM,
            onMediaClick = {
                println(it.dir)
                VocaVM.cleanAllWords()
                prefs.SavePath(it.dir)
                VocaVM.getlevel()

                navController.navigate( "N1")
                           }, onclickNav = {
        println("Navegacion")
        navController.navigate(it.route)})
          }
        composable("SubMenuScreen"){
        SubMenuScreen(viewmodel = VocaVM,onMediaClick = {
        prefs.SaveNumberCategory(it.id)
        navController.navigate( "WorldScreen")
        })
    }

    composable("GramarScreen"){
        GramarScreen(onMediaClick = {})
    }

        composable("N1"){
            //SpeakToTalk(viewModel = VocaVM)

            /*

            when (VocaVM.Step.value) {
                0->Easy(viewModel =  VocaVM, prefs = prefs, onMediaClick = {
                    if(VocaVM.getCurrentIdGame() == it.id){
                        println("es igual")
                        VocaVM.Step.value += 1
                    }else {
                        //   VocaVM.Step.value = 22
                        //navController.navigate( "N2")
                        println("Es diferente")
                    }

                    //VocaVM.Step.value += 1
                    //navController.navigate("N1")
                })
                2,12 -> Easy(viewModel =  VocaVM, prefs = prefs, onMediaClick = {
                    if(VocaVM.getCurrentIdGame() == it.id){
                        println("es igual")
                        VocaVM.Step.value += 1
                    }else {
                     //   VocaVM.Step.value = 22
                        //navController.navigate( "N2")
                        println("Es diferente")
                    }

                    //VocaVM.Step.value += 1
                    //navController.navigate("N1")
                })
                8,15,-> Hard(VocaVM,0) { VocaVM.Step.value += 1}
                9,16 -> Hard(VocaVM,1) { VocaVM.Step.value += 1}
                5,11 -> SpeakToTalk(viewModel = VocaVM) { VocaVM.Step.value += 1}
                3,4,13-> Sonido(VocaVM) { VocaVM.Step.value += 1}
                1,10,14-> WrongWritten(VocaVM,prefs) {
                    VocaVM.Step.value += 1
                }
                6,7-> Sort(VocaVM,2) { VocaVM.Step.value += 1}
                17-> asocie(VocaVM) { VocaVM.Step.value += 1}
                else -> Hard(VocaVM,2) { VocaVM.Step.value += 1}
            }

            */

            //val lst: List<DataWorld> = VocaVM.lstnivel.value
            Nivel(VocaVM, prefs = prefs,context= context)
            //Easy(VocaVM){}

        }


        composable("SentesScreen"){
        SentesScreen(onMediaClick = {},viewmodel = VocaVM)
    }



        composable("N2"){
          //  Nivel(viewModel = VocaVM, prefs = prefs, context = context)
           //Easy()
            //SpeakToTalk(viewModel = VocaVM) {}
            //Hard()
            com.example.betadiccompose.ui.Foundation.GamesScreen.Sonido(VocaVM,{})
           // WrongWritten()

        }


    composable("WorldScreen"){
        WorldScreen(
            viewmodel = VocaVM,
            provider = provider,
            onMediaClick = {
                Sonido(it.id,provider.getCategory())
            })
    }

    }
}