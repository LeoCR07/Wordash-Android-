package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.Foundation.Shared.ExitDialog
import com.example.betadiccompose.ui.Foundation.Shared.NavToBackDialog

@Composable
fun TopAppBarLevel(percentage: Float, IconSkill: Int, onBack: () -> Unit) {

    var showAlertDialog by remember { mutableStateOf(false) }

    if(showAlertDialog){
        ExitDialog(
            hideAlertDialog = { showAlertDialog = false },
            showAlertDialog = {showAlertDialog = true },
            texto = "Estas seguro que deseas salir ?",
            onBack = {onBack()}
        )
    }
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly) {

        Icon(
            Icons.Default.Clear,contentDescription = null,
            modifier = Modifier
                .clickable {
                    showAlertDialog = true
                }
                .size(30.dp))

        Spacer(modifier = Modifier.width(20.dp))

        customProgressBar(percentage)

        Spacer(modifier = Modifier.width(20.dp))



    }
}