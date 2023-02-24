package com.example.betadiccompose.data.repository

import android.net.Uri
import androidx.compose.runtime.MutableState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Authrepository @Inject constructor(){

    val currentUser: FirebaseUser?= Firebase.auth.currentUser
    private val db = FirebaseFirestore.getInstance()

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
                    CreateDataFirebaseStore()
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
        credential:AuthCredential,
        onComplete:(Boolean)->Unit)= withContext(Dispatchers.IO){

        FirebaseAuth
            .getInstance()
                .signInWithCredential(credential)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        CreateDataFirebaseStore()
                        onComplete.invoke(true)
                    }else{
                        onComplete.invoke(false)
                    }
                }.await()

        }

    fun CreateDataFirebaseStore(){

        var counter = 0

        val data = hashMapOf(
            "stars" to 0,
            "level" to  arrayListOf(0,0)
        )


        val docRef = db.collection("users").document( Firebase.auth.currentUser?.uid.orEmpty())


        db.collection("users")
            .get()
            .addOnSuccessListener { result ->

                for (document in result){
                    if(document.id != Firebase.auth.currentUser?.uid.orEmpty() ){
                        counter++
                    }
                }

                if(result.size() == counter){
                    docRef.set(data)
                }
            }


    }

    fun GetDataUserFromFirebase(): Task<DocumentSnapshot> {

       var valor = 0
       val docRef = db.collection("users").document( Firebase.auth.currentUser?.uid.orEmpty())
       return docRef.get()
       /*
       docRef.addSnapshotListener{snapshot,e->

           if(e!=null){
               return@addSnapshotListener
           }

           if(snapshot!=null && snapshot.exists()){
               println("Estellas:.ñ,lpo0  ${snapshot.get("stars")}")
              valor = snapshot.get("stars") as Int
           }else{
               println("No hay datos en la escucha")
           }

       }
*/
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

}

