package sheridan.capstone.findmyfarmer.Farmer.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Customer.View.FarmerInfo
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.Farmer.Controller.GenerateFruitList
import sheridan.capstone.findmyfarmer.R

class FarmManager : Fragment(),FruitListToView.OnItemClickListener  {


    private lateinit var Farmers_Image : ImageView
    private lateinit var Farmers_Name : TextView
    private lateinit var Farmers_City: TextView
    private lateinit var Farmers_Desc : TextView

    var ImageInt =0;
    var FarmerController : GenerateFruitList = GenerateFruitList()


    val List = FarmerController.GenerateFruit(4)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=  inflater.inflate(R.layout.fragment_farm_manager, container, false)

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


        val FragmentManager : FragmentManager? = activity?.supportFragmentManager

        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,
            ProductManagement()
        )
            ?.commit()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}