
//Author - Andrei Constantinescu
package sheridan.capstone.findmyfarmer.Customer.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_market_place.*
import sheridan.capstone.findmyfarmer.Customer.Model.GetAllFarms
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmListToView


class MarketPlace : Fragment(),
    FarmListToView.OnItemClickListener {

    private lateinit var viewModel : SharedViewModel
    private lateinit var adapter : FarmListToView
    private var FarmList = ArrayList<Farm>()
    private lateinit var swipeResfreshLayout: SwipeRefreshLayout
    private var overlay = ArrayList<View>()
    private lateinit var PageOverlay : View
    private lateinit var NocontextText: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val View : View = inflater.inflate(R.layout.fragment_market_place, container, false)
        swipeResfreshLayout = View.findViewById(R.id.pullToRefresh)
        PageOverlay = View.findViewById(R.id.overlay)
        NocontextText = View.findViewById(R.id.NoContentText)

        overlay.add(PageOverlay)
        overlay.add(NocontextText)

        overlay[0].visibility = android.view.View.VISIBLE
        overlay[1].visibility = android.view.View.INVISIBLE

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val recycleView : RecyclerView = View.findViewById(R.id.recycleView)
        val searchView = View.findViewById<SearchView>(R.id.searchBar)

        searchView.isEnabled = false

        adapter = FarmListToView(FarmList, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        val GetAllFarms = activity?.let { GetAllFarms(it,swipeResfreshLayout,adapter,searchView,overlay) }
        if (GetAllFarms != null) {
            GetAllFarms.GetFarms(FarmList)
        }

        swipeResfreshLayout.setOnRefreshListener {
            if (GetAllFarms != null) {
                overlay[0].visibility = android.view.View.VISIBLE
                GetAllFarms.GetFarms(FarmList)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        searchView.setOnClickListener {
            searchView.onActionViewExpanded()
        }

        return View
    }

    override fun onItemClick(position: Int) {
        FarmList[position]?.let { viewModel.setFarmData(it) }

        this.findNavController().navigate(R.id.action_nav_market_to_farmerInfo)

    }

}
