package sheridan.capstone.findmyfarmer.Customer.Model

/**
 * Author:  Sohaib Hussain
 **/

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Following
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData

class AddFollow(private val activity: Activity, private val dialogue: FollowingDialog,private val imageView: ImageView) {

    private lateinit var sessionData: SessionData
    private lateinit var viewModel: SharedViewModel
    fun addfollow(following: Following){
        DatabaseAPIHandler(activity, AsyncResponse {
            Toast.makeText(activity,"Following!", Toast.LENGTH_SHORT).show()
            imageView.setImageDrawable(activity.getDrawable(android.R.drawable.btn_star_big_on))
            dialogue.dismiss()
        }).execute("/addFollow",following)
    }
    
    
}