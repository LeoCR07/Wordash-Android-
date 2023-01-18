package com.example.betadiccompose.ui.Foundation.Shared

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun Sonido(id: Int = 0, category: String = "", url: String = "", vocalview: VocabularyViewModel) {

    CoroutineScope(Dispatchers.IO).launch {
        try {

              Media(category,id,url,vocalview)

           // Exo(category, id, context)
        }catch (e:Exception){
            println("El error es: ${e.message}")
        }
    }

}

private fun Media(category: String, id: Int, uri: String, vocalview: VocabularyViewModel) {

    var url = ""

    if(uri != ""){
        url = uri
    }else{
        url = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${vocalview.GetLearnLenguage()}/$category/$id.mp3"
    }


    val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )

        setDataSource(url)
        prepare() // might take long! (for buffering, etc)
       // start()
    }

    mediaPlayer.start()
}

public fun Exo(category: String, id: Int, context: Context) {
    val url = "https://d1i3grysbjja6f.cloudfront.net/Sonido/$category/0/$id.mp3"
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaItem_1 = MediaItem.fromUri(Uri.parse(url))


    exoPlayer.setMediaItem(mediaItem_1)
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true
    exoPlayer.release()
}