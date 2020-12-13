package sheridan.capstone.findmyfarmer.Farmer.Model

import android.app.Activity
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Farmer.View.FarmerHub
import sheridan.capstone.findmyfarmer.R

/**
 * Author:  Sohaib Hussain
 **/
class FarmDBHandler(val activity: Activity, val progressbar: ProgressBar?) {

    fun updatefarm(farm: Farm){
        progressbar?.visibility = ProgressBar.VISIBLE
        DatabaseAPIHandler(activity, AsyncResponse {
            progressbar?.visibility = ProgressBar.INVISIBLE
        }).execute("/UpdateFarm",farm)
    }

    fun addfarm(farm: Farm){
        progressbar?.visibility = ProgressBar.VISIBLE
        DatabaseAPIHandler(activity, AsyncResponse {
            progressbar?.visibility = ProgressBar.INVISIBLE
        }).execute("/addFarm",farm)
    }

    fun deletefarm(farm: Farm){
        DatabaseAPIHandler(activity, AsyncResponse {
            if (!(it.isNullOrBlank())){
                Toast.makeText(activity,"Deleted!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(activity,"Deleted!",Toast.LENGTH_SHORT).show()
            }
        }).execute("/deleteFarmByID/${farm.farmID}")
    }
}
