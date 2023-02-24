package com.example.betadiccompose.Domain.Game_Provider

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContract
import kotlin.collections.ArrayList

class SpeechRecognizerContract: ActivityResultContract<Unit, ArrayList<String>?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
          RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH


        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            "en-US"
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Come on, just say it"
        )

        return intent
    }
    override fun parseResult(resultCode: Int, intent: Intent?): ArrayList<String>? {
        if (resultCode != Activity.RESULT_OK){
            return null
        }
        return intent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
    }
}