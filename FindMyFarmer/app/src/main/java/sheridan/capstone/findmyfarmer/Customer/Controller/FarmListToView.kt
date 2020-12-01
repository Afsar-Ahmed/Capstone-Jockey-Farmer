package sheridan.capstone.findmyfarmer.FarmerListing.Controller

import android.app.Activity
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.StorageReference
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.farmer_listing.view.*
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import sheridan.capstone.findmyfarmer.R


class FarmListToView (val FarmList: ArrayList<Farm>, private val listener:OnItemClickListener)
    : RecyclerView.Adapter<FarmListToView.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.farmer_listing, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount() = FarmList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = FarmList[position]

        if(!(currentItem.primaryImage.isNullOrBlank())){
            Picasso.get().load(currentItem.primaryImage).into(holder.imageView)
        }
        holder.Farm_Name.text = currentItem.businessName
        holder.Farm_Desc.text = currentItem.businessDescription
        holder.Rating.rating = currentItem.businessRating
        holder.Farm_City.text = currentItem.city


    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        val imageView: RoundedImageView = itemView.ImageView
        val Farm_Name: TextView = itemView.Name
        val Farm_Desc: TextView = itemView.Desc
        val Rating: RatingBar = itemView.rating
        val Farm_City: TextView = itemView.City


        init { itemView.setOnClickListener(this) }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}