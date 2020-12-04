package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.Users.AnonymousUserActivity

class GetStartedActivity : AppCompatActivity() {

    private lateinit var Login : Button
    private  lateinit var GetStarted : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_started)
        auth = Firebase.auth
        Login = findViewById(R.id.login_btn)
        GetStarted = findViewById(R.id.Get_Started)

        Login.setOnClickListener{
            startActivity(Intent(this, LoginRegistrationController::class.java))
            finish()
        }
        GetStarted.setOnClickListener {
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Anonymous", "signInAnonymously:success")
                        val user = auth.currentUser
                        startActivity(Intent(this, AnonymousUserActivity::class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("Anonymous", "signInAnonymously:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

    private lateinit var auth: FirebaseAuth

    override fun onResume() {
        super.onResume()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
