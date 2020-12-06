package sheridan.capstone.findmyfarmer.Customer.Model

import android.app.Activity
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.storage.StorageReference
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

class GetAllFarms(val activity: Activity, val swipeRefreshLayout: SwipeRefreshLayout,val adapter: FarmListToView) {

    private lateinit var sessionData: SessionData
    public fun GetFarms(Farms: ArrayList<Farm>){
        sessionData = SessionData(activity)
        var customer = sessionData.customerData
        swipeRefreshLayout.isRefreshing = true
        DatabaseAPIHandler(activity,AsyncResponse{
            Farms.clear()
            var AllFarms = ObjectConverter.convertStringToObject(it, Farm::class.java,true) as List<*>
            for (farm in AllFarms){
                var farmlistdata = farm as Farm
                var FIH = FirebaseImagehandler(DirectoryName.Farm,farmlistdata.farmID,activity)
                FIH.GetPrimaryImageFromFirebaseURL(object: StorageResponse{
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun processFinish(response: MutableList<StorageReference>?, bitmap: Optional<Bitmap>?, Url: Optional<String>?) {
                            if(Url != null && !(Url.get().isNullOrBlank())) {
                                farmlistdata.primaryImage = Url.get()
                                if(customer != null){
                                    DatabaseAPIHandler(activity, AsyncResponse {resp ->
                                        farmlistdata.isFollowed = !(resp.isNullOrBlank())
                                        DatabaseAPIHandler(activity, AsyncResponse {resp2->
                                            farmlistdata.alreadyRated = !(resp2.isNullOrBlank())
                                            DatabaseAPIHandler(activity, AsyncResponse {
                                                if(it != null) {
                                                    farmlistdata.followers = it.toInt()
                                                }
                                                Farms.add(farmlistdata);
                                                //notifying change on list
                                                adapter.notifyDataSetChanged()
                                                swipeRefreshLayout.isRefreshing = false
                                            }).execute("/getFarmFollowers/${farmlistdata.farmID}")
                                        }).execute("/getRatingByCustomerIDAndFarmID/${farmlistdata.farmID}/${customer.customerID}")
                                    }).execute("/getFollowByCustomerIDAndFarmID/${farmlistdata.farmID}/${customer.customerID}")
                                }
                                else{
                                    DatabaseAPIHandler(activity, AsyncResponse {
                                        if(it != null) {
                                            farmlistdata.followers = it.toInt()
                                        }
                                        Farms.add(farmlistdata);
                                        //notifying change on list
                                        adapter.notifyDataSetChanged()
                                        swipeRefreshLayout.isRefreshing = false
                                    }).execute("/getFarmFollowers/${farmlistdata.farmID}")
                                }
                            }
                            else{
                                if(customer != null){
                                    DatabaseAPIHandler(activity, AsyncResponse {resp ->
                                        farmlistdata.isFollowed = !(resp.isNullOrBlank())
                                        DatabaseAPIHandler(activity, AsyncResponse {resp2->
                                            farmlistdata.alreadyRated = !(resp2.isNullOrBlank())
                                            DatabaseAPIHandler(activity, AsyncResponse {
                                                if(it != null) {
                                                    farmlistdata.followers = it.toInt()
                                                }
                                                Farms.add(farmlistdata);
                                                //notifying change on list
                                                adapter.notifyDataSetChanged()
                                                swipeRefreshLayout.isRefreshing = false
                                            }).execute("/getFarmFollowers/${farmlistdata.farmID}")
                                        }).execute("/getRatingByCustomerIDAndFarmID/${farmlistdata.farmID}/${customer.customerID}")
                                    }).execute("/getFollowByCustomerIDAndFarmID/${farmlistdata.farmID}/${customer.customerID}")
                                }
                                else{
                                    DatabaseAPIHandler(activity, AsyncResponse {
                                        if(it != null) {
                                            farmlistdata.followers = it.toInt()
                                        }
                                        Farms.add(farmlistdata);
                                        //notifying change on list
                                        adapter.notifyDataSetChanged()
                                        swipeRefreshLayout.isRefreshing = false
                                    }).execute("/getFarmFollowers/${farmlistdata.farmID}")
                                }
                            }
                    }
                    override fun OnErrorListener(error: String?) {
                        if(customer != null){
                            DatabaseAPIHandler(activity, AsyncResponse {resp ->
                                farmlistdata.isFollowed = !(resp.isNullOrBlank())
                                DatabaseAPIHandler(activity, AsyncResponse {resp2->
                                    farmlistdata.alreadyRated = !(resp2.isNullOrBlank())
                                    DatabaseAPIHandler(activity, AsyncResponse {
                                        if(it != null) {
                                            farmlistdata.followers = it.toInt()
                                        }
                                        Farms.add(farmlistdata);
                                        //notifying change on list
                                        adapter.notifyDataSetChanged()
                                        swipeRefreshLayout.isRefreshing = false
                                    }).execute("/getFarmFollowers/${farmlistdata.farmID}")
                                }).execute("/getRatingByCustomerIDAndFarmID/${farmlistdata.farmID}/${customer.customerID}")
                            }).execute("/getFollowByCustomerIDAndFarmID/${farmlistdata.farmID}/${customer.customerID}")
                        }
                        else{
                            DatabaseAPIHandler(activity, AsyncResponse {
                                if(it != null) {
                                    farmlistdata.followers = it.toInt()
                                }
                                Farms.add(farmlistdata);
                                //notifying change on list
                                adapter.notifyDataSetChanged()
                                swipeRefreshLayout.isRefreshing = false
                            }).execute("/getFarmFollowers/${farmlistdata.farmID}")
                        }
                    }
                })
            }
        }).execute("/Farms")
    }
}
