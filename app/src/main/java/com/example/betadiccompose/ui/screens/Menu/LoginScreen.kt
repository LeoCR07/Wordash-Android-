package com.example.betadiccompose.ui.screens.Menu

import android.app.Activity
import android.content.ClipData.Item
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.AuthResultContract
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyGame
import com.example.betadiccompose.ui.Foundation.Shared.Button.BtnLogin
import com.example.betadiccompose.ui.Foundation.Shared.Vocabulary.CircleProgress
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    RegisterScreen:() ->Unit,
    NavToSingUpScreen : ()->Unit,
    NavToMainScreen: () -> Unit,
    viewmodel: VocabularyViewModel) {

    MyGame(viewModel = viewmodel, content = {

        val context = LocalContext.current


        /**  Facebook **/
        var callbackManager = remember { CallbackManager.Factory.create() }

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                result.let {
                    val token  = it.accessToken
                    val credential = FacebookAuthProvider.getCredential(token.token)

                    viewmodel.SingInGoogleFirebase(
                        credential = credential,
                        OnNavToHome ={
                            NavToMainScreen.invoke()
                        }
                    )
                }
            }

            override fun onCancel() {
                // Manejar el inicio de sesión cancelado
            }

            override fun onError(error: FacebookException) {
                // Manejar el error de inicio de sesión
            }
        })


        /** Google **/
        val coroutineScope = rememberCoroutineScope()
        val signInRequestCode = 1

        val authResultLauncher = rememberLauncherForActivityResult(contract = AuthResultContract()){
                task ->
            try {
                val account = task?.getResult(ApiException::class.java)

                if (account != null) {
                    println("cuenta 1: ${account.account?.name}")
                }

                if (account==null){
                    println("ERROR comes null")
                    //   text = "Google sign in failed _2"
                }else{

                    println("WORKS")

                    coroutineScope.launch {
                        val credential = GoogleAuthProvider.getCredential(account.idToken,null)


                        viewmodel?.SingInGoogleFirebase(
                            credential = credential,
                            OnNavToHome ={
                                NavToMainScreen.invoke()
                            }
                        )

                        Toast.makeText(context,account.email,Toast.LENGTH_SHORT).show()
                        println("${account.email} Correo **************************************************")

                    }


                }

            }catch (e: ApiException){
                //  text="Google sign in failed _1"
            }
        }


        if(viewmodel.loginUiState.isLoading){
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.align(Alignment.BottomCenter))
            }
        }

        LazyColumn(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
            item() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    // Icono
                    androidx.compose.material.Icon(
                        painter = painterResource(R.drawable.flag_states),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        tint = Color.Unspecified)

                    Spacer(modifier = Modifier.height(40.dp))

                    BtnLogin(icono = R.drawable.facebo, text = "Registrate con facebook"){

                        LoginManager.getInstance().logInWithReadPermissions(context as Activity, listOf("email", "public_profile"))

                    }


                    Spacer(modifier = Modifier.height(15.dp))

                    BtnLogin(icono = R.drawable.google_2,text = "Registrate con google"){
                        authResultLauncher.launch(signInRequestCode)
                    }

                    Spacer(modifier = Modifier.height(15.dp))


                    BtnLogin(icono = R.drawable.email,text ="Registrate con tu correo"){
                        NavToSingUpScreen()
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        color = MaterialTheme.colors.secondaryVariant,
                        text = "O",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        color = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .clickable {
                                viewmodel.SkipLogin {
                                    NavToMainScreen.invoke()
                                }
                            },
                        text = "Omitir",
                        textDecoration = TextDecoration.Underline
                    )

                    Spacer(modifier = Modifier.height(70.dp))
                }

            }

            item {
                Column(  modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Row() {
                        Text(
                            color = MaterialTheme.colors.secondaryVariant,
                            text = "¿No tienes cuentas?")

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            modifier = Modifier
                                .clickable() {
                                    RegisterScreen()
                                },
                            text = "Crear cuenta",
                            color = MaterialTheme.colors.onSurface,
                            textDecoration = TextDecoration.Underline)
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }



        LaunchedEffect(key1 = viewmodel.IsLogin()){

            if(viewmodel.IsLogin()){
                NavToMainScreen.invoke()
            }
            /*
            if (viewmodel?.hasUser ){
                NavToMainScreen.invoke()
            }

            */
        }


    })




}