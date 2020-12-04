package sheridan.capstone.findmyfarmer.Customer.View

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Users.CustomerActivity
import java.util.*
import java.util.jar.Manifest


class Maps : Fragment(), OnMapReadyCallback {

    private lateinit var mapview: MapView
    private lateinit var mMap: GoogleMap

    private var mMarker: Marker? = null
    lateinit var fusedlocation: FusedLocationProviderClient

    lateinit var locationReq: LocationRequest

    lateinit var mLastLoc: Location

    private var lattitude: Double = 0.toDouble()
    private var longitude: Double = 0.toDouble()



    lateinit var locationCallback: LocationCallback


    companion object {
        private val My_PERMESSION_CODE: Int = 1000
    }

    private fun buildLocationCallBack() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                mLastLoc = p0!!.locations.get(p0!!.locations.size - 1)

                if (mMarker != null) {
                    mMarker!!.remove()
                }

                lattitude = mLastLoc.latitude
                longitude = mLastLoc.longitude

                val latlng = LatLng(lattitude, longitude)

                val markerOptions = MarkerOptions()
                    .position(latlng)
                    .title("Your position")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                mMarker = mMap.addMarker(markerOptions)


                mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latlng))
                mMap!!.animateCamera(CameraUpdateFactory.zoomTo(11f))

            }
        }
    }

    private fun buildLocationRequest() {
        locationReq = LocationRequest()
        locationReq.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationReq.interval = 5000
        locationReq.fastestInterval = 3000
        locationReq.smallestDisplacement = 10f

    }

    private fun checkLocationPermission(): Boolean {


        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (
                    requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ), My_PERMESSION_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ), My_PERMESSION_CODE)
            }
                return false


        }
          else
            return true


        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            My_PERMESSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            requireActivity(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        if (checkLocationPermission()) {

                            checkLocationPermission()
                            buildLocationRequest()
                            buildLocationCallBack()

                            fusedlocation = LocationServices.getFusedLocationProviderClient(requireActivity())
                            fusedlocation.requestLocationUpdates(locationReq,locationCallback, Looper.myLooper())
                            mMap!!.isMyLocationEnabled = true
                        }


                    }
                }
            }
        }
    }


        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val View: View = inflater.inflate(R.layout.fragment_maps, container, false)



            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
                checkLocationPermission()
                buildLocationRequest()
                buildLocationCallBack()

            fusedlocation = LocationServices.getFusedLocationProviderClient(View.context)
            fusedlocation.requestLocationUpdates(locationReq,locationCallback, Looper.myLooper())
}
            else
            {
                checkLocationPermission()
                buildLocationRequest()
                buildLocationCallBack()

                fusedlocation = LocationServices.getFusedLocationProviderClient(requireActivity())
                fusedlocation.requestLocationUpdates(locationReq,locationCallback, Looper.myLooper())
            }

        mapview = View.findViewById(R.id.google_map)

        mapview.onCreate(savedInstanceState)
        mapview.onResume()

        mapview.getMapAsync(this)




        return View
    }


    override fun onStop() {

        fusedlocation.removeLocationUpdates(locationCallback)
        super.onStop()
    }
    override fun onMapReady(map:GoogleMap?) {


        if (map != null) {
            mMap = map
        }



        if (ContextCompat.checkSelfPermission(
                requireView().context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled
        }
    else
            !mMap.isMyLocationEnabled

        mMap.uiSettings.isZoomControlsEnabled



    }



}

