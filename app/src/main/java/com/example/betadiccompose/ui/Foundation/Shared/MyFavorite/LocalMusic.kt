package com.example.betadiccompose.ui.Foundation.Shared.MyFavorite

import android.content.Context
import android.media.MediaPlayer
import com.example.betadiccompose.R


fun LocalMusic(context: Context){
    var mediaPlayer = MediaPlayer.create(context,R.raw.fav)
    mediaPlayer.start()
}