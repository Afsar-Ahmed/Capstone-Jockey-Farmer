package sheridan.capstone.findmyfarmer.Customer.View

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
import sheridan.capstone.findmyfarmer.R


class Maps : Fragment(), OnMapReadyCallback {



    private lateinit var mapview : MapView






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val View: View = inflater.inflate(R.layout.fragment_maps, container, false)

        mapview = View.findViewById(R.id.google_map)

        mapview.onCreate(savedInstanceState)
        mapview.onResume()

        mapview.getMapAsync(this)


        return View
    }



    override fun onMapReady(map: GoogleMap?) {

        map?.addMarker(MarkerOptions().position(LatLng(43.594040, -79.644732)))
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(43.594040,-79.644732),15f))
    }
}