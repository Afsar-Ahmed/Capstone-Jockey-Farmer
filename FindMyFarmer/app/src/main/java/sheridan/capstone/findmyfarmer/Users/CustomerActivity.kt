package sheridan.capstone.findmyfarmer.Users

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_farmer_info.view.*

import sheridan.capstone.findmyfarmer.Customer.View.Following
import sheridan.capstone.findmyfarmer.Customer.View.Maps
import sheridan.capstone.findmyfarmer.Customer.View.MarketPlace
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData


class CustomerActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var NavigationView: NavigationView
    private lateinit var sessionData: SessionData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_view)

        sessionData = SessionData(this)
        var customer = sessionData.customerData
        val toolbarView: Toolbar = findViewById(R.id.toolbarD)

        drawerLayout = findViewById(R.id.drawerLayout)
        NavigationView = findViewById(R.id.nav_view)

        NavigationView.setNavigationItemSelectedListener(this)

        if(customer != null) {
            var usertext = NavigationView.getHeaderView(0).findViewById<TextView>(R.id.user)
            usertext.text = customer.customerName
        }


        setSupportActionBar(toolbarView)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            toolbarView,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )


        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()


        val bottomnav: BottomNavigationView = findViewById(R.id.Bottom_nav_customer)



        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            MarketPlace()
        ).commit()


        bottomnav.setOnNavigationItemSelectedListener { item ->

                var selectedFragment: Fragment? = null
                when (item.itemId) {
                    R.id.nav_market -> selectedFragment = MarketPlace()
                    R.id.nav_following -> selectedFragment = Following()
                    R.id.nav_maps-> selectedFragment = Maps()
                }

                supportFragmentManager.beginTransaction()


                    .replace(
                    R.id.fragment_container,
                    selectedFragment!!
                )

          .addToBackStack(null)
                .commit()


                true
            }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_logout ->{
                logOut()


                finish()

            }
            R.id.nav_account -> {
                val intent = Intent(this, AccountSettings::class.java)
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
}