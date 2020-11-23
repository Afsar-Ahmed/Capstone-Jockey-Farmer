package sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.fragment_modified_login.*
import sheridan.capstone.findmyfarmer.CustomerMain.View.CustomerView

import sheridan.capstone.findmyfarmer.LoginAndRegistration.Model.LoginModel
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Model.RegistrationModel
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Model.ResetModel
import sheridan.capstone.findmyfarmer.R

class LoginRegistrationController : AppCompatActivity(), LoginRegistrationInterface, ViewBehaviorInterface{

    private lateinit var auth: FirebaseAuth
    private var RC_SIGN_IN  = 9001
    private lateinit var sic : GoogleSignInClient
    private lateinit var callBackManager: CallbackManager
    private lateinit var fbCallBack : FacebookCallback<LoginResult>
    private val loginModel: LoginModel by viewModels()
    private val registerModel: RegistrationModel by viewModels()
    private val resetModel : ResetModel by viewModels()
    private var user: FirebaseUser? = null
    private var registeredUser: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        callBackManager = CallbackManager.Factory.create()

        //observer for the user value in the LoginModel Class
        //if changed - log in to the farmerListing
        val authObserver = Observer<FirebaseUser?>{
                newAuth -> user = newAuth
            if(user != null){
                startActivity(Intent(this, CustomerView::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, "Incorrect email/password!",
                    Toast.LENGTH_SHORT).show()
            }
        }

        //if the email in reset is registered and reset password was sent - hide keyboard, show Toast
        val resetObserver = Observer<Boolean> {
            newEmailStatus -> registeredUser = newEmailStatus
            var toastMessage: String = if(registeredUser){

                var layout : View = findViewById(R.id.resetPasswordConstraintLayout)
                hideKeyboard(layout)
                layout.requestFocus()
                "Check your E-mail. The reset password is sent"
            }else{
                "Something went wrong. This E-mail might not be registered"
            }
            Toast.makeText(applicationContext, toastMessage,
                Toast.LENGTH_LONG).show()
        }
        //observe when user value has been changed in the resetModel
        resetModel.registeredEmail.observe(this,resetObserver)
        //observe when user value has been changed in the registerModel
        registerModel.user.observe(this,authObserver)
        //observe when user value has been changed in the LoginModel
        loginModel.user.observe(this, authObserver)


    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(this,currentUser)
    }

    //starts the google login pop-up, allowing the user to choose the google account for log in
    private fun googleLogIn(){
        //initializing google services for login
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        sic = GoogleSignIn.getClient(this, gso)
        var signInGoogle = sic.signInIntent
        startActivityForResult(signInGoogle, RC_SIGN_IN)
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
                    loginModel.firebaseAuthWithGoogle(this,auth,acc.idToken!!, acc,bundle=null)
                }
                catch (e: Exception){
                    Toast.makeText(
                        applicationContext,
                        "Error Logging into google account",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.w("GOOGLE SIGN IN FAILED", e)
                }
            }
            else{
                Toast.makeText(applicationContext, "Sign In Not Successful", Toast.LENGTH_LONG).show()
            }
        }
        else{
            callBackManager.onActivityResult(requestCode, resultCode, data)
        }

    }


   //Opens next activity if the user signed in successfully
    private fun updateUI(context: Context, user: FirebaseUser?, extras: Bundle.() -> Unit = {}){
        if(user != null){
            var loggedIn = Intent(context, CustomerView::class.java)
            loggedIn.putExtras(Bundle().apply(extras))
            ContextCompat.startActivity(context, loggedIn, null)
        }
    }


    //Run validation and login function with input provided by the user
    override fun OnLoginButtonClickListener(email: EditText, password: EditText) {
            if(loginModel.loginValidation(email, password)) {
                loginModel.login( auth, this, email.text.toString(), password.text.toString())
        }
    }

    //When sign up button is clicked - parse the information to the input validation and then signUp
    override fun OnSignUpButtonClickListener(email: EditText, password: EditText, repeatPassword: EditText) {
        if(registerModel.registerValidation(email,password,repeatPassword))
            registerModel.register(auth,this,email.text.toString(),password.text.toString())
    }

    //Open registration fragment on link click
    override fun OnRegisterLinkClickListener() {
        var navController = Navigation.findNavController(this,R.id.fragment_host)
        navController.navigate(R.id.action_loginFragment_to_registrationFragment)
    }

    //When google Sign In button is pressed - call googleLogIn
    override fun OnGoogleButtonClickListener() {
        googleLogIn()
    }

    //When facebook Sign In button is pressed - call facebook log in
    override fun OnFBLogInButtonClickListener() {
        FBSignIn.setPermissions("email")
        FBSignIn.registerCallback(callBackManager,
            object : FacebookCallback<LoginResult> { override fun onSuccess(result: LoginResult?) {
                    if (result != null) {
                        loginModel.firebaseAuthWithFacebook(this@LoginRegistrationController, auth,result.accessToken)
                    } else {
                        Toast.makeText(applicationContext, "Error getting Facebook Account",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onCancel() {
                }
                override fun onError(error: FacebookException?) {
                    Log.e("FacebookERROR", error.toString())
                }
            })
    }

    //open reset password fragment
    override fun OnResetPasswordButtonClickListener() {
        var navController = Navigation.findNavController(this,R.id.fragment_host)
        navController.navigate(R.id.action_loginFragment_to_resetPasswordFragment)
    }

    override fun OnSendResetButtonClickListener(email: EditText) {
        if(resetModel.loginValidation(email)){
            resetModel.sendResetPasswordEmail(email)
        }
    }

    //hide the keyboard
    override fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //request focus on from all the input fields and hide a keyboard if touched outside of the current input field
    override fun viewBehavior(view: View) {
        view.requestFocus()
        view.setOnTouchListener{ view, m: MotionEvent ->
            hideKeyboard(view)
            view.requestFocus()
            true}
    }

}
