package sheridan.capstone.findmyfarmer.FarmerListing.Presenter

import android.view.Menu
import android.view.MenuItem
import sheridan.capstone.findmyfarmer.FarmerListing.Model.FarmerListing

interface Contract {


    interface View {
        fun onCreateOptionsMenu(menu: Menu?): Boolean
        fun onOptionsItemSelected(item: MenuItem): Boolean


    }

    interface Action {
        fun GenerateList(size: Int): List<FarmerListing>


    }
}