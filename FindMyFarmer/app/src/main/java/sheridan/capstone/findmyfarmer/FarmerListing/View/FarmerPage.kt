package sheridan.capstone.findmyfarmer.FarmerListing.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farmer_page.*
import androidx.appcompat.widget.Toolbar
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.R

class FarmerPage : AppCompatActivity(){



    var FarmerController :FarmerGenerateList = FarmerGenerateList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_page)

        val List = FarmerController.GenerateList(4)

        val toolbarView: Toolbar = findViewById(R.id.toolbar1)

        recycleView.adapter =
            FarmerListToView(
                List
            )
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)
        setSupportActionBar(toolbarView)

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


