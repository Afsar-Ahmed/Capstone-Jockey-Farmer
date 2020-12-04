package sheridan.capstone.findmyfarmer.Customer.View

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener
import com.squareup.picasso.Picasso
import sheridan.capstone.findmyfarmer.Customer.Model.*
import sheridan.capstone.findmyfarmer.Entities.Following
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData

class FarmerInfo : Fragment(),FruitListToView.OnItemClickListener{

    private lateinit var FarmName:TextView

    private lateinit var FarmDesc:TextView
    private lateinit var FarmRating : RatingBar
    private lateinit var FarmCity:TextView

    private lateinit var FarmImage : ImageView
    private lateinit var To_Map: Button
    private lateinit var RateIt: ImageView

    private lateinit var FarmFollow : ImageView
    private var productlist = ArrayList<Product>()
    var ImageInt : Int =0
    private lateinit var viewModel: SharedViewModel
    private lateinit var sessionData: SessionData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=  inflater.inflate(R.layout.fragment_farmer_info, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sessionData = SessionData(activity)
        FarmName  = view.findViewById(R.id.Name)
        FarmCity = view.findViewById(R.id.Address)
        FarmDesc = view.findViewById(R.id.Desc)
        FarmRating = view.findViewById(R.id.Ratings)
        FarmImage = view.findViewById(R.id.icon)

        To_Map = view.findViewById(R.id.Maps)
        RateIt = view.findViewById(R.id.RateIt)
        FarmFollow = view.findViewById(R.id.FollowIcon)

        val recycleView : RecyclerView = view.findViewById(R.id.Fruit_List)

        var adapter = FruitListToView(productlist, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        var farmproduct = activity?.let { GetAllProducts(it,adapter) }
        if (farmproduct != null) {
            farmproduct.farmProducts(productlist,viewModel.getFarmData().value!!.farmID)
        }

        RateIt.setOnClickListener{
            openDialog()
        }

        FarmFollow.setOnClickListener {
            if(FarmFollow.drawable.constantState == resources.getDrawable(android.R.drawable.btn_star_big_off).constantState){
                var Following: FollowingDialog =
                    FollowingDialog(FarmFollow)

                val FragmentManager : FragmentManager? = activity?.supportFragmentManager
                if (FragmentManager != null) {
                    Following.show(FragmentManager, "Follow?")
                }
            }
            else{

                var deletedFollow = activity?.let { it1 -> DeleteFollow(it1,FarmFollow) }
                var farmid = viewModel.getFarmData().value!!.farmID
                var customerid = sessionData.customerData.customerID
                var following = Following(1,customerid,farmid)
                if (deletedFollow != null) {
                    deletedFollow.removefollow(following)
                }
            }
        }

        var farm = viewModel.getFarmData().value

        To_Map.setOnClickListener {
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager

            val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,
                FarmersMap()
            )
                ?.commit()
        }
        if (farm != null) {
            if(farm.isFollowed){
               FarmFollow.setImageDrawable(context?.resources?.getDrawable(android.R.drawable.btn_star_big_on))
            }
            else{
                FarmFollow.setImageDrawable(context?.resources?.getDrawable(android.R.drawable.btn_star_big_off))
            }

            if(farm.alreadyRated){
                RateIt.visibility = View.INVISIBLE
                RateIt.isEnabled = false;
            }
            else{
                RateIt.visibility = View.VISIBLE
                RateIt.isEnabled = true;
            }
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

        if(!(viewModel.getFarmData().value!!.primaryImage.isNullOrBlank())){
            Picasso.get().load(viewModel.getFarmData().value!!.primaryImage).into(FarmImage)
        }
        FarmName.text = viewModel.getFarmData().value!!.businessName
        FarmCity.text = viewModel.getFarmData().value!!.city
        FarmDesc.text = viewModel.getFarmData().value!!.businessDescription
        FarmRating.rating = viewModel.getFarmData().value!!.businessRating

    }

    override fun onItemClick(position: Int) {
        //Click Event For FruitList
    }


}