package com.example.betadiccompose.ui.Foundation.GamesScreen.Sort

import android.content.Context
import android.media.MediaPlayer
import com.example.betadiccompose.R

fun KeySound(context: Context,beat :Int){
    var mediaPlayer = MediaPlayer.create(context, beat)
    mediaPlayer.start()
}