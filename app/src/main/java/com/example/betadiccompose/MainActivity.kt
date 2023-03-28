package com.example.betadiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import com.example.betadiccompose.Foundation.Category.Navegation.Navegation
import com.example.betadiccompose.data.network_database.model.DataUser
import com.example.betadiccompose.ui.Navigation.routes.LoginRoutes
import com.example.betadiccompose.ui.Navigation.routes.MenuRoutes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel : VocabularyViewModel by viewModels()
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var startDestination = LoginRoutes.Login.name




        CoroutineScope(Dispatchers.IO).launch {
            //Niveles
            //setTheme(R.style.Theme_BetaDicCompose)
            //La primera vez que instalo el app
            if(viewModel.counAllUser()==0){

                //  println("user no")
                var user  = DataUser(id = "0")
                viewModel.insertDataUser(user)
            }else{
                println("user si")
            }
        }

        setContent {

            //window.setBackgroundDrawableResource(MaterialTheme.colors.background.toArgb())
            MobileAds.initialize(this)
            analytics = Firebase.analytics

            viewModel.LoadInterstital()
            viewModel.LoadRewarded()



            if (viewModel?.hasUser){
                //Si el usuario ya esta reguistrado
                startDestination = MenuRoutes.learn.name
            }

            Navegation(viewModel,startDestination,window)



        }
    }


}








