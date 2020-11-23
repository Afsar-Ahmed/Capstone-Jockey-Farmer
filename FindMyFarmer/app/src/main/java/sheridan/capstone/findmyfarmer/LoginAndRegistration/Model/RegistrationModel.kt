package sheridan.capstone.findmyfarmer.LoginAndRegistration.Model

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.DashBoardView

class RegistrationModel:ViewModel() {

    val user : MutableLiveData<FirebaseUser?> by lazy{
        MutableLiveData<FirebaseUser?>()
    }
    public fun register(auth: FirebaseAuth, activity: Activity, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    //if task is successful new user is added to the Firebase
                    user.value = auth.currentUser
                    Log.d("REGISTRATION", "registration :success $user"
                    )
                } else {
                    // If registration fails show log
                    user.value = null
                    Log.d("REGISTRATION", "registration :failure")
                }
            }
    }
    public  fun registerValidation(emailInput: EditText, passwordInput: EditText, repeatPasswordInput: EditText) : Boolean{
        var regexPattern= Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$")
        var emailInputVerification: Boolean = false
        var passwordInputVerification: Boolean = false
        var repeatPasswordInputVerification: Boolean = false

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches()){
            emailInput.setError("Wrong email")
            emailInputVerification = false
        }else{
            emailInputVerification = true
        }
        if(!passwordInput.text.matches(regexPattern)){
            passwordInput.setError("Password must be 6 to 20 characters. Password must include letters and numbers")
            passwordInputVerification = false
        }else{
            passwordInputVerification = true
            if(!passwordInput.text.toString().equals(repeatPasswordInput.text.toString(),false)) {
                repeatPasswordInput.setError("Password doesn't match")
                repeatPasswordInputVerification = false
            }else{
                repeatPasswordInputVerification = true
            }

        }
        /*return android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches() &&
                passwordInput.text.matches(regexPattern)*/
        return emailInputVerification && passwordInputVerification && repeatPasswordInputVerification
    }
}