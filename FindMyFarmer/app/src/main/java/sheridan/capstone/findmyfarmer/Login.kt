package sheridan.capstone.findmyfarmer


import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils

import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var validated: Boolean
        auth = Firebase.auth
        val btnAnimation = AnimationUtils.loadAnimation(this,R.anim.btn_click_animation)
        val btnUpAnimation = AnimationUtils.loadAnimation(this,R.anim.btn_click_up_animation)

        //login when the login button is pressed
        loginBtn.setOnClickListener{
            loginBtn.startAnimation(btnAnimation)
            loginBtn.startAnimation(btnUpAnimation)

            if(loginValidation(inputEmail,inputPassword))
                login()
        }

        //Remove keyboard and focus from the element when touch outside of the EditText
        constraintLayoutLoginPage.setOnTouchListener{v: View, m: MotionEvent ->
            closeKeyboard(constraintLayoutLoginPage)
            var focused = currentFocus
            focused?.clearFocus()
            true}

        forgotPswrdLink.setOnClickListener{
            startActivity(Intent(this, Registration::class.java))
        }
    }

    //validates the input in Email and Password fields according to the regex provided
    private fun loginValidation(emailInput: EditText, passwordInput: EditText) : Boolean{
        var regexPattern= Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches()){
            emailInput.setError("Ouch! Wrong email")
        }
        if(!passwordInput.text.matches(regexPattern)){
            passwordInput.setError("Yikes.. Wrong Password")

        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text).matches() &&
                passwordInput.text.matches(regexPattern)

    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    //logs in user with the right credentials
    private fun login(){
        auth.signInWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("AUTHENTICATION", "login :success")
                    val user = auth.currentUser
                    updateUI(user)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("AUTHENTICATION", "login :failure", task.exception)
                    Toast.makeText(baseContext, "Incorrect email/password!",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }
            }

    }

    //Opens next activity if the user signed in successfully
    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            startActivity(Intent(this,DashBoard::class.java))

        }

    }

    //close keyboard on touch of the particular view
    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}

