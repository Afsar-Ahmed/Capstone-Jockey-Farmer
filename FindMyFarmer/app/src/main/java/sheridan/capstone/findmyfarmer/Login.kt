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
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class Login : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth

    private var RC_SIGN_IN  = 9001
    private lateinit var sic : GoogleSignInClient

    private lateinit var callBackManager: CallbackManager
    private lateinit var fbCallBack : FacebookCallback<LoginResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var validated: Boolean
        auth = Firebase.auth

        callBackManager = CallbackManager.Factory.create()

        GSignIn.setOnClickListener { googleLogIn() }

        val btnAnimation = AnimationUtils.loadAnimation(this,R.anim.btn_click_animation)
        val btnUpAnimation = AnimationUtils.loadAnimation(this,R.anim.btn_click_up_animation)
        //Remove keyboard and focus from the element when touch outside of the EditText
        constraintLayoutLoginPage.setOnTouchListener{v: View, m: MotionEvent ->
            closeKeyboard(constraintLayoutLoginPage)
            var focused = currentFocus
            focused?.clearFocus()
            true}


        //login when the login button is pressed
        loginBtn.setOnClickListener{
            loginBtn.startAnimation(btnAnimation)
            loginBtn.startAnimation(btnUpAnimation)

            if(loginValidation(inputEmail,inputPassword))
                login()
        }

        FBSignIn.setPermissions("email")
        FBSignIn.registerCallback(callBackManager,
                object : FacebookCallback<LoginResult>{
                    override fun onSuccess(result: LoginResult?) {
                        if (result != null) {
                            firebaseAuthWithFacebook(result.accessToken)
                        }
                        else{
                            Toast.makeText(applicationContext,"Error getting Facebook Account",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }

                    override fun onError(error: FacebookException?) {
                        Log.e("FacebookERROR",error.toString())
                    }

                })
    }

    private fun firebaseAuthWithFacebook(token: AccessToken){
        val cred =  FacebookAuthProvider.getCredential(token.token)

        auth.signInWithCredential(cred).addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Successfully logged in with Facebook",Toast.LENGTH_SHORT).show()
                updateUI(auth.currentUser)
            }
            else{
                Toast.makeText(applicationContext,"Unable to log in with Facebook",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //starts the google login pop-up, allowing the user to choose the google account for log in
    private fun googleLogIn(){
        //initializing google services for login
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        sic = GoogleSignIn.getClient(this,gso)
        var signInGoogle = sic.signInIntent
        startActivityForResult(signInGoogle,RC_SIGN_IN)
    }

    //After the user chooses the account (Facebook or google), this handles the user data returned
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Tries to Log in into google first to check if the credentials are approved by google or not
        if(requestCode == RC_SIGN_IN){
            //if all is good then the account object is returned back
            var googleAcc = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (googleAcc != null) {
                try{
                    //breaking down the account object to retrieve the basic data from the account, like name, email, id etc
                    val acc = googleAcc.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(acc.idToken!!, acc)
                }
                catch (e : Exception){
                    Toast.makeText(applicationContext,"Error Logging into google account",Toast.LENGTH_SHORT).show()
                    Log.w("GOOGLE SIGN IN FAILED",e)
                }
            }
            else{
                Toast.makeText(applicationContext,"Sign In Not Successful",Toast.LENGTH_LONG).show()
            }
        }
        else{
            callBackManager.onActivityResult(requestCode,resultCode,data)
        }

    }

    //Registers the google Sign in as a authenticated user in Firebase, lasts only within a session
    private fun firebaseAuthWithGoogle(idToken: String, acc : GoogleSignInAccount){
        val cred = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(cred).addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Logged in successfully to firebase",Toast.LENGTH_SHORT).show()
                updateUI(auth.currentUser){
                    putString("FullName",acc.displayName.toString())
                }
            }
            else{
                Toast.makeText(applicationContext,"Was not able to log in!",Toast.LENGTH_SHORT).show()
            }
        }


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
    private fun updateUI(user: FirebaseUser?, extras: Bundle.() -> Unit = {}){
        if(user != null){
            var loggedIn = Intent(this,DashBoard::class.java)
            loggedIn.putExtras(Bundle().apply(extras))
            startActivity(loggedIn)
        }
    }

    //close keyboard on touch of the particular view
    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}

