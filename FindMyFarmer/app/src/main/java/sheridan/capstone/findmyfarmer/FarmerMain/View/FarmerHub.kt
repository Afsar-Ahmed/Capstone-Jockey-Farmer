package sheridan.capstone.findmyfarmer.FarmerMain.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sheridan.capstone.findmyfarmer.R


class FarmerHub : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_farmer_hub, container, false)
    }


}