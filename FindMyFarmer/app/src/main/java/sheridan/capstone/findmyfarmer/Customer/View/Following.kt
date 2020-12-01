package sheridan.capstone.findmyfarmer.Customer.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import sheridan.capstone.findmyfarmer.Customer.Controller.FollowingList
import sheridan.capstone.findmyfarmer.R

class Following : Fragment(),
    FollowingList.OnItemClickListener {

    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_following, container, false)




=======
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sheridan.capstone.findmyfarmer.Customer.Controller.FollowingListToView
import sheridan.capstone.findmyfarmer.Customer.Model.GetFollows
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmListToView
import sheridan.capstone.findmyfarmer.R

class Following : Fragment(),
    FollowingListToView.OnItemClickListener {

    private lateinit var adapter :FollowingListToView
    var followsList = ArrayList<Farm>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_following, container, false)

        val recycleView : RecyclerView = view.findViewById(R.id.followrecycleview)

        adapter = FollowingListToView(followsList,this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

        var getFollows = activity?.let { it1 -> GetFollows(it1,adapter) }
        if (getFollows != null) {
            getFollows.GetFollowedFarms(followsList)
        }
>>>>>>> Sohaib

        return  view
    }
    override fun onItemClick(position: Int) {

<<<<<<< HEAD

=======
>>>>>>> Sohaib
    }

}