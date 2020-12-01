package sheridan.capstone.findmyfarmer.LoginAndRegistration.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import sheridan.capstone.findmyfarmer.Customer.View.MarketPlace
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Customer
import sheridan.capstone.findmyfarmer.Entities.Farmer
import sheridan.capstone.findmyfarmer.Farmer.View.FarmManager
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
import sheridan.capstone.findmyfarmer.Users.FarmerActivity

class AfterLoginFarmerRegistration(): Fragment() {

    private lateinit var sessionData: SessionData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_farmer_confirmation,container,false)

        val YesFarmer = view.findViewById<Button>(R.id.YesFarmerBtn)
        val NoFarmer = view.findViewById<Button>(R.id.NoFarmerBtn)
        sessionData = SessionData(activity)
        var customer = sessionData.customerData
        YesFarmer.setOnClickListener {
            if(customer != null){
                if(!customer.isFarmer){
                    var farmer = Farmer(1,customer.customerID)
                    DatabaseAPIHandler(activity, AsyncResponse {resp1->
                        if(!(resp1.isNullOrBlank())){
                            var FinalFarmer = ObjectConverter.convertStringToObject(resp1,Farmer::class.java,false) as Farmer
                            var cust = Customer(customer.customerID
                                            ,customer.customerName
                                            ,customer.customerEmail
                                            ,customer.customerPassword
                                            ,true)

                            DatabaseAPIHandler(activity, AsyncResponse {resp->
                                if(!(resp.isNullOrBlank())){
                                    var FinalCustomer = ObjectConverter.convertStringToObject(resp,Customer::class.java,false) as Customer
                                    sessionData.setUserDataForSession(FinalFarmer,FinalCustomer);
                                    val loggedIn = Intent(context, FarmerActivity::class.java)
                                    startActivity(loggedIn)
                                    activity?.finish()
                                }
                            }).execute("/updateCustomer",cust)
                        }
                    }).execute("/addFarmer",farmer)
                }
            }
        }

        NoFarmer.setOnClickListener {
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager
            val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, MarketPlace())?.commit()
        }

        return view
    }
}