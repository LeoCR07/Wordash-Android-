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
import java.util.*

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Color(0xFF272727) ,//Teal200,
    onSecondary = Color(0xFF3A3A3A), //Lineas
    onSurface =  Color(0xFF2196F3), //
    background =  Color(0xFF141414), //fondo
    onPrimary =  Color(0xFF272727), //Cuadro pequeño y nav,topAppBar
    secondaryVariant =  Color(0xFFB9B9B9)  //Textos, iconos

)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Color(0xFFEBEBEB),
    onSecondary = Color(0xFFF3F3F3),
    onSurface =  Color(0xFF313131),
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
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val calendar = Calendar.getInstance()
    val hora = calendar.get(Calendar.HOUR_OF_DAY)


    /*
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
*/

    val colors = if (hora>=18) {
        DarkColorPalette
    } else {
       // DarkColorPalette
        LightColorPalette
    }


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}


@Composable
fun Starting(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        //colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}