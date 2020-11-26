package sheridan.capstone.findmyfarmer.Farmer.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sheridan.capstone.findmyfarmer.Customer.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.Customer.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Customer.View.FarmerInfo
import sheridan.capstone.findmyfarmer.Farmer.Controller.GenerateHubList
import sheridan.capstone.findmyfarmer.Farmer.Controller.HubListToView
import sheridan.capstone.findmyfarmer.R


class FarmerHub : Fragment(),HubListToView.OnItemClickListener {


    var GenerateController: GenerateHubList = GenerateHubList()

    private lateinit var viewModel: SharedViewModel

    val List = GenerateController.GenerateListHub(3)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_farmer_hub, container, false)


        val recycleView: RecyclerView = view.findViewById(R.id.Hub_Recycle_View)

        recycleView.adapter =
            HubListToView(
                List, this
            )

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        return view

    }

    override fun onItemClick(position: Int) {

        val Image = List[position].imageResouce
        val Farmer_Name = List[position].Farmer_Name
        val Farmer_Desc = List[position].Farmer_Desc
        val Farmer_City = List[position].Farmer_City


        viewModel.setImage(Image)
        viewModel.setFarmer_Name(Farmer_Name)
        viewModel.setFarmer_Desc(Farmer_Desc)
        viewModel.setFarmer_City(Farmer_City)



        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,
            FarmManager(),
        )
            ?.commit()




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.getFarmer_Name()
        viewModel.getFarmer_City()
        viewModel.getFarmer_Desc()
        viewModel.getFarmer_Rating()
        viewModel.getImage()
        viewModel.getFarmers_Followers()

    }
}

