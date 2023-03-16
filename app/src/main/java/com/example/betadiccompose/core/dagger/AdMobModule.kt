package com.example.betadiccompose.core.dagger

import android.content.Context
import com.example.betadiccompose.Domain.Ads.AdMobInterstital
import com.example.betadiccompose.Domain.Ads.AdMobRewarded
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdMobModule {

    @Singleton
    @Provides
    fun provideAdMobInterstitial(@ApplicationContext context: Context): AdMobInterstital =
        AdMobInterstital(context)

    @Singleton
    @Provides
    fun provideAdMobRewarded(@ApplicationContext context: Context): AdMobRewarded =
        AdMobRewarded(context)

}