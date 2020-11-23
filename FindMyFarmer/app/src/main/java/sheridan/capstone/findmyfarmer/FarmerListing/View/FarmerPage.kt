package sheridan.capstone.findmyfarmer.FarmerListing.View

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_farmer_page.*
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Customer
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Entities.Farmer
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.FarmerListing.Model.FarmerListData
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.R

class FarmerPage : AppCompatActivity(),FarmerListToView.OnItemClickListener{


    private var farmlist = ArrayList<Farm>()
    private val Bundle : Bundle = Bundle()
    private lateinit var adap: FarmerListToView
    private lateinit var progressbar :ProgressBar
    private lateinit var swipeResfreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_page)

        val toolbarView = findViewById<Toolbar>(R.id.toolbar1)
        swipeResfreshLayout = findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)
        progressbar = findViewById<ProgressBar>(R.id.progressBar);


        //uploading List to the RecycleView
        adap = FarmerListToView(farmlist,this)
        recycleView.adapter = adap
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)
        setSupportActionBar(toolbarView)

        //first run to get all the farmers from the database
        swipeResfreshLayout.isRefreshing = true
        GetAllFarmsFromDatabase()


        //checks if the swipe refresh is called and gets all the farmers from the database again
        swipeResfreshLayout.setOnRefreshListener {
            swipeResfreshLayout.setProgressBackgroundColorSchemeColor(Color.Red.hashCode())
            //accessing all farmers data from database
            GetAllFarmsFromDatabase()
        }

    }

    private fun GetAllFarmsFromDatabase(){
        farmlist.clear()
        DatabaseAPIHandler(this, AsyncResponse {
            var AllFarms = ObjectConverter.convertStringToObject(it, Farm::class.java,true) as List<Object>
            for (farm in AllFarms){
                var farmlistdata = farm as Farm
                farmlist.add(farmlistdata)
                //notifying change on list
                adap.notifyDataSetChanged()
                swipeResfreshLayout.isRefreshing = false
            }
        }).execute("/Farms")
    }

    override fun onItemClick(position: Int) {

        var loggedIn = Intent(this, FarmerInfo::class.java)
        //val Image = null
        val Farm_Name = farmlist[position].businessName
        val Farm_Desc = farmlist[position].businessDescription
        val Farm_Rat = farmlist[position].businessRating.toFloat()
        val Farm_City = farmlist[position].city

        //Bundle.putInt("Image",null)
        Bundle.putInt("FarmID",farmlist[position].farmID)
        Bundle.putString("Name",Farm_Name)
        Bundle.putString("Des",Farm_Desc)
        Bundle.putFloat("Rating",Farm_Rat)
        Bundle.putString("City",Farm_City)

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
            R.id.imagehandler ->{
                var i = Intent(this, sheridan.capstone.findmyfarmer.test::class.java)
                startActivity(i)
                true
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


