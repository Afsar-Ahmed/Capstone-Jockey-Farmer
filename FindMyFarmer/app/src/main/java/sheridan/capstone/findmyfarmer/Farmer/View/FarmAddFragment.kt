package sheridan.capstone.findmyfarmer.Farmer.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import sheridan.capstone.findmyfarmer.Customer.Model.ImageDialog
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Farmer.Model.FarmDBHandler
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
import sheridan.capstone.findmyfarmer.Users.FarmerActivity

class FarmAddFragment(): Fragment() {

    private lateinit var Farm_Name: EditText
    private lateinit var Farm_Desc: EditText
    private lateinit var Farm_Unit: EditText
    private lateinit var Farm_Street: EditText
    private lateinit var Farm_City: EditText
    private lateinit var Farm_Country: EditText
    private lateinit var Farm_PostalCode: EditText
    private lateinit var Farm_Province: EditText
    private lateinit var viewModel: SharedViewModel
    private lateinit var progbar: ProgressBar
    private lateinit var sessionData: SessionData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_farm_add, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sessionData = SessionData(activity)

        Farm_Name = view.findViewById(R.id.farm_Name_added)
        Farm_Desc = view.findViewById(R.id.farm_Desc_added)
        Farm_Unit = view.findViewById(R.id.farm_Unit_added)
        Farm_Street = view.findViewById(R.id.farm_Street_added)
        Farm_City = view.findViewById(R.id.farm_City_added)
        Farm_Country = view.findViewById(R.id.farm_Country_added)
        Farm_PostalCode = view.findViewById(R.id.farm_PostalCode_added)
        Farm_Province = view.findViewById(R.id.farm_Province_added)

        progbar = view.findViewById(R.id.AddFarmProgbar)

        val AddFarmData = view.findViewById<Button>(R.id.addFarmData)


        AddFarmData.setOnClickListener {
            VerifyData()
        }

        return view
    }

    private fun VerifyData() {
        var notEmpty = true
        if (Farm_Name.text.toString().isEmpty()) {
            Farm_Name.setError("Cannot be Empty")
            notEmpty = false
        }
        if (Farm_Desc.text.isEmpty()) {
            Farm_Desc.setError("Cannot be Empty")
            notEmpty = false
        }
        if (Farm_City.text.isEmpty()) {
            Farm_City.setError("Cannot be Empty")
            notEmpty = false
        }
        if (Farm_Country.text.isEmpty()) {
            Farm_Country.setError("Cannot be Empty")
            notEmpty = false
        }
        if (Farm_Street.text.isEmpty()) {
            Farm_Street.setError("Cannot be Empty")
            notEmpty = false
        }
        if (Farm_PostalCode.text.isEmpty()) {
            Farm_PostalCode.setError("Cannot be Empty")
            notEmpty = false
        }
        if (Farm_Unit.text.isEmpty()) {
            Farm_Unit.setText("0")
            notEmpty = false
        }
        if (Farm_Province.text.isEmpty()) {
            Farm_Province.setError("Cannot be Empty")
            notEmpty = false
        }

        if (notEmpty) {
            var PC = Farm_PostalCode.text.toString().replace(" ", "")
            if (PC.length == 6) {
                //insert into database
                var farmer = sessionData.farmerData
                var business_name = Farm_Name.text.toString()
                var business_desc = Farm_Desc.text.toString()
                var business_rating = 0
                var city = Farm_City.text.toString()
                var street = Farm_Street.text.toString()
                var unit = Farm_Unit.text.toString().toInt()
                var country = Farm_Country.text.toString()
                var postalcode = Farm_PostalCode.text.toString()
                var province = Farm_Province.text.toString()
                if (farmer != null) {
                    var farmerid = farmer.farmerID
                    var farm = Farm(
                        id,
                        business_name,
                        business_desc,
                        business_rating.toFloat(),
                        city,
                        street,
                        country,
                        postalcode,
                        province,
                        unit,
                        farmerid
                    )


                    val AddFarm: FarmDBHandler = FarmDBHandler(requireActivity(), progbar)
                    AddFarm.addfarm(farm)


                    val i = Intent(activity, FarmerActivity::class.java)
                    startActivity(i)
                    (activity as Activity?)!!.overridePendingTransition(0, 0)

                }
            }
        }
    }

}
