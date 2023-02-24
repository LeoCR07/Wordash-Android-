package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun currentRoute(navController: NavController):String?{
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}