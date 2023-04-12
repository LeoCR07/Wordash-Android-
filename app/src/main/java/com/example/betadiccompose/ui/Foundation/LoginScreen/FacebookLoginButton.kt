package com.example.betadiccompose.ui.Foundation.LoginScreen

// Importa las siguientes clases
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.betadiccompose.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class CustomLoginButton(context: Context, attrs: AttributeSet) : LoginButton(context, attrs) {

    init {
        text = "Iniciar sesión con Facebook"
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
        textSize = 16f
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.facebo, 0, 0, 0)
        background = ContextCompat.getDrawable(context, R.drawable.facebo)
    }
}

