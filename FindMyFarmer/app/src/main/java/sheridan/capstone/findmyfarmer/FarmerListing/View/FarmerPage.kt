package sheridan.capstone.findmyfarmer.FarmerListing.View

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farmer_page.*
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.farmer_listing.*
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.R

class FarmerPage : AppCompatActivity(),FarmerListToView.OnItemClickListener{



    var FarmerController :FarmerGenerateList = FarmerGenerateList()



    val List = FarmerController.GenerateList(5)



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

        val ClickedItem = List[position].Farmer_Name
        Toast.makeText(this, "To Farmer $ClickedItem's Page",
            Toast.LENGTH_SHORT).show()
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


