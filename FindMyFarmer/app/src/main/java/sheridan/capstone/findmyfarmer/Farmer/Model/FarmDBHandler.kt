package sheridan.capstone.findmyfarmer.Farmer.Model

import android.app.Activity
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Farmer.View.FarmerHub
import sheridan.capstone.findmyfarmer.R

class FarmDBHandler(val activity: Activity, val progressbar: ProgressBar, val fragmentManager: FragmentManager) {

    fun updatefarm(farm: Farm){
        progressbar.visibility = ProgressBar.VISIBLE
        DatabaseAPIHandler(activity, AsyncResponse {
            progressbar.visibility = ProgressBar.INVISIBLE
            val fragmentTransaction : FragmentTransaction? = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, FarmerHub(),)?.commit()
        }).execute("/UpdateFarm",farm)
    }

    fun addfarm(farm: Farm){
        progressbar.visibility = ProgressBar.VISIBLE
        DatabaseAPIHandler(activity, AsyncResponse {
            progressbar.visibility = ProgressBar.INVISIBLE
            val fragmentTransaction : FragmentTransaction? = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, FarmerHub(),)?.commit()
        }).execute("/addFarm",farm)
    }
}