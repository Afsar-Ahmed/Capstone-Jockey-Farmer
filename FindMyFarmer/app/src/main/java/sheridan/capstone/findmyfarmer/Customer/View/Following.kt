package sheridan.capstone.findmyfarmer.Customer.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sheridan.capstone.findmyfarmer.Customer.Controller.FollowingListToView
import sheridan.capstone.findmyfarmer.R

class Following : Fragment(),
    FollowingListToView.OnItemClickListener {

    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_following, container, false)


        return  view
    }
    override fun onItemClick(position: Int) {

    }

}