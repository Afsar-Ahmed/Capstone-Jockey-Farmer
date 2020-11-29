package sheridan.capstone.findmyfarmer.Customer.Model

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProvider
import sheridan.capstone.findmyfarmer.R

private lateinit var Following_Image : ImageView
private lateinit var Add_To_Following : Button
private lateinit var FarmerImage : ImageView
private lateinit var FarmerName: TextView
private  var Image : Int =0
private var Farmer_Name_View = ""
private var Farmer_City_View = ""
private var Farmer_Rating_View = 0f
private var Farmer_Desc_View = ""


class FollowingDialog: AppCompatDialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.following_dialog, null)

        FarmerImage = view.findViewById(R.id.Image_Following)
        FarmerName = view.findViewById(R.id.Farmers_Name)
        Add_To_Following = view.findViewById(R.id.Following)

        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        FarmerName.text= viewModel.getFarmData().value!!.businessName
        Farmer_Name_View = FarmerName.text.toString()

        Farmer_City_View = viewModel.getFarmData().value!!.city


       Farmer_Desc_View = viewModel.getFarmData().value!!.businessDescription

        //Image = viewModel.getImage().value!!.toInt()

        builder.setView(view)
        FarmerName.setText(
            Farmer_Name_View
        )
        FarmerImage.setImageResource(
            Image
        )

        Add_To_Following.setOnClickListener {
//add farmer to the following table.
            dialog?.dismiss()
        }
        return builder.create()
    }


}