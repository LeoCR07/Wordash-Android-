package com.example.betadiccompose.Domain


import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.authentication.domain.getGoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task


class AuthResultContract : ActivityResultContract<Int,Task<GoogleSignInAccount>?>() {

    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
        return when (resultCode){
            Activity.RESULT_OK-> GoogleSignIn.getSignedInAccountFromIntent(intent)
            else -> null
        }
    }

     override fun createIntent(context: Context, input: Int): Intent {
         getGoogleSignInClient(context).signOut()
         return getGoogleSignInClient(context).signInIntent.putExtra("input",input)
     }


}