package sheridan.capstone.findmyfarmer.FarmerListing.View

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
import sheridan.capstone.findmyfarmer.Entities.Address
import sheridan.capstone.findmyfarmer.Entities.Customer
import sheridan.capstone.findmyfarmer.Entities.Farmer
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.FarmerListing.Model.FarmerListData
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.R

class FarmerPage : AppCompatActivity(),FarmerListToView.OnItemClickListener{


    private var farmerlist = ArrayList<FarmerListData>()
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
        adap = FarmerListToView(farmerlist,this)
        recycleView.adapter = adap
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)
        setSupportActionBar(toolbarView)

        //first run to get all the farmers from the database
        swipeResfreshLayout.isRefreshing = true
        GetAllFarmersFromDatabase()


        //checks if the swipe refresh is called and gets all the farmers from the database again
        swipeResfreshLayout.setOnRefreshListener {
            //accessing all farmers data from database
            GetAllFarmersFromDatabase()
        }

    }

    private fun GetAllFarmersFromDatabase(){
        farmerlist.clear()
        val c = DatabaseAPIHandler(this, AsyncResponse {
            var Allfarmers = ObjectConverter.convertStringToObject(it, Farmer::class.java,true) as List<Object>
            for (farmer in Allfarmers){
                var retFarmer = farmer as Farmer
                val c2  = DatabaseAPIHandler(this, AsyncResponse {it2 ->
                    var FarmerCustomer = ObjectConverter.convertStringToObject(it2, Customer::class.java,false) as Customer
                    retFarmer.setCustomer(FarmerCustomer);
                    val c3  = DatabaseAPIHandler(this, AsyncResponse {it3 ->
                        var FarmerAddress = ObjectConverter.convertStringToObject(it3,Address::class.java,false) as Address
                        var farmerlistdata = FarmerListData(retFarmer,FarmerAddress)
                        farmerlist.add(farmerlistdata)
                        //notifying change on list
                        adap.notifyDataSetChanged()
                        swipeResfreshLayout.isRefreshing = false
                    }).execute("/AddressByCustomerID/${retFarmer.customerID}")

                }).execute("/CustomerByFarmerID/${retFarmer.farmerID}")
            }
        }).execute("/Farmers")
    }

    override fun onItemClick(position: Int) {

        var loggedIn = Intent(this, FarmerInfo::class.java)
        //val Image = null
        val Farmer_Name = farmerlist[position].farmer.customerName
        val Farmer_Desc = farmerlist[position].farmer.businessDescription
        val Farmer_Rat = farmerlist[position].farmer.businessRating.toFloat()
        val Farmer_City = farmerlist[position].address.city

        //Bundle.putInt("Image",null)
        Bundle.putInt("FarmerID",farmerlist[position].farmer.farmerID)
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


