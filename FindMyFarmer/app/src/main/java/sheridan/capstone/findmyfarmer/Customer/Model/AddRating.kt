package sheridan.capstone.findmyfarmer.Customer.Model

import android.app.Activity
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Rating

class AddRating(private val activity: Activity,private val dialogue: RateItDialogue){

   fun addRating(rating: Rating){
        DatabaseAPIHandler(activity, AsyncResponse {
            dialogue.dismiss()
        }).execute("/addRating",rating)
   }
}