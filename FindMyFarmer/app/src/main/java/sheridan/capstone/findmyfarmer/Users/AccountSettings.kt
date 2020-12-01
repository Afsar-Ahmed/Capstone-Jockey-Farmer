package sheridan.capstone.findmyfarmer.Users

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.R.string.navigation_drawer_close
import sheridan.capstone.findmyfarmer.R.string.navigation_drawer_open

class AccountSettings : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var NavigationView: NavigationView
    private lateinit var Save : Button
    private lateinit var Password : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)

        val toolbarView: Toolbar = findViewById(R.id.toolbarD)

        drawerLayout=findViewById(R.id.drawerLayout)
        NavigationView = findViewById(R.id.nav_view)
        Save = findViewById(R.id.Save)
        Password = findViewById(R.id.UpdatePassword)

        NavigationView.setNavigationItemSelectedListener(this)
        setSupportActionBar(toolbarView)

        val toggle= ActionBarDrawerToggle(this,drawerLayout,
            toolbarView, navigation_drawer_open, navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()


        // Saves data into data base and passes it to the CustomerView activity which displays the marketplace fragment as default.

        Save.setOnClickListener {
            val i = Intent(applicationContext, CustomerActivity::class.java)
            startActivity(i)
            // The view will jump to the appropriate activity - CustomerActivity for only customers. FarmerActivity for Farmers.

        }
        Password.setOnClickListener {
            // changes password in the database - Firebase
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_logout ->{
                logOut()


                finish()

            }




        }

        return true
    }
    private fun logOut(){
        Firebase.auth.signOut()

        LoginManager.getInstance().logOut()
        AuthUI.getInstance().signOut(this).addOnCompleteListener(){
            startActivity(
                Intent(this,
                    LoginRegistrationController::class.java)
            )
        }
        Toast.makeText(applicationContext, "Logged out", Toast.LENGTH_LONG).show()
    }
}