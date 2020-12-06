package sheridan.capstone.findmyfarmer.Farmer.Model

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Farmer.View.FarmerHub
import sheridan.capstone.findmyfarmer.R

private lateinit var YesDelete: Button
private lateinit var NoDelete : Button

class FarmDeleteConfirmDialog(private val farm: Farm): AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.farm_delete_dialog, null)

        YesDelete = view.findViewById(R.id.YesDelete)
        NoDelete = view.findViewById(R.id.NoDelete)

        YesDelete.setOnClickListener {
            var farmDBHandler = FarmDBHandler(requireActivity(), null,null)
            farmDBHandler.deletefarm(farm)
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager
            val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, FarmerHub())?.commit()
            dismiss()
        }

        NoDelete.setOnClickListener {
            val FragmentManager : FragmentManager? = activity?.supportFragmentManager
            val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, FarmerHub())?.commit()
            dismiss()
        }

        builder.setView(view)
        return builder.create()

    }
}