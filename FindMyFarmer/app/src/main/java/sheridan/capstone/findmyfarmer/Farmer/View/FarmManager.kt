package sheridan.capstone.findmyfarmer.Farmer.View

/**
 * Author:  Sohaib Hussain
 **/

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import sheridan.capstone.findmyfarmer.Customer.Model.ImageDialog
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.Farmer.Model.FarmDBHandler
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Users.CustomerActivity
import sheridan.capstone.findmyfarmer.Users.FarmerActivity

class FarmManager : Fragment(){


    private lateinit var Farm_Image: ImageView
    private lateinit var Farm_Name: EditText
    private lateinit var Farm_Desc: EditText
    private lateinit var Farm_Unit: EditText
    private lateinit var Farm_Street: EditText
    private lateinit var Farm_City: EditText
    private lateinit var Farm_Country: EditText
    private lateinit var Farm_PostalCode: EditText
    private lateinit var Farm_Province: EditText
    private lateinit var ImageChangeIcon: ImageView
    private lateinit var viewModel: SharedViewModel
    private lateinit var progbar: ProgressBar

    private var ProductList = ArrayList<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_farm_manager, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        Farm_Image = view.findViewById(R.id.Farm_Image_Updated)
        Farm_Name = view.findViewById(R.id.Farm_Name_Update)
        Farm_Desc = view.findViewById(R.id.Farm_Desc_Update)
        Farm_Unit = view.findViewById(R.id.Farm_Unit_Update)
        Farm_Street = view.findViewById(R.id.Farm_Street_Update)
        Farm_City = view.findViewById(R.id.Farm_City_Update)
        Farm_Country = view.findViewById(R.id.Farm_Country_Update)
        Farm_PostalCode = view.findViewById(R.id.Farm_PostalCode_Update)
        Farm_Province = view.findViewById(R.id.Farm_Province_Update)
        ImageChangeIcon = view.findViewById(R.id.ImageChangeIcon)
        progbar = view.findViewById(R.id.updateFarmProgbar)


        val updateFarmData = view.findViewById<Button>(R.id.UpdateFarmData)
        val AddProductsToFarm = view.findViewById<Button>(R.id.AddProducts)

        ImageChangeIcon.setOnClickListener {
            //Start image dialog here
            val FragmentManager: FragmentManager? = activity?.supportFragmentManager
            val imageDialog =
                ImageDialog(DirectoryName.Farm)

            if (FragmentManager != null) {
                imageDialog.show(FragmentManager, "image")
            }
        }

        updateFarmData.setOnClickListener {
            VerifyData()
        }

        AddProductsToFarm.setOnClickListener {
            this.findNavController()
                .navigate(R.id.action_fragment_farm_manager_to_farmer_product_management2)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFarmData().observe(viewLifecycleOwner, Observer {
            Farm_Name.setText(it.businessName)
            Farm_City.setText(it.city)
            Farm_Desc.setText(it.businessDescription)
            Farm_Street.setText(it.street)
            Farm_Unit.setText(it.unit.toString())
            Farm_Country.setText(it.country)
            Farm_PostalCode.setText(it.postalCode)
            Farm_Province.setText(it.province)
            Picasso.get().load(it.primaryImage).into(Farm_Image)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFarmData().observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.primaryImage).into(Farm_Image)
        })
    }

    private fun VerifyData(){
        var notEmpty = true
        if(Farm_Name.text.toString().isEmpty()){
            Farm_Name.setError("Cannot be Empty")
            notEmpty =false
        }
        if(Farm_Desc.text.isEmpty()){
            Farm_Desc.setError("Cannot be Empty")
            notEmpty =false
        }
        if(Farm_City.text.isEmpty()){
            Farm_City.setError("Cannot be Empty")
            notEmpty =false
        }
        if(Farm_Country.text.isEmpty()){
            Farm_Country.setError("Cannot be Empty")
            notEmpty =false
        }
        if(Farm_Street.text.isEmpty()){
            Farm_Street.setError("Cannot be Empty")
            notEmpty =false
        }
        if(Farm_PostalCode.text.isEmpty()){
            Farm_PostalCode.setError("Cannot be Empty")
            notEmpty =false
        }
        if(Farm_Unit.text.isEmpty()){
            Farm_Unit.setText("0")
            notEmpty =false
        }
        if(Farm_Province.text.isEmpty()){
            Farm_Province.setError("Cannot be Empty")
            notEmpty =false
        }

        if(notEmpty){
            var PC = Farm_PostalCode.text.toString().replace(" ","")
            if(PC.length == 6){
                //insert into database
                var id = viewModel.getFarmData().value!!.farmID
                var business_name = Farm_Name.text.toString()
                var business_desc = Farm_Desc.text.toString()
                var business_rating = viewModel.getFarmData().value!!.businessRating
                var city = Farm_City.text.toString()
                var street = Farm_Street.text.toString()
                var unit = Farm_Unit.text.toString().toInt()
                var country = Farm_Country.text.toString()
                var postalcode = Farm_PostalCode.text.toString()
                var province = Farm_Province.text.toString()
                var farmerid = viewModel.getFarmData().value!!.farmerID
                var farm = Farm(
                    id,
                    business_name,
                    business_desc,
                    business_rating,
                    city,
                    street,
                    country,
                    postalcode,
                    province,
                    unit,
                    farmerid
                )

                val updateFarm : FarmDBHandler = FarmDBHandler(requireActivity(),progbar)


                updateFarm.updatefarm(farm)

                val i = Intent(activity, FarmerActivity::class.java)
                startActivity(i)
                (activity as Activity?)!!.overridePendingTransition(0, 0)
            }
        }
    }
}
