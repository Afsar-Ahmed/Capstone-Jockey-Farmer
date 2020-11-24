package sheridan.capstone.findmyfarmer.CustomerMain.View

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sheridan.capstone.findmyfarmer.CustomerMain.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.R


class MarketPlace : Fragment(),
    FarmerListToView.OnItemClickListener {

    private lateinit var viewModel : SharedViewModel
    private var farmlist = ArrayList<Farm>()
    private lateinit var adapter: FarmerListToView
    private lateinit var swipeResfreshLayout: SwipeRefreshLayout
    private var FarmImageList = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val View : View = inflater.inflate(R.layout.fragment_market_place, container, false)
        val recycleView : RecyclerView = View.findViewById(R.id.recycleView)
        swipeResfreshLayout = View.findViewById(R.id.pullToRefresh)

        adapter = FarmerListToView(farmlist, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        //first run to get all the farmers from the database
        swipeResfreshLayout.isRefreshing = true
        GetAllFarmsFromDatabase()

        //checks if the swipe refresh is called and gets all the farmers from the database again
        swipeResfreshLayout.setOnRefreshListener {
            swipeResfreshLayout.setProgressBackgroundColorSchemeColor(Color.RED)
            //accessing all farmers data from database
            GetAllFarmsFromDatabase()
        }

        var FIH = FirebaseImagehandler(DirectoryName.Farm,12,context)
        //FIH.RefreshLocalStorage(null)

        //FIH.loadImageFromStorage(FarmImageList[0])
        return View
    }
    private fun GetAllFarmsFromDatabase(){
        farmlist.clear()
        DatabaseAPIHandler(context, AsyncResponse {
            var AllFarms = ObjectConverter.convertStringToObject(it, Farm::class.java,true) as List<*>
            for (farm in AllFarms){
                var farmlistdata = farm as Farm
                farmlist.add(farmlistdata)
                //notifying change on list
                adapter.notifyDataSetChanged()
                swipeResfreshLayout.isRefreshing = false
            }
        }).execute("/Farms")
    }
    override fun onItemClick(position: Int) {

        //val Image = farmlist[position]
        val Farmer_Name = farmlist[position].businessName
        val Farmer_Desc = farmlist[position].businessDescription
        val Farmer_Rat = farmlist[position].businessRating.toFloat()
        val Farmer_City = farmlist[position].city

        viewModel.setImage(R.drawable.farm)
        viewModel.setFarmer_Name(Farmer_Name)
        viewModel.setFarmer_Desc(Farmer_Desc)
        viewModel.setFarmer_Rat(Farmer_Rat)
        viewModel.setFarmer_City(Farmer_City)

        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,sheridan.capstone.findmyfarmer.CustomerMain.View.FarmerInfo())
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

    }


}