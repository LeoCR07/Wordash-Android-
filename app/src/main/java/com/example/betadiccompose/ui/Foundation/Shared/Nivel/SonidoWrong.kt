package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import android.content.Context
import android.media.MediaPlayer
import com.example.betadiccompose.R

fun SonidoWrong(context: Context,sonido:Int = R.raw.wrong){
    var mediaPlayer = MediaPlayer.create(context, sonido)
    mediaPlayer.start()
}