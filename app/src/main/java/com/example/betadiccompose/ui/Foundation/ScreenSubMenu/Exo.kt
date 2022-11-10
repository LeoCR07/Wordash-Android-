package com.example.betadiccompose.ui.Foundation.ScreenSubMenu

import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import java.net.URI
import java.nio.file.WatchEvent.Modifier

@Preview
@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun VideoPlayer() {
    val contex = LocalContext.current
    val url = "https://dicvocabulary.s3.us-east-2.amazonaws.com/Music.mp3"

    val exoPlayer = remember {
        ExoPlayer
            .Builder(contex)
            .build()
            .apply {
                val defaultDataSource = DefaultDataSource.Factory(contex)
                val datasourceFactory : DataSource.Factory =DefaultDataSource.Factory (contex,defaultDataSource)
                val sorce = ProgressiveMediaSource.Factory(datasourceFactory)
                    .createMediaSource(MediaItem.fromUri(url))

                setMediaSource(sorce)
                prepare()
        }
    }

    exoPlayer.playWhenReady = true
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE


    DisposableEffect(
        AndroidView(factory = {
            PlayerView(contex).apply {
                hideController()
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        })
    ) {
        onDispose { exoPlayer.release() }
    }
}

@Composable
fun SpotlightSScreen(){
    Box() {
        VideoPlayer()
    }
}