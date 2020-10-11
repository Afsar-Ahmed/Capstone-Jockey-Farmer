package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.Session
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        
        var logintext = findViewById<TextView>(R.id.LoginText)

        logintext.text = Firebase.auth.currentUser?.email + " " + Firebase.auth.currentUser?.displayName

        logOutBtn.setOnClickListener{
            logOut()
        }
    }

    private fun logOut(){
        Firebase.auth.signOut()

        LoginManager.getInstance().logOut()
        AuthUI.getInstance().signOut(this).addOnCompleteListener(){
            startActivity(Intent(this,Login::class.java))
        }
        finish()

    }
}