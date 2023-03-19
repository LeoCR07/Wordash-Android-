package com.example.betadiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.betadiccompose.Domain.Ads.AdMobInterstital

import com.example.betadiccompose.Foundation.Category.Navegation.Navegation
import com.example.betadiccompose.data.network_database.model.DataUser
import com.example.betadiccompose.ui.Navigation.routes.LoginRoutes
import com.example.betadiccompose.ui.Navigation.routes.MenuRoutes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : VocabularyViewModel by viewModels()
   @Inject lateinit var adMobInterstital: AdMobInterstital

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)



        var startDestination = LoginRoutes.Login.name
       // var startDestination = MenuRoutes.play.name
        //"SelectLanguage"

        CoroutineScope(Dispatchers.IO).launch {
            //Niveles
            //La primera vez que instalo el app
           if(viewModel.counAllUser()==0){

                println("user no")

                var user  = DataUser(id = 1)
                viewModel.insertDataUser(user)
            }else{
                println("user si")
            }
        }

        viewModel.LoadInterstital()
        viewModel.LoadRewarded()
       // println("la suma es: ${viewModel.GetCounterGame()}")

        setContent {

            if (viewModel?.hasUser){
                //Si el usuario ya esta reguistrado
                startDestination = MenuRoutes.play.name
            }

            Navegation(viewModel,startDestination)
        }
    }


}








