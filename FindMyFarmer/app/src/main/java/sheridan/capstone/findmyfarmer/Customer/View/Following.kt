package sheridan.capstone.findmyfarmer.Customer.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sheridan.capstone.findmyfarmer.Customer.Controller.FollowingListToView
import sheridan.capstone.findmyfarmer.Customer.Model.GetFollows
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmListToView
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
import sheridan.capstone.findmyfarmer.Users.AnonymousUserActivity
import sheridan.capstone.findmyfarmer.Users.CustomerActivity
import sheridan.capstone.findmyfarmer.Users.FarmerActivity


class Following : Fragment(),
    FollowingListToView.OnItemClickListener {

    private lateinit var viewModel : SharedViewModel
    private lateinit var adapter :FollowingListToView
    var followsList = ArrayList<Farm>()
    private lateinit var sessionData: SessionData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionData = SessionData(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_following, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val recycleView : RecyclerView = view.findViewById(R.id.followrecycleview)

        adapter = FollowingListToView(followsList,this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        var getFollows = activity?.let { it1 -> GetFollows(it1,adapter) }
        if (getFollows != null) {
            getFollows.GetFollowedFarms(followsList)
        }

        return  view
    }

    override fun onItemClick(position: Int) {
        followsList[position]?.let { viewModel.setFarmData(it) }

        val FragmentManager : FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container, FarmerInfo())?.commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                var customer = sessionData.customerData
                if (customer != null) {
                    if (customer.isFarmer) {
                        val i = Intent(activity, FarmerActivity()::class.java)
                        startActivity(i)
                        (activity as Activity?)!!.overridePendingTransition(0, 0)
                    } else {
                        val i = Intent(activity, CustomerActivity()::class.java)
                        startActivity(i)
                        (activity as Activity?)!!.overridePendingTransition(0, 0)
                    }
                } else {
                    val i = Intent(activity, AnonymousUserActivity::class.java)
                    startActivity(i)
                    (activity as Activity?)!!.overridePendingTransition(0, 0)
                }


        }

    }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,  // LifecycleOwner
            callback
        )
    }


}
