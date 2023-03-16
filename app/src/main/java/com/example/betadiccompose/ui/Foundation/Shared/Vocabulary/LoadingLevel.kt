package com.example.betadiccompose.ui.Foundation.Shared.Vocabulary

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.Local_Animation
import com.facebook.bolts.Task.Companion.delay


@Composable
fun LoadingLevel() {


    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Local_Animation(animacion = R.raw.book, modifier = Modifier.size(500.dp))
    }


        /*
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.padding(6.dp)) {
        Local_Animation(animacion = R.raw.book, modifier = Modifier.size(500.dp))
        Text(
            modifier = Modifier
                .fillMaxHeight()
                ,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colors.secondaryVariant,
            text = "Los anuncios son parte de nuestro modelo de negocio para mantener la aplicación sin costo.")
    }
*/

    /*
    var loadingText by remember { mutableStateOf("The level is loading..") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {



        Text(
            color = MaterialTheme.colors.secondaryVariant,
            text = loadingText, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(40.dp))

        CircularProgressIndicator(
            color = MaterialTheme.colors.onSurface)
    }

    */
}