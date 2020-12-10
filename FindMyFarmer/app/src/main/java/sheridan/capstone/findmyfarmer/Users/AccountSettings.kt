package sheridan.capstone.findmyfarmer.Users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.ViewBehaviorInterface
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.R.string.navigation_drawer_close
import sheridan.capstone.findmyfarmer.R.string.navigation_drawer_open
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData

class AccountSettings : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,ViewBehaviorInterface {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var NavigationView: NavigationView
    private lateinit var Save : Button
    private lateinit var Password : Button
    private lateinit var sessionData: SessionData
    private lateinit var constraintLayout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)

        sessionData = SessionData(this)

        val customer = sessionData.customerData
        val toolbarView: Toolbar = findViewById(R.id.toolbarD)

        drawerLayout=findViewById(R.id.drawerLayout)
        NavigationView = findViewById(R.id.nav_view)
        Save = findViewById(R.id.Save)
        constraintLayout = findViewById(R.id.AccountSettingsConstraint)
        //Password = findViewById(R.id.UpdatePassword)
        viewBehavior(constraintLayout)
        NavigationView.setNavigationItemSelectedListener(this)
        setSupportActionBar(toolbarView)

        if(customer.isFarmer){
            val menuNav : Menu = NavigationView.menu
            val farmer : MenuItem = menuNav.findItem(R.id.WantToBeFarmer)
            farmer.setEnabled(false)
            farmer.setVisible(false)

        }

        val toggle= ActionBarDrawerToggle(this,drawerLayout,
            toolbarView, navigation_drawer_open, navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()


        // Saves data into data base and passes it to the CustomerView activity which displays the marketplace fragment as default.


        Save.setOnClickListener {
            if(customer.isFarmer){
                val i = Intent(applicationContext, FarmerActivity::class.java)
                startActivity(i)
            }
            else {
                val i = Intent(applicationContext, CustomerActivity::class.java)
                startActivity(i)
                // The view will jump to the appropriate activity - CustomerActivity for only customers. FarmerActivity for Farmers.
            }
        }
        /*Password.setOnClickListener {
            // changes password in the database - Firebase
        }*/
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_logout ->{
                logOut()
                finish()
            }


            R.id.WantToBeFarmer ->{
                val intent = Intent(this, CustomerToFarmerActivity::class.java)
                // start your next activity
                startActivity(intent)
            }
        }

        return true
    }
    private fun logOut(){
        Firebase.auth.signOut()
        LoginManager.getInstance().logOut()
        AuthUI.getInstance().signOut(this).addOnCompleteListener(){
            sessionData.ClearAllData()
            startActivity(
                Intent(this,
                    LoginRegistrationController::class.java)
            )
        }
        Toast.makeText(applicationContext, "Logged out", Toast.LENGTH_LONG).show()
    }

    override fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun viewBehavior(parentView: View) {
        parentView.requestFocus()
        parentView.setOnTouchListener{ view, m: MotionEvent ->
            hideKeyboard(view)
            view.requestFocus()
            true
        }
    }
}