package com.example.betadiccompose.ui.ViewModel

data class LoginUiState(
    val userName: String = "",
    val password: String = "",

    val userNamelSignUp: String = "",
    val userEmailSignUp: String = "",
    val passwordSignUp: String = "",
    val confirmPasswordSignUp: String = "",
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,

    val signUpErrorEmail: String? = null,
    val signUpErrorPassword: String? = null,
    val signUpErrorConfirPassword: String? = null,


    val loginError: String? = null,
)