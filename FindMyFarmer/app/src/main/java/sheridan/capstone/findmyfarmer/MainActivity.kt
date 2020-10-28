package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Model.Login
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.DashBoardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //startActivity(Intent(this, Login::class.java))
        //Log.d("Switched Activity", "Switched to the Login")
        checkIfSignedInAccount()
    }

    //Checks if the user in signed in the account
    //if not - go to the sign in page. if yes - go to a different activity
    private fun checkIfSignedInAccount() {

        val user = Firebase.auth.currentUser
        if (user != null) {
            startActivity(Intent(this,
                DashBoardView::class.java))

        } else {
            startActivity(Intent(this, Login::class.java))

        }
    }
}