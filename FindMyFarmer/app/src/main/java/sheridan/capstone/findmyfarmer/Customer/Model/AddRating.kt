package sheridan.capstone.findmyfarmer.Customer.Model

/**
 * Author:  Sohaib Hussain
 **/

import android.app.Activity
import android.widget.ImageView
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Rating

class AddRating(private val activity: Activity,private val dialogue: RateItDialogue,val imageView: ImageView){

   fun addRating(rating: Rating){
        DatabaseAPIHandler(activity, AsyncResponse {
            imageView.visibility = ImageView.INVISIBLE;
            dialogue.dismiss()
        }).execute("/addRating",rating)
   }
}