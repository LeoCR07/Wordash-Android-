package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import android.content.Context
import android.media.MediaPlayer
import com.example.betadiccompose.R

fun SonidoWrong(context: Context){
    var mediaPlayer = MediaPlayer.create(context, R.raw.wrong)
    mediaPlayer.start()
}