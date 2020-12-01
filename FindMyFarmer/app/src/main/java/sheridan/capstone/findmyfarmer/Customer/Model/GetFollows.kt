package sheridan.capstone.findmyfarmer.Customer.Model

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.storage.StorageReference
import sheridan.capstone.findmyfarmer.Customer.Controller.FollowingListToView
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmListToView
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
import java.util.*
import kotlin.collections.ArrayList

class GetFollows(val activity: Activity, val adapter: FollowingListToView){

    private lateinit var sessionData: SessionData

    public fun GetFollowedFarms(FollowedFarms: ArrayList<Farm>){
        sessionData = SessionData(activity)
        var customer = sessionData.customerData
        if(customer != null){
            DatabaseAPIHandler(activity, AsyncResponse {
                FollowedFarms.clear()
                var AllFollowedFarms = ObjectConverter.convertStringToObject(it, Farm::class.java,true) as List<*>
                for (farm in AllFollowedFarms){
                    var followedFarm = farm as Farm
                    var FIH = FirebaseImagehandler(DirectoryName.Farm,followedFarm.farmID,activity)
                    FIH.GetPrimaryImageFromFirebaseURL(object: StorageResponse {
                        @RequiresApi(Build.VERSION_CODES.N)
                        override fun processFinish(response: MutableList<StorageReference>?, bitmap: Optional<Bitmap>?, Url: Optional<String>?) {
                            if(Url != null && !(Url.get().isNullOrBlank())) {
                                followedFarm.primaryImage = Url.get()
                                FollowedFarms.add(followedFarm);
                                //notifying change on list
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                FollowedFarms.add(followedFarm);
                                //notifying change on list
                                adapter.notifyDataSetChanged()
                            }
                        }
                        override fun OnErrorListener(error: String?) {
                            FollowedFarms.add(followedFarm);
                            //notifying change on list
                            adapter.notifyDataSetChanged()
                        }
                    })
                }
            }).execute("/getFollowedFarmsByCustomerID/${customer.customerID}")
        }

    }
}