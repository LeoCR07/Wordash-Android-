package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.betadiccompose.R


@Composable
fun ExitDialog(hideAlertDialog:()->Unit ,showAlertDialog:()->Unit,texto:String,onBack:()->Unit) {

    AlertDialog(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.onPrimary),
        onDismissRequest = {
            showAlertDialog()
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = texto,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color=MaterialTheme.colors.secondaryVariant)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    hideAlertDialog()
                    onBack()
                }
            ) {
                Text("Confirm",color=MaterialTheme.colors.secondaryVariant)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    hideAlertDialog()
                }
            ) {
                Text(
                    text ="Cancel",
                    color = MaterialTheme.colors.secondaryVariant.copy(0.6f))
            }
        }
    )
}