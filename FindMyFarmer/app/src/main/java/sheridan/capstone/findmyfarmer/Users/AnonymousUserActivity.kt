package sheridan.capstone.findmyfarmer.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import sheridan.capstone.findmyfarmer.Customer.View.Following
import sheridan.capstone.findmyfarmer.Customer.View.Maps
import sheridan.capstone.findmyfarmer.Customer.View.MarketPlace
import sheridan.capstone.findmyfarmer.Farmer.View.FarmerHub
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.AfterLoginFarmerRegistration
import sheridan.capstone.findmyfarmer.R

class AnonymousUserActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var NavigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonymous_user)

        val toolbarView: Toolbar = findViewById(R.id.toolbarD)

        drawerLayout = findViewById(R.id.anon_drawer)
        NavigationView = findViewById(R.id.nav_anon_view)

        NavigationView.setNavigationItemSelectedListener(this)

        val bottomnav: BottomNavigationView = findViewById(R.id.Anon_Nav)

        setSupportActionBar(toolbarView)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            toolbarView,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MarketPlace()).commit()
        bottomnav.setOnNavigationItemSelectedListener { item ->

            var selectedFragment: Fragment? = null
            when (item.itemId) {

                R.id.nav_market-> {
                    selectedFragment = MarketPlace()
                }

                R.id.nav_maps-> {
                    selectedFragment = Maps()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment!!).commit()
            true
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_manage ->{
                startActivity(Intent(this, LoginRegistrationController::class.java))
                this.finish()
            }
        }

        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}
