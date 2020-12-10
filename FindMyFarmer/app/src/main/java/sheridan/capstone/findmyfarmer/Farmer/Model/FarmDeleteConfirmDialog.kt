package sheridan.capstone.findmyfarmer.Farmer.Model

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.navigation.fragment.findNavController
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Users.CustomerActivity

private lateinit var YesDelete: Button
private lateinit var NoDelete : Button

class FarmDeleteConfirmDialog(): AppCompatDialogFragment() {

    private lateinit var farm : Farm

    fun FarmDelete(farm_new: Farm) {
    farm = farm_new
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.farm_delete_dialog, null)

        YesDelete = view.findViewById(R.id.YesDelete)
        NoDelete = view.findViewById(R.id.NoDelete)

        YesDelete.setOnClickListener {
            var farmDBHandler = FarmDBHandler(requireActivity(), null)
            farmDBHandler.deletefarm(farm)

            this.findNavController().navigate(R.id.action_nav_manage_hub_self)
            dismiss()
        }

        NoDelete.setOnClickListener {

            this.findNavController().navigate(R.id.action_nav_manage_hub_self)
            dismiss()


        }

        builder.setView(view)
        return builder.create()

    }
}