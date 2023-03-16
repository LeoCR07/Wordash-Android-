package com.example.betadiccompose.Domain.Ads

import android.app.Activity
import android.widget.Toast
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import javax.inject.Inject

class AdMobInterstital @Inject constructor(private val context: android.content.Context) {
    var mInterstitialAd: InterstitialAd? = null
   // var context = mainActivity.applicationContext

    fun LoadAd(){
        val adUnitId = "ca-app-pub-3940256099942544/1033173712"
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context,adUnitId,adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
                println("the interstial is failed")
                Toast.makeText(context,"the interstial is failed",Toast.LENGTH_LONG).show()
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                println("Ad was loaded")
                Toast.makeText(context,"Ad was loaded",Toast.LENGTH_LONG).show()

            }
        })
    }

    fun showAd(activity: Activity,onClick:()->Unit){
        if (mInterstitialAd != null) {

            mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {


                override fun onAdClicked() {
                    println("Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    println("Ad dismissed fullscreen content.")
                    Toast.makeText(context,"Ad dismissed fullscreen content.",Toast.LENGTH_SHORT).show()
                    mInterstitialAd = null
                    LoadAd()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    // Called when ad fails to show.
                    println("Ad failed to show fullscreen content.")
                    Toast.makeText(context,"Ad failed to show fullscreen content.",Toast.LENGTH_SHORT).show()
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    println("Ad recorded an impression.")
                    Toast.makeText(context,"Ad recorded an impression.",Toast.LENGTH_SHORT).show()
                }

                override fun onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    println("Ad showed fullscreen content.")
                    onClick()
                }
            }
            mInterstitialAd?.show(activity)
        } else {
            LoadAd()
            Toast.makeText(context,"The interstitial ad wasn't ready yet.",Toast.LENGTH_SHORT).show()
            println("The interstitial ad wasn't ready yet.")
        }
    }

}