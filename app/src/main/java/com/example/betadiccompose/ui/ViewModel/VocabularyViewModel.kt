package com.example.betadiccompose.ui.ViewModel

import android.app.Activity
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.media.PlaybackParams
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.betadiccompose.Domain.*
import com.example.betadiccompose.Domain.Ads.AdMobInterstital
import com.example.betadiccompose.Domain.Ads.AdMobRewarded
import com.example.betadiccompose.Domain.Game_Provider.GameProvider
import com.example.betadiccompose.Domain.Prefs
import com.example.betadiccompose.R
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteGramar
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteSentes
import com.example.betadiccompose.data.local_database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network_database.model.*
import com.example.betadiccompose.Domain.FirebaseRepository
import com.example.betadiccompose.ui.Navigation.MainScreenState
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale
import javax.inject.Inject



@HiltViewModel
class VocabularyViewModel @Inject constructor (
    @ApplicationContext contexto: Context,
    val rewarded: AdMobRewarded,
    val interstital: AdMobInterstital,
    val game_provider :GameProvider,
    val prefs: Prefs,
    val favorite : Favorite,
    val level : Level,
    val user : User,
    val vocabulary: Vocabulary,
    val books: Books,
    val auth: FirebaseRepository,
    val files :LocalFiles
): ViewModel() {



    /*********** ads ************/
    //var mInterstitialAd: InterstitialAd? = null

    /************  login **************/

    val  context = contexto.applicationContext
    val currentUser = auth.currentUser
    val userId = auth.getUserId()
    val username = auth.getUserName()
    val useremail = auth.getUserEmail()
    val userphoto = auth.getUserPhoto()

    val stars :MutableState<Int> = mutableStateOf(1)

    val hasUser: Boolean
        get() = auth.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(userName: String) {
        loginUiState = loginUiState.copy(userName = userName)
    }

    fun onPasswordNameChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    fun onUserNameChangeSignup(userName: String) {
        loginUiState = loginUiState.copy(userEmailSignUp = userName)
    }

    fun onPasswordChangeSignup(password: String) {
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }

    fun onConfirmPasswordChange(password: String) {
        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.password.isNotBlank()

    private fun validateSignupForm() =
        loginUiState.userEmailSignUp.isNotBlank() &&
                loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmPasswordSignUp.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignupForm()) {
                throw IllegalArgumentException("email and password can not be empty")
            }

            if(loginUiState.passwordSignUp.length < 8){
                throw IllegalArgumentException("password need to have more than 8 words")
            }
            loginUiState = loginUiState.copy(isLoading = true)

            if (loginUiState.passwordSignUp != loginUiState.confirmPasswordSignUp
            ) {
                throw IllegalArgumentException(
                    "Password do not match"
                )
            }

            //loginUiState = loginUiState.copy(signUpError = null)

            auth.createUser(
                loginUiState.userEmailSignUp,
                loginUiState.passwordSignUp
            ) { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "success Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    /*
                    Toast.makeText(
                        context,
                        "Failed Login",
                        Toast.LENGTH_SHORT
                    ).show()*/
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }

            }


        } catch (e: Exception) {
            // loginUiState = loginUiState.copy(signUpError = e.localizedMessage+" mistake")
            e.printStackTrace()
            println("Este es el error ${e.message}")

            var error = ""

            if("The email address is already in use by another account." == e.message){

                loginUiState = loginUiState.copy(signUpErrorEmail = e.localizedMessage)
                loginUiState = loginUiState.copy(signUpErrorPassword = null)
                loginUiState = loginUiState.copy(signUpErrorConfirPassword = null)
            }
            if("email and password can not be empty" == e.message){
                loginUiState = loginUiState.copy(signUpErrorEmail = e.localizedMessage)
                loginUiState = loginUiState.copy(signUpErrorPassword = e.localizedMessage)
                loginUiState = loginUiState.copy(signUpErrorConfirPassword = e.localizedMessage)
            }

            if("password need to have more than 8 words" == e.message){

                loginUiState = loginUiState.copy(signUpErrorEmail = null)
                loginUiState = loginUiState.copy(signUpErrorPassword = e.localizedMessage)
                loginUiState = loginUiState.copy(signUpErrorConfirPassword = e.localizedMessage)
            }

            if("The email address is badly formatted." == e.message){

                loginUiState = loginUiState.copy(signUpErrorEmail = e.localizedMessage)
                loginUiState = loginUiState.copy(signUpErrorPassword = null)
                loginUiState = loginUiState.copy(signUpErrorConfirPassword = null)
            }

            if("Password do not match" == e.message){
                loginUiState = loginUiState.copy(signUpErrorEmail = null)
                loginUiState = loginUiState.copy(signUpErrorPassword = e.localizedMessage)
                loginUiState = loginUiState.copy(signUpErrorConfirPassword = e.localizedMessage)
            }

            Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()


        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }


    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateLoginForm()) {
                throw IllegalArgumentException("email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)

            auth.loginUser(
                loginUiState.userName.trim(),
                loginUiState.password.trim()
            ) { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "Hi, Welcome",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        "Your email or password is wrong",
                        Toast.LENGTH_LONG
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }

            }


        } catch (e: Exception) {
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }


    }

    fun SingOut() {
        deleteUserData()
        auth.SingOut()
    }

        fun SingInGoogleFirebase(
            credential: AuthCredential,
            OnNavToHome:()->Unit) = viewModelScope.launch{

            try {
                loginUiState = loginUiState.copy(isLoading = true)
                auth.SingGoogle(
                    CreateLocalUser = { user ->
                        CoroutineScope(Dispatchers.IO).launch {
                            UpdateUserData(user)

                            /*
                            println("el lenguaje es ${prefs.GetLearnLanguage()}")
                            if(prefs.GetLearnLanguage() == "English"){
                                setlevelLocal(user.English)

                            }else if(prefs.GetLearnLanguage() == "Spanish"){
                                setlevelLocal(user.Spanish)
                            }
                            */
                        }
                    },
                    credential){
                    if(it){
                        Toast.makeText(
                            context,
                            "success Login",
                            Toast.LENGTH_SHORT
                        ).show()

                        loginUiState = loginUiState.copy(isLoading = false)

                        OnNavToHome.invoke()

                    }else{
                        loginUiState = loginUiState.copy(isLoading = false)
                        Toast.makeText(
                            context,
                            "Failed Login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }catch (E:Exception){
                Toast.makeText(context, "${E.message}", Toast.LENGTH_SHORT).show()
            }
        }

        /*
        fun GetDataUser() {

            viewModelScope.launch {
                try {
                    auth.GetDataUserFromFirebase().addOnSuccessListener {
                        println("las stars son ${it.get("stars")}")
                        //    stars.value = it.get("stars")
                    }
                }catch (e:Exception){
                    println(e.message)
                }
            }



        }
    */
        fun sendPasswordResetEmail(context: Context){
            viewModelScope.launch {
                try {
                    auth.sendPasswordResetEmail { isSuccessful->

                        if(isSuccessful){
                            Toast.makeText(
                                context,
                                "UnSuccessfull",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            Toast.makeText(
                                context,
                                "UnSuccessfull",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }catch (e:Exception){

                }
            }
        }

        /************* login ***************/


        /** Nivel **/
        var state by mutableStateOf(MainScreenState())
        private set

        var step by mutableStateOf(0)

        /** Listas **/
        val lstBooks: MutableState<List<DataBooks>> = mutableStateOf(listOf())
        val lstVocabulary: MutableState<List<DataVocabulary>> = mutableStateOf(listOf())
        val lstWords: MutableState<List<DataWorld>> = mutableStateOf(listOf())
        //val lstWords: MutableState<List<DataWorld>> = mutableStateOf(listOf())
        val lstGramar: MutableState<List<DataGramar>> = mutableStateOf(listOf())
        val lstsubmenu: MutableState<List<DataSubMenu>> = mutableStateOf(listOf())
        val lstsentes: MutableState<List<DataSentes>> = mutableStateOf(listOf())

        /** Nivel **/
        val lstniveles: MutableState<List<DataNiveles>> = mutableStateOf(listOf()) //Lista de niveles
        val lstnivel: MutableState<List<DataWorld>> = mutableStateOf(listOf()) //Toda las palabras de una categoria
        val lstPlaying: MutableState<List<DataWorld>> = mutableStateOf(listOf())
        private val currentID: MutableState<Int> = mutableStateOf(0)

        /** Progress **/
        val loadVocabulary: MutableState<Boolean> = mutableStateOf(true)
        val loadBooks: MutableState<Boolean> = mutableStateOf(true)
        val loadWords: MutableState<Boolean> = mutableStateOf(true)
        val loadSubMenu: MutableState<Boolean> = mutableStateOf(true)
        val loadSentes: MutableState<Boolean> = mutableStateOf(true)
        val loadGramar: MutableState<Boolean> = mutableStateOf(true)
        val isloding_5: MutableState<Boolean> = mutableStateOf(true)
        val isloding_level: MutableState<Boolean> = mutableStateOf(true)

        //Books

        //  private val cm:ConnectivityManager = contexto.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        var context_view: Context = contexto

        /** My favorite words */
        var lstfavoritewords: MutableState<List<DataMyFavoriteWord>> = mutableStateOf(listOf())

        var mywords :MutableState<List<DataMyFavoriteWord>> = mutableStateOf(listOf())

        /** My favorite words */
        var lstfavoritesentes: MutableState<List<DataMyFavoriteSentes>> = mutableStateOf(listOf())

        /** My favorite Gramar */
        var lstfavoritegramar: MutableState<List<DataMyFavoriteGramar>> = mutableStateOf(listOf())


        /** data user **/
        var lstdatauser: MutableState<DataUser> = mutableStateOf(DataUser("´0","","",0,0,0,0))


        /************************  Categories  ******************************/

        fun getListOfAllCategories() {

            viewModelScope.launch {

                try{
                    val resp = vocabulary.GetListOfAllCategories()
                    loadVocabulary.value = true

                    if (!resp.isNullOrEmpty()) {
                        lstVocabulary.value = resp
                        loadVocabulary.value = false
                    }

                }catch (e:Exception){

                    //it happens when it don't have intenet
                    if(e.message == "Unable to resolve host \"duq14sjq9c7gs.cloudfront.net\": No address associated with hostname"){

                        val resp_2 =  vocabulary.GetListOfAllVocabularyFromRoom()

                        if(!resp_2.isNullOrEmpty()){
                            loadVocabulary.value = false
                            lstVocabulary.value = resp_2
                        }
                    }
                }



            }
        }


        fun getListOfSubMenu(){
            viewModelScope.launch {
                try {
                    val result = vocabulary.GetListOfSubMenu()
                    loadSubMenu.value = true
                    //loadWords.value = true
                    if(!result.isNullOrEmpty()){
                        lstsubmenu.value = result
                        loadSubMenu.value = false
                        // loadWords.value = false
                    }            }catch (e:Exception){

                }


            }
        }



        /************************  Words  ******************************/

        fun getListOfWordsFromRoom() {
            viewModelScope.launch {
                try {
                    val resp = vocabulary.GetListOfWordsFromRoom()
                    loadWords.value = true
                    if (!resp.isNullOrEmpty()) {
                        lstWords.value = resp
                        loadWords.value = false
                    }
                }catch (e:Exception){

                }

            }
        }

        fun getListOfSentesFromRoom() {
            viewModelScope.launch {
                try {
                    val resp = vocabulary.GetListOfWordsFromRoom()
                    loadSentes.value = true
                    if (!resp.isNullOrEmpty()) {
                        lstWords.value = resp
                        loadSentes.value = false
                    }
                }catch (e:Exception){

                }

            }
        }

        fun getListOfGramar() {
            viewModelScope.launch {
                try {
                    val result = vocabulary.GetListOfGramar()
                    loadGramar.value = true
                    if (!result.isNullOrEmpty()) {
                        loadGramar.value = false
                        lstGramar.value = result
                    }
                }catch (e:Exception){

                }
            }

        }

        fun getListOfWordsToPlayForLevel(){

            viewModelScope.launch {
                try{
                    val result = level.GetListOfWordsToPlayForLevel()
                    isloding_level.value = true

                    if(!result.isNullOrEmpty()) {
                        lstnivel.value = result
                        isloding_level.value = false
                    }

                    lstnivel.value.forEach {
                        println(it.World_1)
                    }


                }catch (E:Exception){
                    println("Este es el error: ${E.message}")
                }

            }

        }

        /************************  Gramar  ******************************/


        /************************  Sentes  ******************************/

        /************************  Game  ******************************/

        fun getEasy(lst:List<DataWorld>):List<DataWorld> {
            return game_provider.MakeEasyAndHard(lst)
        }

        fun getWrongWritten(lst:List<DataWorld>):List<DataWorld> {
            return game_provider.MakeWrongWritten(lst)
        }

        fun getSoundChoose(lst:List<DataWorld>): List<DataWorld> {
            return game_provider.SoundChoose(lst)
        }

        fun getOneWord(lst: List<DataWorld>): DataWorld {
            return game_provider.GetOneWord(lst)
        }

        /***************************************************************/


        /***********************************************************/


        fun getListOfAlllevel(){
            viewModelScope.launch {
                try{
                    val resp = level.GetListOfAlllevel()
                    isloding_5.value = true

                    if(!resp.isNullOrEmpty()){
                        lstniveles.value = resp
                        isloding_5.value = false
                    }

                }catch (e:Exception){

                    if(e.message == "Unable to resolve host \"duq14sjq9c7gs.cloudfront.net\": No address associated with hostname"){
                        val resp_2 =  level.GetListOfAlllevelFromRoom()

                        if(!resp_2.isNullOrEmpty()){
                            isloding_5.value = false
                            lstniveles.value = resp_2
                        }
                    }

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

        /***************  my favorite word *******************/


        fun insertMyFavoriteWord(fav :DataMyFavoriteWord){

            viewModelScope.launch {
                favorite.InsertMyFavoriteWord(fav)
            }

        }

        fun insertarmypalabra(palabra:DataMyFavoriteWord){
            viewModelScope.launch {
                favorite.insertarmyPalabra(palabra)
            }
        }

        fun deleteMyFavoriteWord(url:String){
            viewModelScope.launch {
                favorite.DeleteMyWordByImg(url)
            }
        }

        /***************  my favorite sentes *******************/
        fun getMyFavoriteSentes(): Unit {
            viewModelScope.launch {
                val resp = favorite.GetAllMyFavoriteSentes()
                if(!resp.isNullOrEmpty()){
                    lstfavoritesentes.value = resp
                }
            }
        }

        fun getMyFavoriteWords(): Unit {
            viewModelScope.launch {
                val resp  = favorite.GetListOfAllMyFavoriteWord()
                if(!resp.isNullOrEmpty()){
                    mywords.value = resp
                    //   lstfavoritewords.value = resp
                }
            }

        }

        fun insertMyFavoriteSentes(fav :DataMyFavoriteSentes){
            viewModelScope.launch {
                favorite.InsertMyFavoriteSentes(fav)
            }
        }

        fun deleteMyFavoriteSentes(sentes_1:String){
            viewModelScope.launch {
                favorite.DeleteMySentesBySentes(sentes_1)
            }
        }

        /***************  my favorite Gramar *******************/
        fun getMyFavoriteGramar(): Unit {
            viewModelScope.launch {
                val resp = favorite.GetAllMyFavoriteGramar()
                if(!resp.isNullOrEmpty()){
                    lstfavoritegramar.value = resp
                }
            }
        }

        fun insertMyFavoriteGramar(fav :DataMyFavoriteGramar){
            viewModelScope.launch {
                favorite.InsertMyFavoriteGramar(fav)

            }
        }

        fun deleteMyFavoriteGramar(gramar_1:String){
            viewModelScope.launch {
                favorite.DeleteMyGramarByGramar(gramar_1)
            }
        }

        /************************  Sound  ******************************/

        fun SoundFromLocal(sound:Int){
            var mediaPlayer = MediaPlayer.create(context,sound)
            mediaPlayer.setOnPreparedListener(OnPreparedListener { mp ->
                //works only from api 23
                var myPlayBackParams: PlaybackParams? = null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    myPlayBackParams = PlaybackParams()
                    myPlayBackParams!!.speed = 1f //you can set speed here
                    mp.playbackParams = myPlayBackParams!!
                }})


            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
            }


            mediaPlayer.start()



        }

        fun soundSlowerFromLink(url:String){
            CoroutineScope(Dispatchers.IO).launch {

                try {

                    val mediaPlayer = MediaPlayer().apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )

                        setDataSource(url)
                        prepare()


                    }

                    mediaPlayer.setOnPreparedListener(OnPreparedListener { mp ->
                        //works only from api 23
                        var myPlayBackParams: PlaybackParams? = null
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            myPlayBackParams = PlaybackParams()
                            myPlayBackParams!!.speed = 0.4f //you can set speed here
                            mp.playbackParams = myPlayBackParams!!
                        }})

                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer.release()
                    }


                    mediaPlayer.start()


                }catch (e:Exception){

                }
            }
        }

        fun SoundFromLink(url:String){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val mediaPlayer = MediaPlayer().apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )
                        setDataSource(url)
                        prepare()
                    }
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer.release()
                    }

                    mediaPlayer.start()
                }catch (e:Exception){
                    println("El error es: ${e.message}")
                }
            }
        }

        fun soundSlowerFromUrl(id: Int = 1){
            CoroutineScope(Dispatchers.IO).launch {

                var url = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${GetLearnLenguage()}/${GetPath()}/${id}.mp3"
                try {

                    try {
                        val mediaPlayer = MediaPlayer().apply {
                            setAudioAttributes(
                                AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                            )

                            setDataSource(url)
                            prepare()


                        }

                        mediaPlayer.setOnPreparedListener(OnPreparedListener { mp ->
                            //works only from api 23
                            var myPlayBackParams: PlaybackParams? = null
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                myPlayBackParams = PlaybackParams()
                                myPlayBackParams!!.speed = 0.4f //you can set speed here
                                mp.playbackParams = myPlayBackParams!!
                            }})


                        mediaPlayer.setOnCompletionListener {
                            mediaPlayer.release()
                        }

                        mediaPlayer.start()



                    }catch (e:Exception){
                        println("El error es: ${e.message}")
                    }
                }catch (e:Exception){

                }
            }
        }

        //word and sentes
        fun soundFromUrl(id: Int = 1){
            CoroutineScope(Dispatchers.IO).launch {
                println("el id es ${id}")
                var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${GetLearnLenguage()}/${GetPath()}/${id}.mp3"


                try {

                    val mediaPlayer = MediaPlayer().apply {

                        setAudioAttributes(

                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()

                        )

                        setDataSource(urlAudio)
                        prepare()
                    }


                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer.release()
                    }
                    mediaPlayer.start()

                }catch (e:Exception){
                    println("El error es: ${e.message}")
                }
            }

        }

        fun media(id: Int = 1,media:MediaPlayer){
            CoroutineScope(Dispatchers.IO).launch {
                println("el id es ${id}")
                var urlAudio = "https://duq14sjq9c7gs.cloudfront.net/Sounds/${GetLearnLenguage()}/${GetPath()}/${id}.mp3"


                try {

                    media.setDataSource(urlAudio)
                    media.prepare()

                    media.setOnCompletionListener {
                        media.reset()
                        //media.setDataSource(urlAudio)
                        //media.prepare()
                    }
                    media.start()

                }catch (e:Exception){
                    println("El error es: ${e.message}")
                }
            }

        }

        /**************  User data  *********************/
        fun deleteUserData(){
            viewModelScope.launch {
                user.DeleteUser()
            }
        }


        fun getDataUser(){
            viewModelScope.launch {
                val result = user.GetDetaillsUser()
                lstdatauser.value = result
            }
        }



        fun insertDataUser(dataUser: DataUser){
            viewModelScope.launch {
                user.InsertUser(dataUser)
            }
        }
        suspend fun UpdateUserData(data:DataUser){
            user.UpdateUserData(data)
        }

        suspend fun updateExp(){
            user.UpdateExpUser(1)
        }
        suspend fun lessLives(){
            user.lessLives()
        }

        suspend fun updatelevelLocal(){

            var english = 0
            var spanish = 0
            var totalCrowns:Int

            getDataUser()

            // Data to Firestore
            var level = lstdatauser.value.level
            var position = prefs.GetIndexCurrentLevel()
            var crowns = lstniveles.value[position-1].stars

            for(i in 0 until lstniveles.value.size){
                println("index: ${lstniveles.value[i].id-1}:  id: ${lstniveles.value[i].id} name: ${lstniveles.value[i].name}  crowns ${lstniveles.value[i].stars}" )
            }


            // Get crowns
            user.UpdateCrownsUser(crowns)
            totalCrowns = lstdatauser.value.crowns + crowns

            if(level == GetIndexLevelCurrent()){
                level++

                user.UpdateLevelUser(level)

                if(prefs.GetLearnLanguage() == "English"){
                    user.UpdateLevelEnglish(level)
                    english = level
                    spanish = lstdatauser.value.Spanish
                    println("Se supero un nivel nuevo English")

                }else if(prefs.GetLearnLanguage() == "Spanish"){

                    user.UpdateLevelSpanish(level)
                    spanish = level
                    english = lstdatauser.value.English
                    println("Se supero un nivel nuevo Spanish")
                }


            }else{
                println("no se paso ningun nuevo nivel")
            }

            getDataUser()
            var user = lstdatauser.value
            auth.SetDataUserFireStore(lstdatauser.value.exp,english,spanish,totalCrowns)

        }

        suspend fun setlevelLocal(level:Int){
            user.SetLevelUser(level)
        }


        suspend fun plusLives(){
            user.plusLives()
        }

        suspend fun getLives() = user.getlives()


        suspend fun counAllUser()=user.countUser()


        /***************  preferes  *******************/

        fun saveLearnLenguage(language:String)=prefs.SaveLearnLanguage(language)
        fun SaveLocalLanguage(language:String) = prefs.SaveLocalLanguage(language)

        fun SavePreferences(it: DataVocabulary) {
            prefs.SaveIndex(it)
        }

        fun SavePath(path: String) {
            prefs.SavePath(path)
        }

        fun SetIndexLevelCurrent(index:Int){
            prefs.SetIndexCurrentLevel(index)
        }

        fun GetIndexLevelCurrent() = prefs.GetIndexCurrentLevel()

        fun SaveAllSubNameCategory(doc: String) {
            prefs.SaveCats(doc)
        }

        fun SaveSubNumberCategory(id: Int) {
            prefs.SaveSubNumberCategory(id)
        }

        fun SaveWordCategoryName(name:String){
            prefs.SaveWordNameCategory(name)
        }

        fun GetPath():String = prefs.GetPath()

        fun GetLearnLenguage(): String = prefs.GetLearnLanguage()

        fun GetLocalLenguage(): String = prefs.GetLocalLanguage()

        fun GetCategoryName():String = prefs.GetNameCategory()

        fun GetWordCategoryName():String = prefs.GetWordNameCategory()

        fun GetKindDocument() = prefs.GetDocument()

        fun IsSub() = prefs.IsSubMenu()

        /***** books ****/

        fun getListOfAllBooks() {
            viewModelScope.launch {
                val resp = books.GetListOfBooks()
                loadBooks.value = true
                if (!resp.isNullOrEmpty()) {
                    lstBooks.value = resp
                    loadBooks.value = false
                }
            }
        }

        /*********  Dates  *********/
        fun SetFirstTime(time:String){
            prefs.SaveFirstTime(time)
        }

        fun GetFisrtTime() = prefs.GetFirstTime()


        fun CalculeteDates():Long{

            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            var diffInMillis = 0L

            val calendar1 = Calendar.getInstance()
            calendar1.time = Date()

            val calendar2 = Calendar.getInstance().apply {
                timeInMillis = format.parse(GetFisrtTime()).time
            }

            diffInMillis =  calendar1.timeInMillis - calendar2.timeInMillis
            // val diffInMinutes = diffInMillis / 60000

            return diffInMillis
        }

        /** Ads  */

        fun SetCounterGame(num:Int){
            prefs.setCountInterstitialAdGame(num)
        }

        fun GetCounterGame() = prefs.getCountInterstitialAdGame()


        fun GetCounterVoca() = prefs.getCountInterstitialAdVoca()



        fun SetCounterVoca(num:Int){
            prefs.setCountInterstitialAdVoca(num)
        }

        //ads
        fun LoadInterstital() {
            interstital.LoadAd()
            println("holahola ${prefs.getCountInterstitialAdGame()}")
        }

        fun ShowInterstitalLevel(context: Context){
            //
            var cont = 0
            cont = GetCounterGame()

            cont++
            prefs.setCountInterstitialAdGame(cont)

            if(prefs.getCountInterstitialAdGame() >= 2){
                interstital.showAd(context as Activity, onClick = {
                    prefs.setCountInterstitialAdGame(0)
                })
            }else{
                SoundFromLocal(R.raw.uwin)
            }
        }

        fun ShowInterstitalVoca(context: Context){
            var cont = 0
            cont = GetCounterVoca()

            if(prefs.getCountInterstitialAdVoca() >= 14 ){
                interstital.showAd(context as Activity, onClick = {
                    prefs.setCountInterstitialAdVoca(0)
                })
            }else{
                cont++
                prefs.setCountInterstitialAdVoca(cont)
            }

            println("contador de palabras : ${prefs.getCountInterstitialAdVoca()}" )
        }

        fun LoadRewarded(){

            // getDataUser()
            rewarded.LoadAd()
        }

        fun ShowRewarded(activity: Activity, onClick: () -> Unit){

            rewarded.showAd(activity, OnClick = {
                viewModelScope.launch {
                    prefs.setCountInterstitialAdGame(0)
                    plusLives()
                    onClick()
                }

            })
        }


        /** Cambio de idioma **/

        suspend fun getCurrentLanguage(language:String){

            getDataUser()
            var English = lstdatauser.value.English
            var Spanish = lstdatauser.value.Spanish


            if(language == "English"){
                setlevelLocal(English)

            }else if(language == "Spanish"){
                setlevelLocal(Spanish)
            }

        }


        fun ChangeLanguage(language:String) {

            viewModelScope.launch {
                getCurrentLanguage(language)
                saveLearnLenguage(language)
                getListOfAlllevel()
                getListOfAllCategories()
                getListOfSentesFromRoom()
                getListOfWordsFromRoom()
            }


        }

    fun SetTheme(theme :Int){
        prefs.setTheme(theme)
    }

    fun GetTheme() = prefs.getTheme()

    fun GetFilesLocalLanguages() = files.getLocalLanguages(context)



    fun Review(){
        val manager = ReviewManagerFactory.create(context)

        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = task.result
            } else {
                // There was some problem, log or handle the error code.
                //@ReviewErrorCode val reviewErrorCode = (task.getException() as TaskException).errorCode
            }
        }
    }

}

