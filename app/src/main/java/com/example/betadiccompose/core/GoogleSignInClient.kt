package com.example.authentication.domain

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions



val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken("90652262057-2n418683lqpv9pe8js6c40r1i1cpomrh.apps.googleusercontent.com")
    .requestEmail()
    .build()

fun getGoogleSignInClient(context: Context): GoogleSignInClient{

    //GoogleSignIn.getClient(context,signInOptions).signOut()
    return GoogleSignIn.getClient(context,signInOptions)
}



