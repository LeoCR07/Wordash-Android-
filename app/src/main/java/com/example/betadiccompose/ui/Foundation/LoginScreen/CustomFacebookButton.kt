package com.example.betadiccompose.ui.Foundation.LoginScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.betadiccompose.core.dagger.FacebookUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

@Composable
public fun CustomFacebookButton(
    modifier: Modifier ,
    enabled: Boolean = true,
    onSuccess: (LoginResult) -> Unit,
    onCancel: () -> Unit,
    onError: (FacebookException) -> Unit,
) {

    val callbackManager = FacebookUtil.callbackManager
    val loginText = "facebbok"
    AndroidView(
        modifier = modifier,
        factory = ::LoginButton,
        update = { button ->
          //  button.loginText = loginText
            //button.setLoginText(loginText)
            button.setPermissions("email")
            button.isEnabled = enabled

            button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    onSuccess(result)
                  //  Timber.e("Login : ${result.accessToken}")
                }

                override fun onCancel() {
                    onCancel()
                  //  Timber.e("Login : On Cancel")
                }

                override fun onError(error: FacebookException) {
                    onError(error)
                }
            })
        }
    )
}
