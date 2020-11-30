package sheridan.capstone.findmyfarmer.Farmer.View

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Farmer.Controller.HubListToView
import sheridan.capstone.findmyfarmer.Farmer.Model.GetFarmersFarms
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData


class FarmerHub : Fragment(),HubListToView.OnItemClickListener {


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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.getFarmData()
        viewModel.getFarmers_Followers()

    }
}

