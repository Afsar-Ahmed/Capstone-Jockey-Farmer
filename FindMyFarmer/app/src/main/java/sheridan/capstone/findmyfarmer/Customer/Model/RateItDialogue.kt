package sheridan.capstone.findmyfarmer.Customer.Model


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
<<<<<<< HEAD
import sheridan.capstone.findmyfarmer.R
=======
import androidx.lifecycle.ViewModelProvider
import sheridan.capstone.findmyfarmer.Entities.Rating
import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.SessionDataHandler.SessionData
>>>>>>> Sohaib

private lateinit var ratingsbar : RatingBar
private lateinit var ratingsdesc : EditText
private lateinit var SendFeedBack : Button
private lateinit var RatingsScale : TextView

//The custom dialog box - appearing when the user clicks the small icon near the ratings bar
class RateItDialogue : AppCompatDialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.activity_rate_it, null)
        ratingsdesc = view.findViewById(R.id.RateDesc)
        ratingsbar = view.findViewById(R.id.ratingBar)
        SendFeedBack = view.findViewById(R.id.RateIt)
        RatingsScale = view.findViewById(R.id.RatingsScale)

        builder.setView(view)
        ratingsbar.setOnRatingBarChangeListener(OnRatingBarChangeListener { ratingBar, v, b ->
           RatingsScale.setText(v.toString())
            when (ratingBar.rating.toInt()) {
                1 -> RatingsScale.setText("Very bad")
                2 -> RatingsScale.setText("Need some improvement")
                3 -> RatingsScale.setText("Good")
                4 -> RatingsScale.setText("Great")
                5 -> RatingsScale.setText("Awesome. I love it")
                else -> RatingsScale.setText("")
            }
        })
<<<<<<< HEAD

SendFeedBack.setOnClickListener {
    // add feedback desc/rating to the database in the ratings table.
    dialog?.dismiss()
}
        return builder.create()
    }


=======
        SendFeedBack.setOnClickListener {
            // add feedback desc/rating to the database in the ratings table.
            var viewModel =  ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            var sessionData = SessionData(activity)
            var farmid = viewModel.getFarmData().value!!.farmID
            var customerid = sessionData.customerData.customerID
            var rating = Rating(1,farmid,customerid, ratingsbar.rating, ratingsdesc.text.toString())
            var addRating = activity?.let { it1 -> AddRating(it1,this) }
            if (addRating != null) {
                addRating.addRating(rating)
            }
        }
        return builder.create()
    }

>>>>>>> Sohaib
}