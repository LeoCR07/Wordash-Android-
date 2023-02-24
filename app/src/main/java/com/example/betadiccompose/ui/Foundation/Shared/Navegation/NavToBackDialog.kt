package com.example.betadiccompose.ui.Foundation.Shared

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavToBackDialog(onBack: () -> Unit, texto: String = "Estas seguro que quieres salir?") {

    var showAlertDialog by remember { mutableStateOf(false) }

    BackHandler {
        showAlertDialog = true
    }


    if(showAlertDialog){
        ExitDialog(
            hideAlertDialog = { showAlertDialog = false },
            showAlertDialog = {showAlertDialog = true },
            texto = texto,
            onBack = {onBack()}
        )
    }

}