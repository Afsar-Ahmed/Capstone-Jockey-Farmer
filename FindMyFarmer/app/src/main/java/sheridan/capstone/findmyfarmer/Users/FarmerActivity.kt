package sheridan.capstone.findmyfarmer.Users

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
<<<<<<< HEAD
=======
import android.widget.TextView
>>>>>>> Sohaib
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.Farmer.View.FarmerHub
import sheridan.capstone.findmyfarmer.Customer.View.MarketPlace
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.Customer.View.Following
import sheridan.capstone.findmyfarmer.Customer.View.Maps
<<<<<<< HEAD
import sheridan.capstone.findmyfarmer.R
=======
import sheridan.capstone.findmyfarmer.Farmer.View.FarmAddFragment
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
>>>>>>> Sohaib

class FarmerActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var NavigationView: NavigationView
<<<<<<< HEAD
<<<<<<< HEAD
    var User_Customer_Only : Boolean = false
=======
    private lateinit var sessionData: SessionData
>>>>>>> Sohaib
=======
>>>>>>> parent of 7f933a8... Most Recent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_view)

<<<<<<< HEAD


=======
        sessionData = SessionData(this)
        var customer = sessionData.customerData
>>>>>>> Sohaib
        val toolbarView: Toolbar = findViewById(R.id.toolbarD)

        drawerLayout=findViewById(R.id.drawerLayout)
        NavigationView = findViewById(R.id.nav_view)

<<<<<<< HEAD
=======
        var menu = NavigationView.menu
        var wanttobefarmeritem = menu.findItem(R.id.WantToBeFarmer)
        wanttobefarmeritem.setEnabled(false)
        wanttobefarmeritem.setVisible(false)

        if(customer != null) {
            var usertext = NavigationView.getHeaderView(0).findViewById<TextView>(R.id.user)
            usertext.text = customer.customerName
        }


>>>>>>> Sohaib
        NavigationView.setNavigationItemSelectedListener(this)


        setSupportActionBar(toolbarView)

        val toggle= ActionBarDrawerToggle(this,drawerLayout,
            toolbarView,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )


        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        val bottomnav: BottomNavigationView = findViewById(R.id.bottom_nav_farmer)

<<<<<<< HEAD




        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            FarmerHub()
        ).commit()

=======
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FarmerHub()).commit()
>>>>>>> Sohaib

        bottomnav.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
<<<<<<< HEAD
                R.id.nav_manage_hub -> selectedFragment = FarmerHub()
                R.id.nav_manage_market-> selectedFragment = MarketPlace()
                R.id.nav__manage_following -> selectedFragment = Following()
                R.id.nav_farmer_map_view-> selectedFragment = Maps()
            }

            supportFragmentManager.beginTransaction()


                .replace(
                    R.id.fragment_container,
                    selectedFragment!!
                )

                .commit()
=======
                R.id.nav_manage_hub -> {
                    selectedFragment = FarmerHub()
                }
                R.id.nav_manage_market-> {
                    selectedFragment = MarketPlace()
                }
                R.id.nav__manage_following -> {
                    selectedFragment = Following()
                }
                R.id.nav_farmer_map_view-> {
                    selectedFragment = Maps()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment!!).commit()
>>>>>>> Sohaib
            true
        }
    }

<<<<<<< HEAD

=======
>>>>>>> Sohaib
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_logout ->{
                logOut()
<<<<<<< HEAD


                finish()

=======
                finish()
>>>>>>> Sohaib
            }
            R.id.nav_account -> {
                val intent = Intent(this, AccountSettings::class.java)
                // start your next activity
                startActivity(intent)
            }
        }

        return true
    }

<<<<<<< HEAD

    private fun logOut(){
        Firebase.auth.signOut()

        LoginManager.getInstance().logOut()
        AuthUI.getInstance().signOut(this).addOnCompleteListener(){
=======
    private fun logOut(){
        Firebase.auth.signOut()
        LoginManager.getInstance().logOut()
        AuthUI.getInstance().signOut(this).addOnCompleteListener(){
            sessionData.ClearAllData()
>>>>>>> Sohaib
            startActivity(
                Intent(this,
                    LoginRegistrationController::class.java)
            )
        }
        Toast.makeText(applicationContext, "Logged out", Toast.LENGTH_LONG).show()
    }
}