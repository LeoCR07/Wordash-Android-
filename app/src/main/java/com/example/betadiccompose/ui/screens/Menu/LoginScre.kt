package com.example.betadiccompose.ui.screens.Menu

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.ui.Foundation.Shared.Button.BtnLogin
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    NavToSingUpScreen : ()->Unit,
    NavToMainScreen: () -> Unit,
    viewmodel: VocabularyViewModel) {

    MyApp(viewModel = viewmodel, content = {

        val context = LocalContext.current

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

            Text(text = "O",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "Omitir",
                textDecoration = TextDecoration.Underline
            )


        }


        LaunchedEffect(key1 = viewmodel?.hasUser){
            if (viewmodel?.hasUser ){
                NavToMainScreen.invoke()
            }
        }
    })




}