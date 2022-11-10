package com.example.betadiccompose.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsMenu(val icon: ImageVector,
                       val title :String,
val route : String){

object Pantalla_1 :ItemsMenu(Icons.Default.Face,"Learn","vocabulary")
    object Pantalla_2 :ItemsMenu(Icons.Default.Wallet,"Play","levels")
    object Pantalla_3 :ItemsMenu(Icons.Default.Store,"Store","levels")
}
