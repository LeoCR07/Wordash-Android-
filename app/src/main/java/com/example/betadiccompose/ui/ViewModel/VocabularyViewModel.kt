package com.example.betadiccompose.ui.ViewModel

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.betadiccompose.Domain.*
import com.example.betadiccompose.Domain.Provider.GameProvider
import com.example.betadiccompose.Domain.Provider.Prefs
import com.example.betadiccompose.Domain.UserData.*
import com.example.betadiccompose.Domain.model.DataGramar
import com.example.betadiccompose.Domain.model.DataNiveles
import com.example.betadiccompose.Domain.model.DataSentes
import com.example.betadiccompose.Domain.model.DataSubMenu
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network.model.DataUser
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Navigation.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyViewModel @Inject constructor (
    val useCases : GetCategorys,
    val gramar: GetGramar,
    val words :GetWords,
    val sentes :GetSentes,
    val submenu: GetSubMenu,
    val niveles : GetNiveles,
    val woo :GetFrom,
    val gameProvider :GameProvider,
    val level:GetCategoriaNivel,
    val clean :CleanWord,
    val prefs: Prefs,
    val getfavoritewords :GetMyFavoriteWord,
    val insertMyFavoriteWord: InsertMyFavoriteWord,
    val deletemyFavoriteWord: DeleteMyFavoriteWord,

    val insertuserdata :InsertDataUser,
    val deletedatauser: DeleteUserData,
    val getDataUser: GetDataUser,
    val updateExpUser: UpdateExpUser,
    val updateLevelUser: UpdateLevelUser,
    val updateStarsUser: UpdateStarsUser

): ViewModel() {


    /** Nivel **/
    var Step =  mutableStateOf(0)

    var state by mutableStateOf(MainScreenState())
        private set

    var step by mutableStateOf(0)

    /** Listas **/
    val rquote : MutableState<List<DataVocabulary>> = mutableStateOf(listOf())
    val lstwords : MutableState<List<DataWorld>> = mutableStateOf(listOf())
    val lstgramar : MutableState<List<DataGramar>> = mutableStateOf(listOf())
    val lstsubmenu : MutableState<List<DataSubMenu>> = mutableStateOf(listOf())
    val lstsentes  : MutableState<List<DataSentes>> = mutableStateOf(listOf())

    /** Nivel **/
    val lstniveles  : MutableState<List<DataNiveles>> = mutableStateOf(listOf()) //Lista de niveles
    val lstnivel  : MutableState<List<DataWorld>> = mutableStateOf(listOf()) //Toda las palabras de una categoria
    val lstPlaying  : MutableState<List<DataWorld>> = mutableStateOf(listOf())
    private val currentID  : MutableState<Int> = mutableStateOf(0)

   /** Progress **/
    val isloding : MutableState<Boolean> = mutableStateOf(true)
    val isloding_2 : MutableState<Boolean> = mutableStateOf(true)
    val isloding_3 : MutableState<Boolean> = mutableStateOf(true)
    val isloding_4 : MutableState<Boolean> = mutableStateOf(true)
    val isloding_5 : MutableState<Boolean> = mutableStateOf(true)
    val isloding_level : MutableState<Boolean> = mutableStateOf(true)


    /** My favorite words */
    var lstfavoritewords  : MutableState<List<DataMyFavoriteWord>> = mutableStateOf(listOf())

    /** data user **/
    var lstdatauser :MutableState<DataUser> = mutableStateOf(DataUser())


    /** Metodos **/
    fun getList(){
        viewModelScope.launch {
            val resp = useCases()
            isloding.value = true

            if(!resp.isNullOrEmpty()){

                rquote.value = resp
                isloding.value = false

            }
        }
    }

    fun getWorlds(){
        viewModelScope.launch {
            //val result = words()
            val result = woo()
            isloding_2.value = true

             if(!result.isNullOrEmpty()){
                 lstwords.value = result
                 isloding_2.value = false
                // for (e in lstwords.value ) println("Este es el autor vm: ${e.World_1}")
             }
        }
    }

    fun getGramar(){
        viewModelScope.launch {
            val result = gramar()
            isloding_4.value = true

            if(!result.isNullOrEmpty()){
                isloding_4.value = false
                lstgramar.value = result
            }

        }
    }


    //obter toda la categoria por nivel
    fun getlevel(){
        viewModelScope.launch {
            val result = level()
            isloding_level.value = true
            println("Obteniendo nivel")

            if(!result.isNullOrEmpty()) {
                lstnivel.value = result
               isloding_level.value = false
            }
        }
    }

    //filtrar la categoria
    //Juegos nivel by categorias
    fun GetEasy(lst:List<DataWorld>):List<DataWorld> {
       return gameProvider.MakeEasyAndHard(lst)
    }

    fun GetWrongWritten(lst:List<DataWorld>):List<DataWorld> {
        return gameProvider.MakeWrongWritten(lst)
    }

    fun GetSoundChoose(lst:List<DataWorld>): List<DataWorld> {
        return gameProvider.SoundChoose(lst)
    }

    fun GetOneWord(lst: List<DataWorld>): DataWorld {
        return gameProvider.GetOneWord(lst)
    }

    /***************************************************************/

    fun getCurrentIdGame(): Int {
        currentID.value = gameProvider.currentID
        return currentID.value
        //return "${prefs.GetCategory()}/${currentID.value}"
    }

    /***********************************************************/

    fun getSentes(){
        viewModelScope.launch {
            val result = sentes()
            isloding_4.value = true
            if(!result.isNullOrEmpty()){
                lstsentes.value = result
                isloding_4.value = false
                // for (e in lstwords.value ) println("Este es el autor vm: ${e.World_1}")
            }
        }
    }

    fun getSubMenu(){
        viewModelScope.launch {
            val result = submenu()
            isloding_3.value = true
            if(!result.isNullOrEmpty()){
                lstsubmenu.value = result
                isloding_3.value = false
            }
        }
    }

    fun getNiveles(){
        viewModelScope.launch {
            val resp = niveles()
            isloding_5.value = true

            if(!resp.isNullOrEmpty()){
                //lst = resp
                lstniveles.value = resp
                isloding_5.value = false
                //for (e in rquote.value ) println("Este es el autor vm: ${e.author}")
            }
        }
    }

    fun changeTextValue(text:String){
        viewModelScope.launch {
            state = state.copy(
                text = text
            )
        }
    }

    fun cleanAllWords(){
        viewModelScope.launch {
            //clean()
            isloding_level.value = true
            //lstnivel.value = emptyList()
        }

    }

    fun Sonido(id: Int, category:String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                Media(category, id)
                // Exo(category, id, context)
            }catch (e:Exception){
                println("El error es: ${e.message}")
            }
        }

    }

    private fun Media(category: String, id: Int) {

        val url = "https://d1i3grysbjja6f.cloudfront.net/Sonido/$category/1/$id.mp3"

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
    /*************************************/
    fun getSound(beat:Int){
        gameProvider.KeySound(beat)
    }

    /***************  my favorite word *******************/
    fun GetMyFavoriteWords(): Unit {
        viewModelScope.launch {
            val resp  = getfavoritewords()

            if(!resp.isNullOrEmpty()){
                lstfavoritewords.value = resp
            }
        }
    }

    fun InsertMyFavoriteWord(favorite :DataMyFavoriteWord){
        viewModelScope.launch {
            insertMyFavoriteWord(favorite)
        }
    }

    fun DeleteMyFavoriteWord(url:String){
        viewModelScope.launch {
            deletemyFavoriteWord(url)
        }
    }

    /**************  User data  *********************/
    fun DeleteUserData(){
        viewModelScope.launch {
            deletedatauser()
        }
    }

    fun GetDataUser(){
        viewModelScope.launch {
            val result = getDataUser()
            lstdatauser.value = result
        }
    }

    fun InsertDataUser(dataUser: DataUser){
        viewModelScope.launch {
            insertuserdata(dataUser)
        }
    }

    fun UpdateStarsUser(stars:Int){
        viewModelScope.launch {
            updateStarsUser(stars)
        }
    }

    fun UpdateLevelUser(level:Int){
        viewModelScope.launch {
            updateLevelUser(level)
        }
    }

    fun UpdateExpUser(exp:Int){
        viewModelScope.launch {
            updateExpUser(exp)
        }
    }

    fun SaveLearnLenguage(language:String){
        prefs.SaveLearnLanguage(language)
    }

    fun GetLearnLenguage(): String = prefs.GetLearnLanguage()


    fun SaveLocalLanguage(language:String){
        prefs.SaveLocalLanguage(language)
    }

    fun GetLocalLenguage(): String = prefs.GetLocalLanguage()

    fun GetCategoryName():String = prefs.GetNameCategory()
}
