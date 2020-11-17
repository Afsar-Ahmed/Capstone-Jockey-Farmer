package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Product
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
            startActivity(Intent(this,
                FarmerPage::class.java))

        } else {
            startActivity(Intent(this, LoginRegistrationController::class.java))
        }

        //example
        //This how to call the API
        val c = DatabaseAPIHandler(this, AsyncResponse {
            System.err.println(it)
        }).execute("/storage/GetImagesForCustomer/22")

    }

}