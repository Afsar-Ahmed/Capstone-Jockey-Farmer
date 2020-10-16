package sheridan.capstone.findmyfarmer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_regristration.*

class Registration : AppCompatActivity() {
   private lateinit var  auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regristration)

        //initialized firebase authentication
        auth = Firebase.auth


        signUp.setOnClickListener{
            signUpNewUsers()

        }

    }
    //checks if fields are valid and proceeds to authentication
    private fun signUpNewUsers(){
        var email = Regex("( /w+[+.w-]*@)(([w-]+.)*w+[w-]*.)(([a-z]{2,4}|d+)/i)")
            //if email is empty or not
            if (newUserEmail.text.toString().isEmpty()){
                newUserEmail.error="Please Enter your email"
                newUserEmail.requestFocus()
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(newUserEmail.text).matches()){
                newUserEmail.error="Ouch! Wrong email"
                newUserEmail.requestFocus()
            }

            //if password or RePassword(which is dependant on password) is empty or not
            if(password.text.toString().isEmpty() || RePassword.text.toString().isEmpty()){
                password.error="Please enter your password"
                password.requestFocus()
            }
            if(password.text.length <= 6){
                password.error="Passwords must be 6 or more characters"
                password.requestFocus()
            }


        //authenticates new users
        auth.createUserWithEmailAndPassword(newUserEmail.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) startActivity(Intent(this,DashBoard::class.java)) else {
                    //else they are told to try authenticating again
                    Toast.makeText(baseContext, "You have Failed!!! Try Again!",
                        Toast.LENGTH_SHORT).show()
                }
                //if task is successful, new users enter to the Dashboard
            }
    }

    override fun onStart(){
        super.onStart()

        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    fun updateUI(currentUser:FirebaseUser?){

    }





}