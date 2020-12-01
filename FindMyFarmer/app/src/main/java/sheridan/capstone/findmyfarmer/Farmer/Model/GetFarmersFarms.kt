package sheridan.capstone.findmyfarmer.Farmer.Model

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.storage.StorageReference
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Farmer.Controller.HubListToView
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import java.util.*
import kotlin.collections.ArrayList

class GetFarmersFarms(val context: Context, val swipeRefreshLayout: SwipeRefreshLayout, val adapter: HubListToView){
    public fun GetHubFarms(HubFarms: ArrayList<Farm>,id: Int){
        swipeRefreshLayout.isRefreshing = true
        DatabaseAPIHandler(context, AsyncResponse{
            HubFarms.clear()
            var AllFarms = ObjectConverter.convertStringToObject(it, Farm::class.java,true) as List<*>
            for (farm in AllFarms){
                var farmlistdata = farm as Farm
                var FIH = FirebaseImagehandler(DirectoryName.Farm,farmlistdata.farmID,context)
                FIH.GetPrimaryImageFromFirebaseURL(object: StorageResponse {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun processFinish(response: MutableList<StorageReference>?, bitmap: Optional<Bitmap>?, Url: Optional<String>?) {
                            if(Url != null && !(Url.get().isNullOrBlank())) {
                                farmlistdata.primaryImage = Url.get()
                                HubFarms.add(farmlistdata)
                                //notifying change on list
                                adapter.notifyDataSetChanged()
                                swipeRefreshLayout.isRefreshing = false
                            } else{
                                HubFarms.add(farmlistdata)
                                //notifying change on list
                                adapter.notifyDataSetChanged()
                                swipeRefreshLayout.isRefreshing = false
                            }
                    }
                    override fun OnErrorListener(error: String?) {
                        HubFarms.add(farmlistdata)
                        //notifying change on list
                        adapter.notifyDataSetChanged()
                        swipeRefreshLayout.isRefreshing = false
                    }
                })
            }
            swipeRefreshLayout.isRefreshing = false
        }).execute("/FarmsByFarmerID/$id")
    }
}