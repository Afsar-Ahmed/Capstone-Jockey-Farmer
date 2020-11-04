package sheridan.capstone.findmyfarmer.LoginAndRegistration.Model

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.Login


class LoginModel:ViewModel() {

    val user :MutableLiveData<FirebaseUser?> by lazy{
        MutableLiveData<FirebaseUser?>()
    }
    public fun login(bundle: Bundle?, auth: FirebaseAuth, activity: Activity, email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success - show log, update user value
                     user.value = auth.currentUser
                    Log.d("AUTHENTICATION", "login :success $user"
                            )

                } else {
                    user.value = null
                    // If sign in fails show log
                            Log.d("AUTHENTICATION", "login :failure")
                }
            }
    }
}