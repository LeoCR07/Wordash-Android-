package com.example.authentication.Presentation

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.authentication.ui.Presentation.Login.BtnLogin
import com.example.authentication.ui.Presentation.Login.LineColor
import com.example.authentication.ui.Presentation.Login.Name
import com.example.betadiccompose.Domain.AuthResultContract
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.LoginScreen.CustomFacebookButton
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    NavToSingUpScreen : ()->Unit,
    ClickSingUpFacebook : ()->Unit,
    ClickSingUpMicrosoft : ()->Unit,
    NavToAccountScreen: () -> Unit,
    ClickForgotPassword : ()->Unit,
    loginViewModel: VocabularyViewModel? = null,
) {


    /* View model */
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current


    /**  Facebook **/
    val callbackManager = CallbackManager.Factory.create()

    /** Google **/
    val coroutineScope = rememberCoroutineScope()
    val signInRequestCode = 1
    val authResultLauncher = rememberLauncherForActivityResult(contract = AuthResultContract()){
            task ->
        try {
            val account = task?.getResult(ApiException::class.java)

            if (account != null) {
                Toast.makeText(
                    context,
                    "${account.account?.name}",
                    Toast.LENGTH_LONG
                ).show()
            }

            if (account==null){
                println("ERROR comes null")
                //   text = "Google sign in failed _2"
            }else{

                println("fUNCIONA")

                coroutineScope.launch {
                    //NUevo firebase experimental

                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    loginViewModel?.SingInGoogleFirebase(credential,context){
                        NavToAccountScreen.invoke()
                    }

                    println("${account.email} Correo **************************************************")

                    /*
                  FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {

                        if(it.isSuccessful){
                            println("Completo")
                        }else{
                            println("incompleto")
                        }

                    }

                    */
                    //  account.email?.let {account.displayName?.let { it1 -> authViewModel.signIn(email = it,displayName = it1) } }
                }


            }

        }catch (e: ApiException){
            //  text="Google sign in failed _1"
        }
    }

    var visibility by remember {
        mutableStateOf(false)
    }




    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{
            Name()
            LineColor(height = 1f)

            Spacer(modifier = Modifier.height(15.dp))


            Text(
                text = "To continue, log in to Dicvocabulary",
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(15.dp))

/*
            BtnLogin(
                title ="Continue with Facebook",
                color = Color(0xFF3b5998),
                icon = R.drawable.facebook,
                FontColor = Color.White,
                onClick = {

                    FacebookSdk.setAutoLogAppEventsEnabled(true);

                    LoginManager.getInstance().registerCallback(callbackManager,object :
                        FacebookCallback<LoginResult> {
                        override fun onCancel() {
                            TODO("Not yet implemented")

                        }

                        override fun onError(error: FacebookException) {
                            TODO("Not yet implemented")

                        }

                        override fun onSuccess(result: LoginResult) {
                            TODO("Not yet implemented")


                        }

                    })

                }
            )
*/


            /*
            CustomFacebookButton(
                modifier = Modifier
                    .height(20.dp)
                    .width(200.dp),
                true,{},{},{})
            */

            Spacer(modifier = Modifier.height(12.dp))
            BtnLogin(
                title = "Continue with Google",
                icon = R.drawable.google,
                Outline = true,
                FontColor = Color.Black,
                onClick = {
                    authResultLauncher.launch(signInRequestCode)
                }
            )


            Spacer(modifier = Modifier.height(15.dp))
            LineColor(1f)
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 15.dp, 0.dp),
                text = "Email address",
                textAlign = TextAlign.Left)


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .height(60.dp),
                value = loginUiState?.userName ?: "",
                onValueChange = {loginViewModel?.onUserNameChange(it)},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    )
                },
                isError = isError,
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Email",
                        color = Color.Black.copy(alpha = 0.2f))
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 15.dp, 0.dp),
                text = "Password",
                textAlign = TextAlign.Left)


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .height(60.dp),
                value = loginUiState?.password ?: "",
                onValueChange = { loginViewModel?.onPasswordNameChange(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Password",
                        color = Color.Black.copy(alpha = 0.2f))
                },
                visualTransformation = if(visibility) VisualTransformation.None else PasswordVisualTransformation(),
                isError = isError,
                trailingIcon = {
                    Icon(
                        imageVector = if(visibility) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                        modifier = Modifier
                            .clickable { visibility = !visibility },
                        contentDescription = null,
                        tint = Color.Black
                    )

                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                modifier = Modifier
                    //  .clickable { loginViewModel?.sendPasswordResetEmail(context) }
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 15.dp, 0.dp),
                text = "Forgot your password",
                textAlign = TextAlign.Left,
                textDecoration = TextDecoration.Underline)

            Spacer(modifier = Modifier.height(10.dp))

            BtnLogin(
                title = "Log in",
                color = Color(0xFF43E24A),
                IsIcon = false,
                FontColor = Color.White,
                onClick = { loginViewModel?.loginUser(context) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            LineColor(height = 1f)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Don't have an account?",
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(10.dp))

            BtnLogin(
                title = "Sign up",
                IsIcon = false,
                Outline = true,
                FontColor = Color.Gray,
                onClick = NavToSingUpScreen
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }


    if (loginUiState?.isLoading == true){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){

            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.Center))
        }

    }


    LaunchedEffect(key1 = loginViewModel?.hasUser){
        if (loginViewModel?.hasUser == true){
            NavToAccountScreen.invoke()
        }
    }


}