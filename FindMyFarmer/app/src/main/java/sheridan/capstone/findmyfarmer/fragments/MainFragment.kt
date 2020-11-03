package sheridan.capstone.findmyfarmer.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sheridan.capstone.findmyfarmer.DashBoard
import sheridan.capstone.findmyfarmer.R


class MainFragment : Fragment() {
    var navController: NavController? = null
    private var signedIn:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        if(signedIn){
            navController!!.navigate(R.id.action_mainFragment_to_farmerListing)
        }else{
            navController!!.navigate(R.id.action_mainFragment_to_loginFragment)
        }
        
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfSignedInAccount()
    }
    private fun checkIfSignedInAccount() {

        val user = Firebase.auth.currentUser
        if (user != null) {
            signedIn = true
            //startActivity(Intent(this, DashBoard::class.java))
        }
    }
}