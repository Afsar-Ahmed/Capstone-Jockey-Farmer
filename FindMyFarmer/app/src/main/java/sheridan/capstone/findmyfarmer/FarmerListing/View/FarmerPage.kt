package sheridan.capstone.findmyfarmer.FarmerListing.View

import android.content.Context
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farmer_page.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_farmer_info.*
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.R

class FarmerPage : AppCompatActivity(),FarmerListToView.OnItemClickListener{



    var FarmerController :FarmerGenerateList = FarmerGenerateList()



    val List = FarmerController.GenerateList(5)

    val Bundle : Bundle = Bundle()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_page)

        val toolbarView: Toolbar = findViewById(R.id.toolbar1)

        recycleView.adapter =
            FarmerListToView(
                List,this
            )

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)
        setSupportActionBar(toolbarView)


    }





    override fun onItemClick(position: Int) {



        var loggedIn = Intent(this, FarmerInfo::class.java)
        val Image = List[position].imageResouce
        val Farmer_Name = List[position].Farmer_Name
        val Farmer_Desc = List[position].Farmer_Desc
        val Farmer_Rat = List[position].Farmer_Rating
        val Farmer_City = List[position].Farmer_City

        Bundle.putInt("Image",Image)
        Bundle.putString("Name",Farmer_Name)
        Bundle.putString("Des",Farmer_Desc)
        Bundle.putFloat("Rating",Farmer_Rat)
        Bundle.putString("City",Farmer_City)

        loggedIn.putExtras(Bundle)

        ContextCompat.startActivity(this, loggedIn, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.examplemenu, menu)

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when ( item.itemId){
            R.id.Account -> {
                Toast.makeText(applicationContext, "click on Account", Toast.LENGTH_LONG).show()
                true
            }
            R.id.Logout -> {
                logOut()


                finish()
                return true
            }

            else -> return super.onOptionsItemSelected(item)

        }

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
