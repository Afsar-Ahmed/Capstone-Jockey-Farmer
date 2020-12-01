package sheridan.capstone.findmyfarmer.Customer.View

<<<<<<< HEAD
import android.os.Bundle
=======
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
>>>>>>> Sohaib
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
=======
import android.widget.Toast
>>>>>>> Sohaib
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Customer.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.Customer.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Farmer.View.FarmManager


class MarketPlace : Fragment(),
    FarmerListToView.OnItemClickListener {

    var FarmerController : FarmerGenerateList =
        FarmerGenerateList()


    private lateinit var viewModel : SharedViewModel
    val List = FarmerController.GenerateList(5)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val View : View = inflater.inflate(R.layout.fragment_market_place, container, false)

        val recycleView : RecyclerView = View.findViewById(R.id.recycleView)

            recycleView.adapter =
                FarmerListToView(
                    List, this
                )

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        return View

    }
    override fun onItemClick(position: Int) {

        val Image = List[position].imageResouce
        val Farmer_Name = List[position].Farmer_Name
        val Farmer_Desc = List[position].Farmer_Desc
        val Farmer_Rat = List[position].Farmer_Rating
        val Farmer_City = List[position].Farmer_City

        viewModel.setImage(Image)
        viewModel.setFarmer_Name(Farmer_Name)
        viewModel.setFarmer_Desc(Farmer_Desc)
        viewModel.setFarmer_Rat(Farmer_Rat)
        viewModel.setFarmer_City(Farmer_City)

        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,
          FarmerInfo()
        )
            ?.commit()




=======
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.AccessToken
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_farm_manager.*
import sheridan.capstone.findmyfarmer.Customer.Model.GetAllFarms
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmListToView
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData


class MarketPlace : Fragment(),
    FarmListToView.OnItemClickListener {

    private lateinit var viewModel : SharedViewModel
    private lateinit var adapter : FarmListToView
    private var FarmList = ArrayList<Farm>()
    private lateinit var swipeResfreshLayout: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val View : View = inflater.inflate(R.layout.fragment_market_place, container, false)
        swipeResfreshLayout = View.findViewById(R.id.pullToRefresh)

        val recycleView : RecyclerView = View.findViewById(R.id.recycleView)

        adapter = FarmListToView(FarmList,this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        val GetAllFarms = activity?.let { GetAllFarms(it,swipeResfreshLayout,adapter) }
        if (GetAllFarms != null) {
            GetAllFarms.GetFarms(FarmList)
        }

        swipeResfreshLayout.setOnRefreshListener {
            if (GetAllFarms != null) {
                GetAllFarms.GetFarms(FarmList)
            }
        }

        return View
    }
    override fun onItemClick(position: Int) {
        FarmList[position]?.let { viewModel.setFarmData(it) }

        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container, FarmerInfo())?.commit()
>>>>>>> Sohaib
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
<<<<<<< HEAD

=======
>>>>>>> Sohaib
    }


}