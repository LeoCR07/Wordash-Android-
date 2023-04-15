package com.example.betadiccompose.Domain

import android.content.Context
import com.google.gson.Gson
import java.io.InputStreamReader
import javax.inject.Inject

class LocalFiles @Inject constructor(){


    private  val TARGETS = "archivo.json"
    private val SETTING = "LocalTranslations.json"

    data class MyCodes(val language: String, val code: String, val local:String)

    data class MySettings(
        val MyFavoriteWords: Map<String, String>,
        val MyFavoritePhrases: Map<String, String>,
        val MyFavoriteVerbs: Map<String, String>,
        val SeeMore: Map<String, String>,
        val YourLanguage: Map<String, String>,
        val YourNextLanguage: Map<String, String>,
        val ApplicationTheme: Map<String, String>,
        val credits: Map<String, String>,
        val SignOff : Map<String, String>,
        val Settings : Map<String, String>,
        val English: Map<String, String>,
        val Spanish: Map<String, String>,
        val DayNight: Map<String, String>,
        val day: Map<String, String>,
        val Night: Map<String, String>,
        val Cancel: Map<String, String>,
        val confirm: Map<String, String>,
        val AreYouSureYouWantToCloseTheSession: Map<String, String>,
    val AreYouSureYouWantToGoOut: Map<String, String>,
    val ListenCarefully: Map<String, String>,
    val WhatIsTheWellWrittenOption: Map<String, String>,
    val HowDoYouSay: Map<String, String>,
    val SelectTheCorrectAudio: Map<String, String>,
    val ItsTimeToListenToYouPronounce: Map<String, String>,
    val PressToTalk: Map<String, String>,
    val JumpExercise: Map<String, String>,
    val WriteWhatYouHear: Map<String, String>,
    val LetGoOneMoreTimePronounce: Map<String, String>,
    val ThisWillNotBeEasySay: Map<String, String>,
    val TranslateAndWrite: Map<String, String>,
    val CongratulationsYouHavePassedTheChallenge: Map<String, String>,
    val NextLevel: Map<String, String>,
    val PlayAgain: Map<String, String>,
    val GoOut: Map<String, String>,
    val skip: Map<String, String>,
    val ContinueWith: Map<String, String>,
    val Email: Map<String, String>,
    val Password: Map<String, String>,
    val PasswordMinimumCharacters: Map<String, String>,
    val ContinueBtn: Map<String, String>,
    val UserIsAlreadyRegistered: Map<String, String>,
    val IncorrectPassword: Map<String, String>,
    val InvalidMail: Map<String, String>,
    val WrongPasswordOreMail: Map<String, String>,
    val Speak: Map<String, String>,
    val YouNeedToPracticeMore: Map<String, String>,
    val GoToStudy: Map<String, String>,
    val IForGotMyPassword: Map<String, String>,

        val YouDoNotHaveAnAccount: Map<String, String>,
        val CreateAccount: Map<String, String>,
        val Username: Map<String, String>,
        val PleaseFillAllSpaces: Map<String, String>,
        val EmailIsInTheWrongFormat: Map<String, String>,
        val MinimumBetweenToCharacters: Map<String, String>,
        val ThisEmailAlreadyExistsAssociatedWithAnAccount: Map<String, String>,
        val ThisEmailDoesNotExistAssociatedWithAnAccount: Map<String, String>,
        val PasswordIsWrong: Map<String, String>,
        val Or: Map<String, String>,
        val IWantToLearn: Map<String, String>,
        val MyLanguage: Map<String, String>,

)

     fun readLocalLanguege(context: Context): List<MyCodes> {

        val inputStream = context.assets.open(TARGETS)
        val jsonString = InputStreamReader(inputStream).use { it.readText() }
        val gson = Gson()
        val lst  = gson.fromJson(jsonString, Array<MyCodes>::class.java).toList()

         for(e in lst){
             println("datos: ${e.language}")
         }
       return lst
    }

    fun  readLocalSettings(context: Context ): MySettings {
        val inputStream = context.assets.open(SETTING)
        val jsonString = InputStreamReader(inputStream).use { it.readText() }
        val gson = Gson()
        val lst =  gson.fromJson(jsonString, MySettings::class.java)

        for(e in lst.Settings){
            println(e)
        }
        return lst
    }



}