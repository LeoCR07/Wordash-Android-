package com.example.betadiccompose.ui.Foundation.GamesScreen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.Domain.Provider.SpeechRecognizerContract
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo
import com.example.betadiccompose.ui.Foundation.Shared.NameGame
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeakToTalk(viewModel: VocabularyViewModel, function: () -> Unit) {

    val porcentaje = viewModel.Step.value / 18f

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.RECORD_AUDIO
    )
    SideEffect {
        permissionState.launchPermissionRequest()
    }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract(),
        onResult = {
            viewModel.changeTextValue(it.toString())
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

        GetLogo(icon = true, titulo = "Days")
        NameGame(titulo = "Days")
        LinearProgressIndicator(
            modifier = Modifier
                .height(4.dp)
                .width(340.dp),
            progress = porcentaje
        )

        //fondo("")

        Spacer(modifier = Modifier.height(0.dp))

        Text(text = "Palabra", fontSize = 30.sp)

        Spacer(modifier = Modifier.height(50.dp))

        if (viewModel.state.text != null) {
            Text(
                text = viewModel.state.text!!,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(76.dp))
        }
        Button(
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)
                .background(Color(0xFF0897D8)),

            onClick = {
                if (permissionState.status.isGranted) {
                    speechRecognizerLauncher.launch(Unit)
                } else
                    permissionState.launchPermissionRequest()
            }) {
            Icon(
                Icons.Sharp.Mic,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp),
                tint = Color.White)

    }


        //Text(text = "Speak")


}



}