package sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registration.*
import sheridan.capstone.findmyfarmer.FarmerListing.View.FarmerPage
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Model.LoginModel
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Model.RegistrationModel
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.LoginFragment
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.RegistrationView
import sheridan.capstone.findmyfarmer.R

class LoginRegistrationController : AppCompatActivity(), LoginRegistrationInterface{

    val Controller: Controller = Controller()
    private lateinit var auth: FirebaseAuth
    private var RC_SIGN_IN  = 9001
    private lateinit var sic : GoogleSignInClient
    private lateinit var callBackManager: CallbackManager
    private lateinit var fbCallBack : FacebookCallback<LoginResult>
    private val model: LoginModel by viewModels()
    private val registerModel: RegistrationModel by viewModels()
    private var user: FirebaseUser? = null
    private var navController: NavController? = null

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
                startActivity(Intent(this, FarmerPage::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, "Incorrect email/password!",
                    Toast.LENGTH_SHORT).show()
            }
        }

        registerModel.user.observe(this,authObserver)
        //observe when user value has been changed in the LoginModel
        model.user.observe(this, authObserver)

       /* val btnAnimation = AnimationUtils.loadAnimation(this,
            R.anim.btn_click_animation
        )
        val btnUpAnimation = AnimationUtils.loadAnimation(this,
            R.anim.btn_click_up_animation
        )*/


            /*forgotPswrdLink.setOnClickListener{
                startActivity(Intent(this, RegistrationView::class.java))
            }*/

        /*
        //open register form when pressed register button
        registerAccount.setOnClickListener{
            Controller.register(this)
        }
        */



        /*
        //press FBSign button in to login with faceBook
        */
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
                    model.firebaseAuthWithGoogle(this,auth,acc.idToken!!, acc,bundle=null)
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
            var loggedIn = Intent(context, FarmerPage::class.java)
            loggedIn.putExtras(Bundle().apply(extras))
            ContextCompat.startActivity(context, loggedIn, null)
        }
    }

    private fun register(ApplicationContext: Context){
        var registration = Intent(ApplicationContext, RegistrationView::class.java)
        ContextCompat.startActivity(ApplicationContext, registration, null)
    }

    //Run validation and login function with input provided by the user
    override fun OnLoginButtonClickListener(email: EditText, password: EditText) {
            if(model.loginValidation(email, password)) {
                model.login( auth, this, email.text.toString(), password.text.toString())
        }
    }

    //When sign up button is clicked - parse the information to the input validation and then signUp
    override fun OnSignUpButtonClickListener(email: EditText, password: EditText, repeatPassword: EditText) {
        if(registerModel.registerValidation(email,password,repeatPassword))
            registerModel.register(auth,this,email.text.toString(),password.text.toString())
    }

    //Open registration fragment on link click
    override fun OnRegisterLinkClickListener() {
        registerAccount.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
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
                        model.firebaseAuthWithFacebook(this@LoginRegistrationController, auth,result.accessToken)
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

}

