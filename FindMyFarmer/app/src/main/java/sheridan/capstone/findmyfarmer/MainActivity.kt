package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.CustomerMain.View.CustomerView
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Customer
import sheridan.capstone.findmyfarmer.Entities.Farmer
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.DashBoardView

class MainActivity : AppCompatActivity() {

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
              CustomerView::class.java))

        } else {
            startActivity(Intent(this, LoginRegistrationController::class.java))
        }

        val c = DatabaseAPIHandler(this)
        var d = Customer(1,"Sobi5180","sobi@hotmail.ca","5180")
        var d1 = Customer(1,"Sobi","sobi5180@hotmail.ca","1234")
        var d2 = Customer(1,"Sobi","sobi5180@hotmail.ca","1234")

        var f = Farmer(1,"TestBus","Testsestes",10,14)
        var f1 = Farmer(1,"TestBus","Testsestes",10,16)
        var f2 = Farmer(1,"TestBus","Testsestes",10,12)

        var p = Product(1,"Rice","Grain")
        var p1 = Product(1,"Quinoa","Grain")
        var p2 = Product(1,"Carrot","Vegetable")

        var custlists = listOf<Customer>(d,d1,d2)
        var flist = listOf<Farmer>(f,f1,f2)
        var plist = listOf<Product>(p1,p2)

        //This how to call the API
        //c.execute("/addProducts",plist);
    }
}