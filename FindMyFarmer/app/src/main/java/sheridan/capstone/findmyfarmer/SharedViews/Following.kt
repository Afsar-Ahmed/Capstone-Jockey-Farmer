package sheridan.capstone.findmyfarmer.SharedViews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sheridan.capstone.findmyfarmer.CustomerMain.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.CustomerMain.Controller.FavoriteList
import sheridan.capstone.findmyfarmer.CustomerMain.Model.ListData
import sheridan.capstone.findmyfarmer.CustomerMain.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.R

class Following : Fragment(),FavoriteList.OnItemClickListener {

    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_following, container, false)





        return  view
    }
    override fun onItemClick(position: Int) {


    }

}