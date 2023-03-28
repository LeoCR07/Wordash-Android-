package com.example.betadiccompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Color(0xFF272727) ,//Teal200,
    onSecondary = Color(0xFF3A3A3A), //Lineas
    onSurface =  Color(0xFF2196F3), //
    background =  Color(0xFF141414), //fondo
    onPrimary =  Color(0xFF1B1B1B), //Cuadro pequeño y nav,topAppBar
    secondaryVariant =  Color(0xFFB9B9B9)  //Textos, iconos

)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Color(0xFFEBEBEB),
    onSecondary = Color(0xFFF3F3F3),
    onSurface =  Color(0xFF2196F3),
    background =  Color(0xFFFFFFFF),
    onPrimary =  Color(0xFFFFFFFF),
    secondaryVariant =  Color(0xFF000000)


    /* Other default colors to override

    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun BetaDicComposeTheme(
    content: @Composable () -> Unit,
    viewModel :VocabularyViewModel
) {

    val calendar = Calendar.getInstance()
    val hora = calendar.get(Calendar.HOUR_OF_DAY)

    var colors = if (hora>=18) {
        DarkColorPalette
    } else {
        LightColorPalette
    }


    if(viewModel.GetTheme() == 1){
        colors = DarkColorPalette
    }else if(viewModel.GetTheme() == 2){
        colors = LightColorPalette
    }


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,

    )
}

@Composable
fun splash(
    content: @Composable () -> Unit,
) {


    var colors =  DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,)
}