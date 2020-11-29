package sheridan.capstone.findmyfarmer.Customer.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Customer.Model.FollowingDialog
import sheridan.capstone.findmyfarmer.Customer.Model.RateItDialogue
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView

class FarmerInfo : Fragment(),FruitListToView.OnItemClickListener{

private lateinit var FarmName:TextView

private lateinit var FarmDesc:TextView
private lateinit var FarmRating : RatingBar
private lateinit var FarmCity:TextView

private lateinit var FarmImage : ImageView
private lateinit var To_Map: Button
private lateinit var RateIt: ImageView

private lateinit var FarmInfo : ImageView

var ImageInt : Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view:View=  inflater.inflate(R.layout.fragment_farmer_info, container, false)
         FarmName  = view.findViewById(R.id.Name)
        FarmCity = view.findViewById(R.id.Address)
        FarmDesc = view.findViewById(R.id.Desc)
        FarmRating = view.findViewById(R.id.Ratings)
        FarmImage = view.findViewById(R.id.icon)

        To_Map = view.findViewById(R.id.Maps)
        RateIt = view.findViewById(R.id.RateIt)
        FarmInfo = view.findViewById(R.id.FollowIcon)


//        val recycleView : RecyclerView = view.findViewById(R.id.Fruit_List)
//
//        recycleView.adapter =
//            FruitListToView(
//                List, this
//            )
//        recycleView.layoutManager = LinearLayoutManager(context)
//        recycleView.setHasFixedSize(true)



        To_Map.setOnClickListener {
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager

            val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,
                Maps()
            )
                ?.commit()
        }

        RateIt.setOnClickListener{
         openDialog()
        }

        return view
    }

    fun openDialog() {
        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val exampleDialog =
            RateItDialogue()

        if (FragmentManager != null) {
            exampleDialog.show(FragmentManager,"Rate it")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        FarmImage.setImageBitmap(viewModel.getFarmData().value!!.primaryImage)
        FarmName.text = viewModel.getFarmData().value!!.businessName
        FarmCity.text = viewModel.getFarmData().value!!.city
        FarmDesc.text = viewModel.getFarmData().value!!.businessDescription
        FarmRating.rating = viewModel.getFarmData().value!!.businessRating


        FarmInfo.setOnClickListener {
            var Following: FollowingDialog =
                FollowingDialog()

            val FragmentManager : FragmentManager? = activity?.supportFragmentManager
            if (FragmentManager != null) {
                Following.show(FragmentManager, "Follow?")
            }
        }



    }

    override fun onItemClick(position: Int) {
        //Click Event For FruitList
    }


}