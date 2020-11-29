package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Customer
import sheridan.capstone.findmyfarmer.Entities.Farmer
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
import sheridan.capstone.findmyfarmer.Users.CustomerActivity
import sheridan.capstone.findmyfarmer.Users.FarmerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sessionData: SessionData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionData = SessionData(this)
        checkIfSignedInAccount()
    }

    //Checks if the user in signed in the account
    //if not - go to the sign in page. if yes - go to a different activity
    private fun checkIfSignedInAccount() {

        val user = Firebase.auth.currentUser
        var customer = sessionData.customerData
        if (user != null && customer != null) {
            if(customer.isFarmer){
                startActivity(Intent(this,
                    FarmerActivity::class.java))
            }
            else{
                startActivity(Intent(this,
                    CustomerActivity::class.java))
            }

        }
        else {
            sessionData.ClearAllData()
            startActivity(Intent(this, LoginRegistrationController::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        checkIfSignedInAccount()
    }
}