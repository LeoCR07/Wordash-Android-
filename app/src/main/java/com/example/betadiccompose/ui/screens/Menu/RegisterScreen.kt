package com.example.betadiccompose.ui.screens.Menu

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.AuthResultContract
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyGame
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.Foundation.Shared.Button.BtnLogin
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.facebook.login.LoginManager
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    NavToMainScreen: () -> Unit,
    viewmodel: VocabularyViewModel,
    onNavToAccount : ()->Unit,
) {


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


                }


            }

        }catch (e: ApiException){
            //  text="Google sign in failed _1"
        }
    }


    /**  code of language **/
    var code by remember{
        mutableStateOf(viewmodel.GetCode())
    }

    val interactionSource = remember { MutableInteractionSource() }

    MyGame(viewModel = viewmodel,
        content = {
            var visibility_1 by remember {
                mutableStateOf(false)
            }

            var showError by remember { mutableStateOf(false) }


            val loginUiState = viewmodel?.loginUiState

            val isErrorUserName = loginUiState?.loginErrorUserName != null
            val isErrorEmail = loginUiState?.loginErrorEmail!= null
            val isErrorPassword = loginUiState?.loginErrorPassword != null

            if(viewmodel.loginUiState.isLoading){
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.align(Alignment.BottomCenter))
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(20.dp, 0.dp, 20.dp, 0.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var textValue by remember { mutableStateOf("") }

                        Spacer(modifier = Modifier.height(30.dp))

                        /*
                        BtnLogin(icono = R.drawable.facebo, text = "${viewmodel.GetSettings().ContinueWith[code]!!} facebook".replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }) {

                        }
                        */


                        Spacer(modifier = Modifier.height(15.dp))

                        BtnLogin(icono = R.drawable.google_2,text = "${viewmodel.GetSettings().ContinueWith[code]!!} Google".replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }){
                            authResultLauncher.launch(signInRequestCode)
                        }

                        Spacer(modifier = Modifier.height(35.dp))

                        // Icono
                        Icon(
                            painter = painterResource(
                                if(viewmodel.GetLearnLenguage() =="English")R.drawable.flag_states else R.drawable.flag_spain
                            ),
                            contentDescription = "BTN",
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape),
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Start),
                            text = viewmodel.GetSettings().Username[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            color = MaterialTheme.colors.secondaryVariant,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = loginUiState?.userLoginName ?: "",
                            onValueChange = {
                                viewmodel?.onUserNameChangeLogin(it) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.DarkGray,
                                unfocusedBorderColor = Color.LightGray,
                                cursorColor = Color.DarkGray
                            ),
                            isError = isErrorUserName,
                            placeholder = { Text(viewmodel.GetSettings().Username[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            }) },
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.secondaryVariant)

                        )

                        if (isErrorUserName) {
                            Text(loginUiState.errorUserNameLogin, color = Color.Red)
                        }



                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Start),
                            text = viewmodel.GetSettings().Email[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            color = MaterialTheme.colors.secondaryVariant,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))



                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = loginUiState?.userLoginEmail ?: "",
                            onValueChange = { viewmodel?.onEmailChangeLogin(it) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.DarkGray,
                                unfocusedBorderColor = Color.LightGray,
                                cursorColor = Color.DarkGray
                            ),
                            isError = isErrorEmail,
                            placeholder = { Text(viewmodel.GetSettings().Email[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            }) },
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.secondaryVariant)

                        )
                        if (isErrorEmail) {
                            Text(loginUiState.errorEmailLogin, color = Color.Red)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Start), text = viewmodel.GetSettings().PasswordMinimumCharacters[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.secondaryVariant,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = loginUiState?.userLoginPassword ?: "",
                            onValueChange = { viewmodel?.onPasswordChangeLogin(it) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.DarkGray,
                                unfocusedBorderColor = Color.LightGray,
                                cursorColor = Color.DarkGray
                            ),
                            placeholder = { Text(viewmodel.GetSettings().Password[code]!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            }) },
                            visualTransformation = if (visibility_1) VisualTransformation.None else PasswordVisualTransformation(),
                            isError = isErrorPassword,
                            trailingIcon = {
                                androidx.compose.material3.Icon(
                                    imageVector = if (visibility_1) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                                    modifier = Modifier
                                        .clickable (interactionSource =interactionSource, indication = null){ visibility_1 = !visibility_1 },
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.secondaryVariant
                                )

                            },
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.secondaryVariant)
                        )

                        if (isErrorPassword) {
                            Text(loginUiState.errorPasswordlLogin, color = Color.Red)
                        }

                    }
                }

                item {
                    Spacer(Modifier.height(100.dp))
                    BtnSuper(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(10.dp),
                        outlineColor = MaterialTheme.colors.secondaryVariant,
                        IsIcon = false,
                        color = MaterialTheme.colors.onPrimary,
                        title = viewmodel.GetSettings().ContinueBtn[code]!!.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        fontSize = 15.sp,
                        Outline = true,
                        FontColor = MaterialTheme.colors.secondaryVariant,
                        onClick = {
                            viewmodel?.createUser(NavToMainScreen)
                        }
                    )
                }

            }


        })



}