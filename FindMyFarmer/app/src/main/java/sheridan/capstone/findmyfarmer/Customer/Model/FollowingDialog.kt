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
import com.squareup.picasso.Picasso
import sheridan.capstone.findmyfarmer.Entities.Following
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData

private lateinit var Add_To_Following : Button
private lateinit var FarmImage : ImageView
private lateinit var FarmName: TextView
private  var Image : Int = 0
private var Farm_City_View = ""
private var Farm_Desc_View = ""


class FollowingDialog(val imageView: ImageView): AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.following_dialog, null)

        FarmImage = view.findViewById(R.id.Image_Following)
        FarmName = view.findViewById(R.id.Farmers_Name)
        Add_To_Following = view.findViewById(R.id.Following)

        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        FarmName.text= viewModel.getFarmData().value!!.businessName
        Farm_City_View = viewModel.getFarmData().value!!.city
        Farm_Desc_View = viewModel.getFarmData().value!!.businessDescription
        var img = viewModel.getFarmData().value!!.primaryImage
        if(!(img.isNullOrBlank())){
            Picasso.get().load(img).into(FarmImage)
        }
        else{
            FarmImage.setImageResource(R.drawable.default_farm_image)
        }

        builder.setView(view)

        Add_To_Following.setOnClickListener {
            //add farmer to the following table.
            var viewModel =  ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            var sessionData = SessionData(activity)
            var farmid = viewModel.getFarmData().value!!.farmID
            var customerid = sessionData.customerData.customerID
            var following = Following(1,customerid,farmid)
            var addFollow = activity?.let { it1 -> AddFollow(it1,this,imageView) }
            if (addFollow != null) {
                addFollow.addfollow(following)
            }
        }
        return builder.create()
    }


}
