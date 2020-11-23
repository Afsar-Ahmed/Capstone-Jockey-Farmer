package sheridan.capstone.findmyfarmer.CustomerMain.View

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import sheridan.capstone.findmyfarmer.CustomerMain.Model.FollowingDialog
import sheridan.capstone.findmyfarmer.CustomerMain.Model.RateItDialogue
import sheridan.capstone.findmyfarmer.CustomerMain.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.R

class FarmerInfo : Fragment(){

private lateinit var FarmerName:TextView

private lateinit var FarmerDesc:TextView
private lateinit var FarmerRating : RatingBar
private lateinit var FarmerCity:TextView

private lateinit var FarmerImage : ImageView
private lateinit var To_Products : Button
private lateinit var To_Map: Button
private lateinit var RateIt: ImageView

private lateinit var FarmerInfo : ImageView

var ImageInt : Int =0





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view:View=  inflater.inflate(R.layout.fragment_farmer_info, container, false)
         FarmerName  = view.findViewById(R.id.Name)
        FarmerCity = view.findViewById(R.id.Address)
        FarmerDesc = view.findViewById(R.id.Desc)
        FarmerRating = view.findViewById(R.id.Ratings)
        FarmerImage = view.findViewById(R.id.icon)
        To_Products = view.findViewById(R.id.Products)
        To_Map = view.findViewById(R.id.Maps)
        RateIt = view.findViewById(R.id.RateIt)
        FarmerInfo = view.findViewById(R.id.FollowIcon)



        To_Map.setOnClickListener {
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager

            val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,Maps())
                ?.commit()



        }

        To_Products.setOnClickListener {
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager

            val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,FarmerProducts())
                ?.commit()

        }

        RateIt.setOnClickListener{
         openDialog()
        }




        return view
    }

    fun openDialog() {
        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val exampleDialog = RateItDialogue()

        if (FragmentManager != null) {
            exampleDialog.show(FragmentManager,"Rate it")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.getFarmer_Name().observe(viewLifecycleOwner, Observer {

           FarmerName.text = it

        })
        viewModel.getFarmer_City().observe(viewLifecycleOwner, Observer {
            FarmerCity.text = it

        })
        viewModel.getFarmer_Desc().observe(viewLifecycleOwner, Observer {
            FarmerDesc.text = it

        })
        viewModel.getImage().observe(viewLifecycleOwner, Observer {
            ImageInt = it
        FarmerImage.setImageResource(it)
        })
        viewModel.getFarmer_Rating().observe(viewLifecycleOwner, Observer {


            FarmerRating.rating = it

        })





        FarmerInfo.setOnClickListener {
            var Following:FollowingDialog = FollowingDialog()
            Following.setImagage(ImageInt)
            Following.setFarmerName(FarmerName.text.toString())

            val FragmentManager : FragmentManager? = activity?.supportFragmentManager
            if (FragmentManager != null) {
                Following.show(FragmentManager, "Follow?")
            }
        }



    }




}