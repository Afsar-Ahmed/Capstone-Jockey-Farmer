package sheridan.capstone.findmyfarmer.LoginAndRegistration.Model

import android.app.Activity
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Customer
import sheridan.capstone.findmyfarmer.Entities.Farmer
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData

class RegistrationModel:ViewModel() {

    val user : MutableLiveData<FirebaseUser?> by lazy{
        MutableLiveData<FirebaseUser?>()
    }
    private lateinit var sessionData: SessionData
    public fun register(auth: FirebaseAuth, activity: Activity, email: String,name:String, password: String,IsFarmer:Boolean) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    //if task is successful new user is added to the Firebase
                    user.value = auth.currentUser
                    //Creating customer to add to database
                    //Adding customer instance to database
                    sessionData = SessionData(activity)
                    var customer = Customer(1,name,email,password,IsFarmer)
                    DatabaseAPIHandler(activity, AsyncResponse {
                        var CustomerAdded = ObjectConverter.convertStringToObject(it,Customer::class.java,false) as Customer
                        if(CustomerAdded != null){
                            if(customer.isFarmer){
                                var farmer = Farmer(1,CustomerAdded.customerID)
                                DatabaseAPIHandler(activity, AsyncResponse {it2 ->
                                    var FarmerAdded = ObjectConverter.convertStringToObject(it2,Farmer::class.java,false) as Farmer
                                    if(FarmerAdded != null){
                                        sessionData.setUserDataForSession(FarmerAdded,CustomerAdded)
                                    }
                                    else{
                                        Toast.makeText(activity.applicationContext,"Couldnt add Farmer for: ${customer.customerEmail}",Toast.LENGTH_SHORT).show()
                                    }
                                }).execute("/addFarmer",farmer)
                            }
                            else{
                                sessionData.setUserDataForSession(null,CustomerAdded)
                            }
                        }
                        else{
                            Toast.makeText(activity.applicationContext,"Couldnt add Customer ${customer.customerEmail}",Toast.LENGTH_SHORT).show()
                        }
                    }).execute("/addCustomer",customer)

                    Log.d("REGISTRATION", "registration :success $user")
                } else {
                    // If registration fails show log
                    user.value = null
                    Log.d("REGISTRATION", "registration :failure")
                }
            }
    }

    public fun registerNameValidation(name: EditText):Boolean{
        var regexPattern= Regex("\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+")
        var nameValidated = false
        if(name.text.matches(regexPattern)){
            nameValidated = true
        }else{
            name.setError("Please enter properly formatted name")
        }
        return nameValidated

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
            if(repeatPasswordInput.text.toString().equals(passwordInput.text.toString(),false)) {
                repeatPasswordInputVerification = true
            }else{
                repeatPasswordInput.setError("Password doesn't match")
                repeatPasswordInputVerification = false
            }

        }
        /*return android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches() &&
                passwordInput.text.matches(regexPattern)*/
        //Log.d("VERIFICATION","$emailInputVerification $passwordInputVerification $repeatPasswordInputVerification")
        return emailInputVerification && passwordInputVerification && repeatPasswordInputVerification
    }
}