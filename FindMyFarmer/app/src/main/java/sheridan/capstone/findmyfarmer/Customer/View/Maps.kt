package sheridan.capstone.findmyfarmer.Customer.View

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Customer.Model.TestAddressData
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
import sheridan.capstone.findmyfarmer.Users.AnonymousUserActivity
import sheridan.capstone.findmyfarmer.Users.CustomerActivity
import sheridan.capstone.findmyfarmer.Users.FarmerActivity
import java.util.*
import kotlin.collections.ArrayList


class Maps : Fragment(),OnMapReadyCallback{
    private lateinit var mapview: MapView
    private lateinit var mMap: GoogleMap

    private lateinit var sessionData: SessionData

    private var mMarker: Marker? = null
    lateinit var fusedlocation: FusedLocationProviderClient

    lateinit var locationReq: LocationRequest

    lateinit var mLastLoc: Location


    private var lattitude: Double = 0.toDouble()
    private var longitude: Double = 0.toDouble()

    var SizeOfLatList : Int = 0

    var Index : Int =0

    var Farm_List : String = " "




    private lateinit var address: Address

    private var Lat = ArrayList<Double>()
    private  var Long = ArrayList<Double>()


    private val Address_Name_List = ArrayList<String>()
    private lateinit var viewModel: SharedViewModel

    lateinit var locationCallback: LocationCallback

     var Test_Address : TestAddressData = TestAddressData()

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
                mMap!!.animateCamera(CameraUpdateFactory.zoomTo(10f))

            }
        }
    }

    private fun buildLocationRequest() {
        locationReq = LocationRequest()
        locationReq.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationReq.interval = 1000
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionData = SessionData(requireActivity())
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        Farm_List= "Mississauga"

        val geo : Geocoder = Geocoder(requireActivity(), Locale.getDefault())



        for(farm_address in Test_Address.getFarmData()){
            val  addressList=geo.getFromLocationName(farm_address,1)

            address = addressList[0]

            Lat.add(address.latitude)
            Long.add(address.longitude)

            Address_Name_List.add(Test_Address.getFarm_Establishment_Name()[Index])

            Index++

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val View: View = inflater.inflate(R.layout.fragment_maps, container, false)

        mapview = View.findViewById(R.id.google_map)




        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
            checkLocationPermission()
            buildLocationRequest()
            buildLocationCallBack()

            fusedlocation = LocationServices.getFusedLocationProviderClient(View.context)
            fusedlocation.requestLocationUpdates(locationReq,locationCallback, Looper.myLooper())



            mapview.onCreate(savedInstanceState)
            mapview.onResume()

            mapview.getMapAsync(this)

        }
        else
        {
            checkLocationPermission()
            buildLocationRequest()
            buildLocationCallBack()

            fusedlocation = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedlocation.requestLocationUpdates(locationReq,locationCallback, Looper.myLooper())



            mapview.onCreate(savedInstanceState)
            mapview.onResume()

            mapview.getMapAsync(this)
        }






        return View
    }


    override fun onStop() {

        fusedlocation.removeLocationUpdates(locationCallback)
        super.onStop()
    }
    override fun onMapReady(map:GoogleMap?) {

        if (map != null) {
            mMap = map
            run {
                while (SizeOfLatList < Lat.size) {

                    mMap!!.addMarker(
                        MarkerOptions().position(
                            LatLng(
                                Lat[SizeOfLatList],
                                Long[SizeOfLatList]
                            )
                        ).title(Address_Name_List[SizeOfLatList])

                    )
                    SizeOfLatList++
                }
            }

        }

            if (ContextCompat.checkSelfPermission(
                    requireView().context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                mMap.isMyLocationEnabled
            } else
                !mMap.isMyLocationEnabled

            mMap.uiSettings.isZoomControlsEnabled


        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                var customer = sessionData.customerData
                if (customer != null) {
                    if (customer.isFarmer) {
                        val i = Intent(activity, FarmerActivity()::class.java)
                        startActivity(i)
                        (activity as Activity?)!!.overridePendingTransition(0, 0)
                    } else {
                        val i = Intent(activity, CustomerActivity()::class.java)
                        startActivity(i)
                        (activity as Activity?)!!.overridePendingTransition(0, 0)
                    }
                } else {
                    val i = Intent(activity, AnonymousUserActivity::class.java)
                    startActivity(i)
                    (activity as Activity?)!!.overridePendingTransition(0, 0)
                }


            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,  // LifecycleOwner
            callback
        )
    }

    }

