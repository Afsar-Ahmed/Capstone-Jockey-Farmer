package sheridan.capstone.findmyfarmer.Farmer.View

import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_farm_manager.*
import sheridan.capstone.findmyfarmer.Customer.Model.FollowingDialog
import sheridan.capstone.findmyfarmer.Customer.Model.ImageDialog
import sheridan.capstone.findmyfarmer.Customer.Model.RateItDialogue
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.Farmer.Model.GetFarmProducts
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.R

class FarmManager : Fragment(),FruitListToView.OnItemClickListener  {


    private lateinit var Farm_Image : ImageView
    private lateinit var Farm_Name : EditText
    private lateinit var Farm_Desc : EditText
    private lateinit var Farm_Unit: EditText
    private lateinit var Farm_Street : EditText
    private lateinit var Farm_City: EditText
    private lateinit var Farm_Country: EditText
    private lateinit var Farm_PostalCode: EditText
    private lateinit var ImageChangeIcon: ImageView
    private lateinit var viewModel: SharedViewModel
    private lateinit var progbar: ProgressBar

    private var ProductList = ArrayList<Product>()
    private lateinit var ImageBitmap: Bitmap;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=  inflater.inflate(R.layout.fragment_farm_manager, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        Farm_Image = view.findViewById(R.id.Farm_Updated_Image)
        Farm_Name  = view.findViewById(R.id.Farm_Name_Updated)
        Farm_Desc = view.findViewById(R.id.Farm_Updated_Desc)
        Farm_Unit = view.findViewById(R.id.Farm_Unit_Updated)
        Farm_Street = view.findViewById(R.id.Farm_Street_Updated)
        Farm_City =  view.findViewById(R.id.Farm_City_Updated)
        Farm_Country = view.findViewById(R.id.Farm_Country_Updated)
        Farm_PostalCode =view.findViewById(R.id.Farm_PostalCode_Updated)
        ImageChangeIcon = view.findViewById(R.id.ImageChangeIcon)
        progbar = view.findViewById(R.id.updateFarmProgbar)


        val updateFarmData = view.findViewById<Button>(R.id.UpdateFarmData)
        val AddProductsToFarm = view.findViewById<Button>(R.id.AddProducts)

        ImageChangeIcon.setOnClickListener {
            //Start image dialog here
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager
            val imageDialog =
                ImageDialog(DirectoryName.Farm)

            if (FragmentManager != null) {
                imageDialog.show(FragmentManager,"image")
            }
        }

        updateFarmData.setOnClickListener {
            VerifyData()
        }

        return view
    }

    override fun onItemClick(position: Int) {
        val FragmentManager : FragmentManager? = activity?.supportFragmentManager

        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,
            ProductManagement()
        )
            ?.commit()

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
            Farm_Image.setImageBitmap(it.primaryImage)
        })
    }
    override fun onResume() {
        super.onResume()
        viewModel.getFarmData().observe(viewLifecycleOwner, Observer {
            Farm_Image.setImageBitmap(it.primaryImage)
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
            Farm_Unit.setError("Cannot be Empty")
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
                var farmerid = viewModel.getFarmData().value!!.farmerID
                var farm = Farm(id,business_name,business_desc,business_rating,city,street,country,postalcode,unit,farmerid)
                progbar.visibility = ProgressBar.VISIBLE
                DatabaseAPIHandler(context, AsyncResponse {
                    progbar.visibility = ProgressBar.INVISIBLE
                }).execute("/UpdateFarm",farm)
            }
            else{
                Farm_PostalCode.setError("PostalCode has to be 6 letters only")
            }
        }
    }

}