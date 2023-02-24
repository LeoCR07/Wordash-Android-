package com.example.authentication.ui.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.authentication.ui.Presentation.Login.BtnLogin
import com.example.authentication.ui.Presentation.Login.LineColor
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(
    onNavToAccount : ()->Unit,
    ClickSingUp : ()->Unit,
    loginViewModel: VocabularyViewModel? = null,
) {


    var visibility_1 by remember {
        mutableStateOf(false)
    }

    var visibility_2 by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val loginUiState = loginViewModel?.loginUiState
    val isErrorEmail = loginUiState?.signUpErrorEmail != null
    val isErrorConfirmPassword = loginUiState?.signUpErrorConfirPassword != null
    val isErrorEmailPassword = loginUiState?.signUpErrorPassword != null


    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{
            val loginUiState = loginViewModel?.loginUiState
            //val isError = loginUiState?.signUpError != null

            Spacer(modifier = Modifier.height(20.dp))

            Icon(
                painter = painterResource(id = R.drawable.thunder),
                contentDescription = null,
                modifier = Modifier.size(65.dp),
                tint = Color.Unspecified)

            Spacer(modifier = Modifier.height(20.dp))

            BtnLogin(
                title ="Continue with Facebook",
                color = Color(0xFF3b5998),
                icon = R.drawable.facebook,
                FontColor = Color.White,
                onClick = ClickSingUp
            )

            Spacer(modifier = Modifier.height(12.dp))
            BtnLogin(
                title = "Continue with Google",
                icon = R.drawable.google,
                Outline = true,
                FontColor = Color.Black,
                onClick = ClickSingUp
            )
            Spacer(modifier = Modifier.height(15.dp))
            LineColor(0.5f)
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "sign up with your email",
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp),
                text = "Name User",
                textAlign = TextAlign.Left)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp)
                    .height(60.dp),
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    )
                },
             //   isError = isError
            )


            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp),
                text = "Email",
                textAlign = TextAlign.Left)


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp)
                    .height(60.dp),
                value = loginUiState?.userEmailSignUp ?: "",
                onValueChange = {loginViewModel?.onUserNameChangeSignup(it)},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    )
                },
                isError = isErrorEmail
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp),
                text = "Password",
                textAlign = TextAlign.Left)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp)
                    .height(60.dp),
                value = loginUiState?.passwordSignUp ?: "",
                onValueChange = { loginViewModel?.onPasswordChangeSignup(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                    )
                },
                visualTransformation = if(visibility_1) VisualTransformation.None else PasswordVisualTransformation(),
                isError = isErrorEmailPassword,
                trailingIcon = {
                    Icon(
                        imageVector = if(visibility_1) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                        modifier = Modifier
                            .clickable { visibility_1 = !visibility_1 },
                        contentDescription = null,
                        tint = Color.Black
                    )

                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp),
                text = "Repeat you password",
                textAlign = TextAlign.Left)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp,0.dp,15.dp,0.dp)
                    .height(60.dp),
                value = loginUiState?.confirmPasswordSignUp ?: "",
                onValueChange = { loginViewModel?.onConfirmPasswordChange(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                    )
                },
                visualTransformation = if(visibility_2) VisualTransformation.None else PasswordVisualTransformation(),
                isError = isErrorConfirmPassword,
                trailingIcon = {
                    Icon(
                        imageVector = if(visibility_2) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                        modifier = Modifier
                            .clickable { visibility_2 = !visibility_2 },
                        contentDescription = null,
                        tint = Color.Black
                    )

                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            BtnLogin(
                title = "Sign in",
                color = Color(0xFF6CC73A),
                IsIcon = false,
                FontColor = Color.White,
                onClick =  { loginViewModel?.createUser(context) }
            )

            Spacer(modifier = Modifier.height(20.dp))


        }
    }

    if (loginUiState?.isLoading == true){
        CircularProgressIndicator()
    }

    LaunchedEffect(key1 = loginViewModel?.hasUser){
        if (loginViewModel?.hasUser == true){
            onNavToAccount.invoke()
        }
    }
}