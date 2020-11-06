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
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.farmer_listing.*
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.FarmerListing.Model.ListData
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
                Toast.makeText(applicationContext, "Logged out", Toast.LENGTH_LONG).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)

        }

    }

}


