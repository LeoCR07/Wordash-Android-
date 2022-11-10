package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.layout.Box
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.Foundation.ScreenVocabulary.Thumb


@Composable
fun Alert(show:Boolean ,dimisissDialog :()->Unit) {

    if(show){
        
        AlertDialog(
            onDismissRequest = { dimisissDialog()},
            title = { Text(text = "Dialogo")},
            confirmButton = {
                Button(onClick = { dimisissDialog()}) {
                    Text(text = "OK")
                }
               },
            dismissButton = {
                Button(onClick = { dimisissDialog()}) {
                Text(text = "Cancelar")
            }
         })
        }
    }

