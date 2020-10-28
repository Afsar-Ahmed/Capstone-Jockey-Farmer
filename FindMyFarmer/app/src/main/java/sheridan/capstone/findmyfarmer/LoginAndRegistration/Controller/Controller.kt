package sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseUser
import sheridan.capstone.findmyfarmer.FarmerListing.View.FarmerPage
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.RegistrationView

class Controller {

     fun updateUI(ApplicationContext:Context, user: FirebaseUser?,extras: Bundle.() -> Unit = {}){
        if(user != null){
            var loggedIn = Intent(ApplicationContext, FarmerPage::class.java)
            loggedIn.putExtras(Bundle().apply(extras))
            startActivity(ApplicationContext,loggedIn,null)
        }
    }

   fun register(ApplicationContext: Context){
        var registration = Intent(ApplicationContext, RegistrationView::class.java)
        startActivity(ApplicationContext,registration,null)
    }

}