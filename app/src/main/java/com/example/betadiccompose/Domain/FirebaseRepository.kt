package com.example.betadiccompose.Domain

import android.net.Uri
import com.example.betadiccompose.data.network_database.model.DataUser
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    prefs: Prefs,
     users : User
){


    val user = users
    private var preference = prefs
    private val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?= Firebase.auth.currentUser
    fun hasUser():Boolean = Firebase.auth.currentUser != null
    fun SingOut() = Firebase.auth.signOut()
    fun getUserId():String = Firebase.auth.currentUser?.uid.orEmpty()
    fun getUserEmail():String = Firebase.auth.currentUser?.email.orEmpty()
    fun getUserName():String =Firebase.auth.currentUser?.displayName.orEmpty()
    fun getUserPhoto(): Uri? =Firebase.auth.currentUser?.photoUrl



    suspend fun createUser(
        CreateLocalUser:(DataUser)->Unit,
        email:String,
        password:String,
        username: String,
        onComplete:(Boolean)->Unit)= withContext(Dispatchers.IO){

        Firebase.auth
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){

                    CoroutineScope(Dispatchers.IO).launch {
                        val user = auth.currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build()
                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // El nombre de usuario se ha establecido correctamente
                                }
                            }
                        CreateDataFirebaseStore(CreateLocalUser)
                    }

                    onComplete.invoke(true)


                }else{
                    onComplete.invoke(false)
                }
            }.await()


    }

    private fun UpdateProfile(username:String): UserProfileChangeRequest {

       // val photoURI = Uri.parse("")
        //    .setPhotoUri(photoURI)

        val result = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .build()

        return result
    }

    suspend fun loginUser(
        CreateLocalUser:(DataUser)->Unit,
        email:String,
        password:String,
        onComplete:(Boolean)->Unit)= withContext(Dispatchers.IO){

        Firebase.auth
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    CoroutineScope(Dispatchers.IO).launch {
                        CreateDataFirebaseStore(CreateLocalUser)
                    }

                    onComplete.invoke(true)
                }else{
                    onComplete.invoke(false)
                }
            }.await()
    }

    suspend fun SingGoogle(
        CreateLocalUser:(DataUser)->Unit,
        credential:AuthCredential,
        onComplete:(Boolean)->Unit)= withContext(Dispatchers.IO){

        FirebaseAuth
            .getInstance()
                .signInWithCredential(credential)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        CoroutineScope(Dispatchers.IO).launch {
                            CreateDataFirebaseStore(CreateLocalUser)
                        }

                        onComplete.invoke(true)
                    }else{
                        onComplete.invoke(false)
                    }
                }.await()

        }

    suspend fun sendPasswordResetEmail(
        onComplete:(Boolean)->Unit)= withContext(Dispatchers.IO){

        val emailAddress = "leomurillo07@gmail.com"

            Firebase.auth
                .sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        onComplete.invoke(true)
                    }else{
                        onComplete.invoke(false)
                    }
                }.await()
    }

     suspend fun CreateDataFirebaseStore(CreateLocalUser: (DataUser) -> Unit, username:String = "use") {


       var lst = user.GetDetaillsUser()


        //lst.English
        //lst.Spanish

        println("usuarios 0 $username")

        val data = hashMapOf(
            "crowns" to lst.crowns,
            "english" to lst.English,
            "spanish" to lst.Spanish,
            "exp" to lst.exp
        )

        val docRef = db.collection("users").document(getUserId())

        docRef.get()
            .addOnSuccessListener { document ->
                //El usuario ya tiene datos en firestore
                if(document.exists()){
                    println("usuario si tiene datos en firestore")

                    //Cargar los datos de remoto a local

                    var english = (document.get("english") as Long).toInt()
                    var spanish = (document.get("spanish") as Long).toInt()


                    var user  = DataUser(
                        id = getUserId(),
                        email = getUserEmail(),
                        name = getUserName(),
                        exp = (document.get("exp") as Long).toInt(),
                        English = english,
                        Spanish = spanish,
                        crowns = (document.get("crowns") as Long).toInt(),
                        level = if(preference.GetLearnLanguage() == "English") english  else english
                        )

                    CreateLocalUser(user)


                }else{
                    println("usuario: no tiene datos en firestore")

                    if(username!="use"){
                        val profileUpdates = userProfileChangeRequest {
                            displayName = username
                        }

                        currentUser!!.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    println("User profile updated.")
                                }
                            }
                    }

                    // Crear datos en firebase
                    docRef.set(data)

                    var user  = DataUser(
                        id = getUserId(),
                        email = getUserEmail(),
                        name = getUserName(),
                        exp = lst.exp,
                        crowns = lst.crowns,
                        English = lst.English,
                        Spanish = lst.Spanish
                    )

                    //Crear datos locales
                    CreateLocalUser(user)
                }
            }
            .addOnFailureListener{
                println("LOGIN ERROR ${it}")
            }


    }

    fun SetDataUserFireStore(exp: Int, english: Int, spanish: Int, totalCrowns: Int) {


        val docRef = db.collection("users").document(getUserId())

        docRef.get()
            .addOnSuccessListener { document ->

                if(document.exists()){

                    val data = hashMapOf(
                        "crowns" to totalCrowns,
                        "english" to english,
                        "spanish" to spanish,
                        "exp" to exp
                    )

                    docRef.set(data)

                }
            }
            .addOnFailureListener{
                println("LOGIN ERROR ${it}")
            }
    }

}

