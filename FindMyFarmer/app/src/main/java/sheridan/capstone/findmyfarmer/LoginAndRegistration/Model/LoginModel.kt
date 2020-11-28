package sheridan.capstone.findmyfarmer.LoginAndRegistration.Model

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.*


class LoginModel:ViewModel() {

    val user :MutableLiveData<FirebaseUser?> by lazy{
        MutableLiveData<FirebaseUser?>()
    }

    //logs in user with right credentials
    public fun login(auth: FirebaseAuth, activity: Activity, email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity) { task ->
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

    //Registers the google Sign in as an authenticated user in Firebase, lasts only within a session
    public fun firebaseAuthWithGoogle( activity: Activity, auth: FirebaseAuth, idToken: String, acc: GoogleSignInAccount, bundle: Bundle?) {
        val cred = GoogleAuthProvider.getCredential(idToken, null)
        signInWithCredential(activity,cred,auth,"google")
    }

    //Registers the facebook Sign in as an authenticated user in Firebase, lasts only within a session
    public fun firebaseAuthWithFacebook(activity: Activity, auth: FirebaseAuth, token: AccessToken){
        val cred =  FacebookAuthProvider.getCredential(token.token)
        signInWithCredential(activity,cred,auth,"facebook")
    }

    private fun signInWithCredential(activity: Activity, credential: AuthCredential, auth: FirebaseAuth, platform: String){
        auth.signInWithCredential(credential).addOnCompleteListener(activity){ task ->
            if(task.isSuccessful){
                // Sign in success - show log, update user value
                user.value = auth.currentUser
                Log.d("AUTHENTICATION", "login with $platform :success $user")
            }
            else{
                // If sign in fails show log
                user.value = null
                Log.d("AUTHENTICATION", "login with $platform :failure")
            }
        }
    }

    //validates the input in Email and Password fields according to the regex provided
    public  fun loginValidation(emailInput: EditText, passwordInput: EditText) : Boolean{
        var regexPattern= Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$")
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches()){
            emailInput.setError("Wrong email")
        }
        if(!passwordInput.text.matches(regexPattern)){
            passwordInput.setError("Password must be 6 to 20 characters." +
                    " Password must include letters and numbers")

        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches() &&
                passwordInput.text.matches(regexPattern)
    }
}
