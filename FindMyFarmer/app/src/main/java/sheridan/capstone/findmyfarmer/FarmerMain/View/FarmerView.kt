package sheridan.capstone.findmyfarmer.FarmerMain.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.SharedViews.MarketPlace
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SharedViews.Following
import sheridan.capstone.findmyfarmer.SharedViews.Maps

class FarmerView : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var NavigationView: NavigationView

<<<<<<< HEAD:FindMyFarmer/app/src/main/java/sheridan/capstone/findmyfarmer/Users/CustomerActivity.kt
<<<<<<< HEAD
   var User_Customer_Only : Boolean = true
<<<<<<< HEAD
=======
    private lateinit var sessionData: SessionData
>>>>>>> Sohaib
=======
>>>>>>> parent of 7f933a8... Most Recent
=======
>>>>>>> parent of 43f1637... up

=======
>>>>>>> parent of 8f3d46a... Most Recent Branch:FindMyFarmer/app/src/main/java/sheridan/capstone/findmyfarmer/FarmerMain/View/FarmerView.kt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_view)



        val toolbarView: Toolbar = findViewById(R.id.toolbarD)

        drawerLayout=findViewById(R.id.drawerLayout)
        NavigationView = findViewById(R.id.nav_view)

        NavigationView.setNavigationItemSelectedListener(this)


        setSupportActionBar(toolbarView)

        val toggle= ActionBarDrawerToggle(this,drawerLayout,
            toolbarView,R.string.navigation_drawer_open,R.string.navigation_drawer_close)


        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        val bottomnav : BottomNavigationView = findViewById(R.id.bottom_farmer_nav)

        bottomnav.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.nav_manage_hub -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_farmer_view,
                        FarmerHub()
                    ).commit()
                }
                R.id.nav_market -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_farmer_view,
                        MarketPlace()
                    ).commit()
                }
                R.id.nav_following -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_farmer_view,
                        Following()
                    ).commit()
                }
                R.id.nav_farmer_map_view -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_farmer_view,
                        Maps()
                    ).commit()
                }
            }

            false
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