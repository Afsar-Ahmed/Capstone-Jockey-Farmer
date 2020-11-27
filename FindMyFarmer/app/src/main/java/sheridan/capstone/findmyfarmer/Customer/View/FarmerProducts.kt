package sheridan.capstone.findmyfarmer.Customer.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text
import sheridan.capstone.findmyfarmer.R

class FarmerProducts : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.fragment_farmer_products, container, false)
        val Tname = view.findViewById<TextView>(R.id.PrdouctN)
        val Tcateg = view.findViewById<TextView>(R.id.PrdouctC)
        val Tid = view.findViewById<TextView>(R.id.PrdouctI)

        var name = view.findViewById<TextView>(R.id.text)

        Tname.text="Name"
        Tcateg.text="Category"
        Tid.text="ID"



        // Inflate the layout for this fragment
        return view
    }


}