package sheridan.capstone.findmyfarmer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_farmer_page.*
import kotlinx.android.synthetic.main.activity_farmer_page.recycleView
import kotlinx.android.synthetic.main.fragment_farmer_listing.*
import kotlinx.android.synthetic.main.fragment_login.*
import sheridan.capstone.findmyfarmer.FarmerData
import sheridan.capstone.findmyfarmer.FarmerListingView
import sheridan.capstone.findmyfarmer.R



class FarmerPageFragment : Fragment() {
    var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farmer_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        fragment_logout_btn.setOnClickListener{
            navController!!.navigate(R.id.action_farmerListing_to_loginFragment2)
            Firebase.auth.signOut()

        }
        val data = FarmerData()
        val List = data.GenerateList(4)
        recycleView.adapter = FarmerListingView(List)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)

    }

}