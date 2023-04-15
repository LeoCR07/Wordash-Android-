package com.example.betadiccompose.Domain.Ads

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdMobRewarded  @Inject constructor(private val context: android.content.Context) {

    var rewardedAd: RewardedAd? = null

    fun LoadAd(){
        val adUnitId = "ca-app-pub-7052510061101888/6598585759"
        var adRequest = AdRequest.Builder().build()


        RewardedAd.load(context,adUnitId, adRequest, object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    rewardedAd = null
                    println("el anuncio no cargo ${adError.message}")
                    LoadAd()
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    println("Ad was loaded.")
                    rewardedAd = ad

                }
            })
    }

    fun showAd(activity: Activity,OnClick:()->Unit){


        if (rewardedAd != null) {
            rewardedAd?.let { ad ->
                ad.show(activity, OnUserEarnedRewardListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        OnClick()
                    }

                })
            } ?: run {
                Log.d(ContentValues.TAG, "The rewarded ad wasn't ready yet.")
                LoadAd()
            }
            rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                override fun onAdClicked() {
                    println("Click")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    //Log.d(TAG, "Ad dismissed fullscreen content.")
                    println("Se cerro")
                    LoadAd()
                    rewardedAd = null
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    // Called when ad fails to show.
                    //Log.e(TAG, "Ad failed to show fullscreen content.")
                    println(" ${p0.message}")
                    rewardedAd = null
                }

                override fun onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    println("impresion")
                }

                override fun onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    println("Ad showed fullscreen content.")
                }
            }
        }else{
            LoadAd()
        }

    }











}