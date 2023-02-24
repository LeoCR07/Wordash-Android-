package com.example.authentication.domain

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions



val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken("48761715141-rjoe4i5pf5t3n0glij8t0btth27p05g3.apps.googleusercontent.com")
    .requestEmail()
    .build()

fun getGoogleSignInClient(context: Context): GoogleSignInClient{

    //GoogleSignIn.getClient(context,signInOptions).signOut()
    return GoogleSignIn.getClient(context,signInOptions)
}



