package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import android.content.Context
import android.media.MediaPlayer
import com.example.betadiccompose.R

import javax.inject.Inject

fun SonidoLocal (context:Context) {
    var mediaPlayer = MediaPlayer.create(context, R.raw.rigth)
    mediaPlayer.start()
}