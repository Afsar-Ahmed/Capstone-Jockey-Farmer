package sheridan.capstone.findmyfarmer.Customer.View

import android.content.res.Configuration
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.R
import java.util.*


class FarmersMap : Fragment(), OnMapReadyCallback {


    private lateinit var address: Address

    private lateinit var mapview : MapView
    private lateinit var viewModel: SharedViewModel

    var Farmers_Address : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View: View = inflater.inflate(R.layout.fragment_farmers_map, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        Farmers_Address= viewModel.getFarmData().value!!.city

        val geo : Geocoder = Geocoder(View.context, Locale.getDefault())

        val addresslist =geo.getFromLocationName(Farmers_Address,1)

        address = addresslist.get(0)
        mapview = View.findViewById(R.id.farmers_map)

        mapview.onCreate(savedInstanceState)
        mapview.onResume()

        mapview.getMapAsync(this)


        return View
    }

    override fun onMapReady(mMap: GoogleMap?) {

    mMap!!.addMarker(MarkerOptions().position(LatLng(address.latitude,address.longitude)))
     mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(address.latitude,address.longitude),15f))

    }

}