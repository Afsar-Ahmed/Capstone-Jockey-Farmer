package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.Users.AnonymousUserActivity

class GetStartedActivity : AppCompatActivity() {

    private lateinit var Login : Button
    private  lateinit var GetStarted : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_started)

        Login = findViewById(R.id.login_btn)
        GetStarted = findViewById(R.id.Get_Started)

        Login.setOnClickListener{
            startActivity(Intent(this, LoginRegistrationController::class.java))

        }
        GetStarted.setOnClickListener {
            startActivity(Intent(this, AnonymousUserActivity::class.java))
        }

    }
}