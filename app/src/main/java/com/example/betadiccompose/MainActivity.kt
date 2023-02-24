package com.example.betadiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.example.betadiccompose.Foundation.Category.Navegation.Navegation
import com.example.betadiccompose.data.network_database.model.DataUser
import com.example.betadiccompose.ui.Navigation.routes.LoginRoutes
import com.example.betadiccompose.ui.Navigation.routes.MenuRoutes
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : VocabularyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Splash
        val screenSplash = installSplashScreen()

        //Start destination when no tenga el usuario
        var startDestination = LoginRoutes.Login.name

        CoroutineScope(Dispatchers.IO).launch {

            //La primera vez que instalo el app
            if(viewModel.counAllUser()==0){
                viewModel.insertDataUser(dataUser = DataUser(id = 0,exp=0,level=1, stars = 0))
            }else{

                print("Ya existe")
            }
        }

        //Niveles
        viewModel.getListOfAlllevel()

        //Categorias
        viewModel.getListOfAllCategories()

        //Favoritos
        viewModel.getMyFavoriteWords()
        viewModel.getMyFavoriteSentes()
        viewModel.getMyFavoriteGramar()



        setContent {
            screenSplash.setKeepOnScreenCondition{false}

            if (viewModel?.hasUser){
                //Si el usuario ya esta reguistrado
                startDestination = MenuRoutes.play.name
            }

            Navegation(viewModel,startDestination)
        }
    }
}





