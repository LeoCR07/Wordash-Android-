package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Foundation.ScreenVocabulary.Thumb
import com.example.betadiccompose.R

@Preview
@Composable
fun TopApp() {
    var opendialog = remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "hola") },
        navigationIcon = {

            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = null,
            modifier = Modifier
                .clickable { opendialog.value = true }
                .padding(10.dp)
                .clip(CircleShape))

        },
        actions = {

            Text(text = "12", fontSize = 20.sp)

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )

            }

        },
        modifier = Modifier
            .padding(4.dp, 2.dp, 4.dp, 2.dp)
            .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
           // .clip(CutCornerShape(0.dp,0.dp,5.dp,5.dp))
    )

    Alert(show = opendialog.value, dimisissDialog =  {opendialog.value = false})
}