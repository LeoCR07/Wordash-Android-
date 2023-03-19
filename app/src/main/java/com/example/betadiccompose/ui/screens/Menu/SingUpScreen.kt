package com.example.authentication.ui.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.Foundation.Shared.Button.BtnLogin
import com.example.betadiccompose.ui.Foundation.Shared.Button.OutlinedButtonSample
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(
    viewmodel: VocabularyViewModel,
    onNavToAccount : ()->Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp, 0.dp, 20.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        var textValue by remember { mutableStateOf("") }

        // Icono
        androidx.compose.material.Icon(
            painter = painterResource(R.drawable.flag_states),
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
            text = "Email",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp)




        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textValue,
            onValueChange =  { textValue = it },
            colors =TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
            ),
            placeholder = { Text("Email") },
        )


        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),text = "Contraseña (minimo 6 caracteres)",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textValue,
            onValueChange =  { textValue = it },
            colors =TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
            ),
            placeholder = { Text("Contraseña") },
        )


    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){


            BtnSuper(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp),
                color = Color.Black,
                IsIcon = false,
                title = "Continuar",
                fontSize = 15.sp,
                FontColor =Color.White, onClick = { /*TODO*/ }
            )
        }
    }
}