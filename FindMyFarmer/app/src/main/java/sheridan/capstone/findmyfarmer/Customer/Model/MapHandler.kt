package sheridan.capstone.findmyfarmer.Customer.Model

import android.location.Geocoder
import android.os.AsyncTask
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import sheridan.capstone.findmyfarmer.Customer.View.MapResponse

class MapHandler(var Geocoder : Geocoder,var mapResponse : MapResponse) : AsyncTask<Any, Any, Any>() {


    var mapResponse_ = mapResponse

    override fun doInBackground(vararg params: Any?): Any {


        return Geocoder.getFromLocationName(params[0].toString(), 1) as Object

    }

    override fun onPostExecute(result: Any) {

        mapResponse_.onProcessComplete(result)

    }


}
