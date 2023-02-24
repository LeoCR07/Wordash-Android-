package com.example.betadiccompose.ui.Foundation.Shared.Books

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.theme.Purple200
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun PlayerListener() {

    val contex = LocalContext.current

    var totalDuration by remember { mutableStateOf(0L) }

    //var currentTime by remember { mutableStateOf(0L) }
    var currentTime = 0L

    var bufferedPercentage by remember { mutableStateOf(0) }
   // var bufferedPercentage = 0

    val exoPlayer = remember {
        ExoPlayer.Builder(contex).build().apply {
            setMediaItem(
                MediaItem.fromUri("https://dicvocabulary.s3.us-east-2.amazonaws.com/Track419.mp3")
            )
            prepare()
            playWhenReady = true
        }
    }


        /*
    val exoPlayer =
        ExoPlayer
            .Builder(contex)
            .build()
            .apply {
                val defaultDataSource = DefaultDataSource.Factory(contex)
                val datasourceFactory : DataSource.Factory =
                    DefaultDataSource.Factory (contex,defaultDataSource)
                val sorce = ProgressiveMediaSource.Factory(datasourceFactory)
                    .createMediaSource(MediaItem.fromUri("https://dicvocabulary.s3.us-east-2.amazonaws.com/Track419.mp3"))

                setMediaSource(sorce)
                prepare()
            }
        */

    Box(modifier = Modifier) {



        DisposableEffect(key1 = Unit){


            //exoPlayer.playWhenReady = true


            val listener =
                object : Player.Listener {
                    override fun onEvents(player: Player, events: Player.Events) {
                        super.onEvents(player, events)
                        totalDuration = player.duration.coerceAtLeast(0L)
                        currentTime = player.currentPosition.coerceAtLeast(0L)
                        bufferedPercentage = player.bufferedPercentage

                    }
                }
            exoPlayer.addListener(listener)

            onDispose {
                exoPlayer.removeListener(listener)
                exoPlayer.release()
            }


        }

        BottomControls(
            modifier =  Modifier
                .fillMaxWidth(),
            totalDuration = {totalDuration},
            currentTime = { exoPlayer.currentPosition.coerceAtLeast(0L) },
            bufferPercentage = { bufferedPercentage },
            onSeekChanged = { timeMs: Float -> exoPlayer.seekTo(timeMs.toLong()) }
        )
    }







}

@Composable
fun BottomControls(
    modifier: Modifier = Modifier,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferPercentage: () -> Int,
    onSeekChanged: (timeMs: Float) -> Unit
) {

    val duration = remember(totalDuration()) { totalDuration() }

   // val videoTime = remember(currentTime()) { currentTime() }

    var videoTime = currentTime()

    val buffer = remember(bufferPercentage()) { bufferPercentage() }
    //var buffer = bufferPercentage()

    println("Se construye")
    Column(modifier = modifier.padding(bottom = 32.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // buffer bar
            Slider(
                value = buffer.toFloat(),
                enabled = false,
                onValueChange = { /*do nothing*/},
                valueRange = 0f..100f,
                colors =
                SliderDefaults.colors(
                    disabledThumbColor = Color.Transparent,
                    disabledActiveTrackColor = Color.Gray
                )
            )

            // seek bar
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = videoTime.toFloat(),
                onValueChange = onSeekChanged,
                valueRange = 0f..duration.toFloat(),
                colors =
                SliderDefaults.colors(
                    thumbColor = Purple200,
                    activeTickColor = Purple200
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = duration.formatMinSec(),
                color = Purple200
            )

        }
    }
}

fun Long.formatMinSec(): String {
    return if (this == 0L) {
        "..."
    } else {
        String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(this),
            TimeUnit.MILLISECONDS.toSeconds(this) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(this)
                    )
        )
    }
}