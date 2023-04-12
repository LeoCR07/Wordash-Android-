package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.Foundation.Shared.ExitDialog
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun secondTopAppBarLevel(percentage: Float, onBack: () -> Unit,viewmodel:VocabularyViewModel) {

    var showAlertDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    if(showAlertDialog){
        ExitDialog(
            hideAlertDialog = { showAlertDialog = false },
            showAlertDialog = {showAlertDialog = true },
            texto = "Estas seguro que deseas salir ?",
            onBack = {onBack()},
            viewmodel = viewmodel
        )
    }
    Spacer(modifier = Modifier.height(20.dp).background(MaterialTheme.colors.background))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.background(MaterialTheme.colors.background)) {

        Icon(
            Icons.Default.Clear,contentDescription = null,
            modifier = Modifier
                .clickable (interactionSource = interactionSource,indication = null){
                    showAlertDialog = true
                }
                .size(30.dp)
            , tint = MaterialTheme.colors.secondaryVariant)

        Spacer(modifier = Modifier.width(20.dp).background(MaterialTheme.colors.background))

        customProgressBar(percentage)

        Spacer(modifier = Modifier.width(20.dp).background(MaterialTheme.colors.background))



    }
}
