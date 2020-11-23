package sheridan.capstone.findmyfarmer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.FarmerListing.View.FarmerPage
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //startActivity(Intent(this, LoginRegistrationController::class.java))
        //Log.d("Switched Activity", "Switched to the LoginRegistrationController")
        checkIfSignedInAccount()


    }

    //Checks if the user in signed in the account
    //if not - go to the sign in page. if yes - go to a different activity
    private fun checkIfSignedInAccount() {

        val user = Firebase.auth.currentUser
        if (user != null) {
            startActivity(
                Intent(
                    this,
                    FarmerPage::class.java
                )
            )

        } else {
            startActivity(Intent(this, LoginRegistrationController::class.java))
        }
    }

}