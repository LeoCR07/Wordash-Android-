package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DialogSignOut(show:Boolean ,dimisissDialog :()->Unit) {

    if(show){
        AlertDialog(
            onDismissRequest = { dimisissDialog() },
            title = { Text(text = "¿Esta seguro que deseas cerrar la session?")},
            dismissButton = {
                Button(onClick = { dimisissDialog()}) {
                    Text(text = "Cancelar")
                }
            },
            confirmButton = {
                Button(onClick = { }) {
                    Text(text = "OK")
                }
            })
    }
}