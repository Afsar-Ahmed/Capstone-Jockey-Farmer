package sheridan.capstone.findmyfarmer.CustomerMain.Model

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import sheridan.capstone.findmyfarmer.R

private lateinit var Following_Image : ImageView
private lateinit var Add_To_Following : Button
private lateinit var FarmerImage : ImageView
private lateinit var FarmerName: TextView
private  var Image : Int =0
private var Farmer_Name_View = ""


class FollowingDialog: AppCompatDialogFragment() {

    fun setImagage(Farmer_Image: Int){
        Image = Farmer_Image

    }
    fun setFarmerName(Farmer_Name: String){
       Farmer_Name_View = Farmer_Name

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.following_dialog, null)

        FarmerImage = view.findViewById(R.id.Image_Following)
        FarmerName = view.findViewById(R.id.Farmers_Name)
        Add_To_Following = view.findViewById(R.id.Following)


        builder.setView(view)
        FarmerName.setText(Farmer_Name_View)
        FarmerImage.setImageResource(Image)

        Add_To_Following.setOnClickListener {
            dialog?.dismiss()
        }
        return builder.create()
    }


}