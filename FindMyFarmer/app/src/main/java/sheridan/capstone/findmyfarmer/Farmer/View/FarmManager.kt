package sheridan.capstone.findmyfarmer.Farmer.View

<<<<<<< HEAD
=======
import android.graphics.Bitmap
>>>>>>> Sohaib
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.ImageView
import android.widget.TextView
=======
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
>>>>>>> Sohaib
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
<<<<<<< HEAD
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Customer.View.FarmerInfo
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.Farmer.Controller.GenerateFruitList
=======
import com.squareup.picasso.Picasso
import sheridan.capstone.findmyfarmer.Customer.Model.ImageDialog
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.Farmer.Model.FarmDBHandler
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
>>>>>>> Sohaib
import sheridan.capstone.findmyfarmer.R

class FarmManager : Fragment(),FruitListToView.OnItemClickListener  {


<<<<<<< HEAD
    private lateinit var Farmers_Image : ImageView
    private lateinit var Farmers_Name : TextView
    private lateinit var Farmers_City: TextView
    private lateinit var Farmers_Desc : TextView

    var ImageInt =0;
    var FarmerController : GenerateFruitList = GenerateFruitList()


    val List = FarmerController.GenerateFruit(4)
=======
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
>>>>>>> Sohaib

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=  inflater.inflate(R.layout.fragment_farm_manager, container, false)
<<<<<<< HEAD

        Farmers_Image = view.findViewById(R.id.Farmers_Updated_Image)
        Farmers_Name  = view.findViewById(R.id.Farmers_Name_Updated)
        Farmers_City =  view.findViewById(R.id.Farmers_City_Updated)
        Farmers_Desc = view.findViewById(R.id.Farmers_Updated_Desc)

        val recycleView : RecyclerView = view.findViewById(R.id.fruitlist)

        recycleView.adapter =
            FruitListToView(
                List, this
            )

        recycleView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            true
        )
        recycleView.setHasFixedSize(true)



        return  view
    }

    override fun onItemClick(position: Int) {


=======
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        Farm_Image = view.findViewById(R.id.Farm_Image_Updated)
        Farm_Name  = view.findViewById(R.id.Farm_Name_Update)
        Farm_Desc = view.findViewById(R.id.Farm_Desc_Update)
        Farm_Unit = view.findViewById(R.id.Farm_Unit_Update)
        Farm_Street = view.findViewById(R.id.Farm_Street_Update)
        Farm_City =  view.findViewById(R.id.Farm_City_Update)
        Farm_Country = view.findViewById(R.id.Farm_Country_Update)
        Farm_PostalCode =view.findViewById(R.id.Farm_PostalCode_Update)
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
>>>>>>> Sohaib
        val FragmentManager : FragmentManager? = activity?.supportFragmentManager

        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,
            ProductManagement()
        )
            ?.commit()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
<<<<<<< HEAD
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.getFarmer_Name().observe(viewLifecycleOwner, Observer {

            Farmers_Name.text = it
        })
        viewModel.getFarmer_City().observe(viewLifecycleOwner, Observer {

            Farmers_City.text = it
        })
        viewModel.getFarmer_Desc().observe(viewLifecycleOwner, Observer {

            Farmers_Desc.text = it
        })
        viewModel.getImage().observe(viewLifecycleOwner, Observer {
            ImageInt = it
            Farmers_Image.setImageResource(it)
        })

    }
=======
        viewModel.getFarmData().observe(viewLifecycleOwner, Observer {
            Farm_Name.setText(it.businessName)
            Farm_City.setText(it.city)
            Farm_Desc.setText(it.businessDescription)
            Farm_Street.setText(it.street)
            Farm_Unit.setText(it.unit.toString())
            Farm_Country.setText(it.country)
            Farm_PostalCode.setText(it.postalCode)
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
                val FragmentManager : FragmentManager? = activity?.supportFragmentManager
                var updateFarm = FragmentManager?.let { FarmDBHandler(requireActivity(),progbar, it) }
                if (updateFarm != null) {
                    updateFarm.updatefarm(farm)
                }
            }
            else{
                Farm_PostalCode.setError("PostalCode has to be 6 letters only")
            }
        }
    }

>>>>>>> Sohaib
}