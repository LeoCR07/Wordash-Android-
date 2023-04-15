package com.example.authentication.ui.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.Runtime.MyGame
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.Foundation.Shared.Button.BtnLogin
import com.example.betadiccompose.ui.Foundation.Shared.Button.OutlinedButtonSample
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(
    NavToMainScreen: () -> Unit,
    viewmodel: VocabularyViewModel,
    onNavToAccount : ()->Unit,
) {

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

            val loginUiState = viewmodel?.loginUiState
            val isErrorEmail = loginUiState.signUpErrorEmail != null
            val isErrorEmailPassword = loginUiState?.signUpErrorPassword != null

            if(viewmodel.loginUiState.isLoading){
                Box(modifier = Modifier.fillMaxSize()){
                    androidx.compose.material.CircularProgressIndicator(
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }

            LazyColumn(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
                item {
                    Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(20.dp, 0.dp, 20.dp, 0.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    var textValue by remember { mutableStateOf("") }

                    // Icono
                    androidx.compose.material.Icon(
                        painter = painterResource(
                            if(viewmodel.GetLearnLenguage() =="English")R.drawable.flag_states else R.drawable.flag_spain
                        ),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        tint = Color.Unspecified
                    )

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
                        color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )




                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = loginUiState?.userEmailSignUp ?: "",
                        onValueChange = { viewmodel?.onUserNameChangeSignup(it) },
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
                        textStyle = TextStyle(fontSize = 18.sp, color = androidx.compose.material.MaterialTheme.colors.secondaryVariant)

                    )

                    if (!loginUiState.signUpErrorEmail.isNullOrEmpty()) {
                        Text(loginUiState.errorEmail, color = Color.Red)
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start), text = viewmodel.GetSettings().Password[code]!!.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        fontWeight = FontWeight.ExtraBold,
                        color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = loginUiState?.passwordSignUp ?: "",
                        onValueChange = { viewmodel?.onPasswordChangeSignup(it) },
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
                        isError = isErrorEmailPassword,
                        trailingIcon = {
                            Icon(
                                imageVector = if (visibility_1) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                                modifier = Modifier
                                    .clickable (interactionSource =interactionSource, indication = null){ visibility_1 = !visibility_1 },
                                contentDescription = null,
                                tint = androidx.compose.material.MaterialTheme.colors.secondaryVariant
                            )

                        },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 18.sp, color = androidx.compose.material.MaterialTheme.colors.secondaryVariant)
                    )

                    if (!loginUiState.signUpErrorPassword.isNullOrEmpty()) {
                        Text(loginUiState.errorPassword , color = Color.Red)
                    }


                }

                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {


                            BtnSuper(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(10.dp),
                                outlineColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                                IsIcon = false,
                                color =androidx.compose.material.MaterialTheme.colors.onPrimary,
                                title = viewmodel.GetSettings().ContinueBtn[code]!!.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                },
                                fontSize = 15.sp,
                                Outline = true,
                                FontColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                                onClick = {
                                    viewmodel?.loginUser(NavToMainScreen)
                                }
                            )
                        }
                    }
                }

            }



        })

}




