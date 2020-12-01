package sheridan.capstone.findmyfarmer.Farmer.View

import android.os.Bundle
<<<<<<< HEAD
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
=======
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
>>>>>>> Sohaib
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
import sheridan.capstone.findmyfarmer.Customer.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.Customer.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Customer.View.FarmerInfo
import sheridan.capstone.findmyfarmer.Farmer.Controller.GenerateHubList
import sheridan.capstone.findmyfarmer.Farmer.Controller.HubListToView
import sheridan.capstone.findmyfarmer.R
=======
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Farmer.Controller.HubListToView
import sheridan.capstone.findmyfarmer.Farmer.Model.GetFarmersFarms
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
>>>>>>> Sohaib


class FarmerHub : Fragment(),HubListToView.OnItemClickListener {


<<<<<<< HEAD
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




=======
    private lateinit var viewModel: SharedViewModel
    private lateinit var sessionData: SessionData
    private var HubList = ArrayList<Farm>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        sessionData = SessionData(activity)
        var farmer = sessionData.farmerData


        val view: View = inflater.inflate(R.layout.fragment_farmer_hub, container, false)
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)
        val recycleView: RecyclerView = view.findViewById(R.id.Hub_Recycle_View)
        val farmadd = view.findViewById<ImageView>(R.id.AddFarmImage)

        val adapter = HubListToView(requireActivity(),HubList, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        var getFarmersFarms = context?.let { GetFarmersFarms(it,swipeRefreshLayout,adapter) }

        if (getFarmersFarms != null && farmer != null) {
            getFarmersFarms.GetHubFarms(HubList,farmer.farmerID)
        }

        swipeRefreshLayout.setOnRefreshListener {
            if (getFarmersFarms != null && farmer != null) {
                getFarmersFarms.GetHubFarms(HubList,farmer.farmerID)
            }
        }

        farmadd.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container,FarmAddFragment())?.commit()
        }



        return view
    }

    override fun onItemClick(position: Int) {
        viewModel.setFarmData(HubList[position])

        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container, FarmManager(),)?.commit()
>>>>>>> Sohaib
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
<<<<<<< HEAD
        viewModel.getFarmer_Name()
        viewModel.getFarmer_City()
        viewModel.getFarmer_Desc()
        viewModel.getFarmer_Rating()
        viewModel.getImage()
=======
        viewModel.getFarmData()
>>>>>>> Sohaib
        viewModel.getFarmers_Followers()

    }
}

