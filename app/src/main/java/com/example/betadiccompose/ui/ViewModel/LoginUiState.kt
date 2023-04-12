package com.example.betadiccompose.ui.ViewModel

data class LoginUiState(

    //Sign up
    // Datos
    val userEmailSignUp: String = "",
    val passwordSignUp: String = "",

    //Almacena el error a mostras
    var errorEmail :String = "",
    var errorPassword :String = "",

    //Valida los boolenos
    val signUpErrorEmail: String? = null,
    val signUpErrorPassword: String? = null,


    //Login
    //datos
    val userLoginName: String = "",
    val userLoginEmail:String = "",
    val userLoginPassword: String = "",

    //Almacena el error a mostras
    var errorEmailLogin :String = "",
    var errorPasswordlLogin:String = "",
    var errorUserNameLogin:String = "",

    // Validar los booleanos
    val loginErrorEmail: String? = null,
    val loginErrorPassword: String? = null,
    val loginErrorUserName: String? = null,

    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val loginError: String? = null,
)