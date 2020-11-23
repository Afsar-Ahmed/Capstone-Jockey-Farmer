package sheridan.capstone.findmyfarmer.CustomerMain.View

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
import sheridan.capstone.findmyfarmer.CustomerMain.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.CustomerMain.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.CustomerMain.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.R


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