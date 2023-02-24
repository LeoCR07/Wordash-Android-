package com.example.betadiccompose.core.dagger

import com.facebook.CallbackManager

object FacebookUtil {
    val callbackManager by lazy {
        CallbackManager.Factory.create()
    }
}
