package com.example.betadiccompose.Domain

import android.net.Uri
import com.example.betadiccompose.data.network_database.model.DataUser
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    prefs: Prefs
){


    private var preference = prefs
    private val db = FirebaseFirestore.getInstance()

    val currentUser: FirebaseUser?= Firebase.auth.currentUser
    fun hasUser():Boolean = Firebase.auth.currentUser != null
    fun SingOut() = Firebase.auth.signOut()
    fun getUserId():String = Firebase.auth.currentUser?.uid.orEmpty()
    fun getUserEmail():String = Firebase.auth.currentUser?.email.orEmpty()
    fun getUserName():String =Firebase.auth.currentUser?.displayName.orEmpty()
    fun getUserPhoto(): Uri? =Firebase.auth.currentUser?.photoUrl



    suspend fun createUser(
        email:String,
        password:String,
        onComplete:(Boolean)->Unit)= withContext(Dispatchers.IO){
        Firebase.auth
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    //currentUser?.updateProfile(UpdateProfile())
                  //  CreateDataFirebaseStore(CreateLocalUser, UpdateLocalUser)
                    onComplete.invoke(true)
                }else{
                    onComplete.invoke(false)
                }
            }.await()
    }

    private fun UpdateProfile(): UserProfileChangeRequest {

        val username = ""
        val photoURI = Uri.parse("")

        val result = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .setPhotoUri(photoURI)
            .build()

        return result
    }

    suspend fun loginUser(
        email:String,
        password:String,
        onComplete:(Boolean)->Unit)= withContext(Dispatchers.IO){

        Firebase.auth
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
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
                        CreateDataFirebaseStore(CreateLocalUser)
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

    fun CreateDataFirebaseStore(CreateLocalUser: (DataUser) -> Unit) {

        println("usuarios 0")

        val data = hashMapOf(
            "crowns" to 0,
            "english" to 1,
            "spanish" to 1,
            "exp" to 0
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

                    // Crear datos en firebase
                    docRef.set(data)

                    var user  = DataUser(
                        id = getUserId(),
                        email = getUserEmail(),
                        name = getUserName())

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

