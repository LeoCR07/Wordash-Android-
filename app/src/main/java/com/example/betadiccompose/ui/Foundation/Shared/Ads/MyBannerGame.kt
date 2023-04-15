package com.example.betadiccompose.ui.Foundation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun MyBannerGame() {
    AndroidView(

        modifier = Modifier
            .fillMaxWidth(),
        factory = {
            AdView(it).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = "ca-app-pub-7052510061101888/8674956896"
                loadAd(AdRequest.Builder().build())
            }
        })
}