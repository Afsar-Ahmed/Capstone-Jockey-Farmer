package sheridan.capstone.findmyfarmer.LoginAndRegistration.Model

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResetModel: ViewModel() {

    val registeredEmail : MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }
    public fun sendResetPasswordEmail(emailInput: EditText){
        Log.d("RESET", "Entered Function")
        Firebase.auth.sendPasswordResetEmail(emailInput.text.toString())
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    registeredEmail.value = true
                    Log.d("RESET", "Email sent.")
                }else{
                    registeredEmail.value = false
                    Log.d("RESET", "Not Sent")
                }
            }
    }
    public fun loginValidation(emailInput: EditText) : Boolean{
        var regexPattern= Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$")
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches()){
            emailInput.setError("Wrong email")
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches()
    }
}