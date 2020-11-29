package sheridan.capstone.findmyfarmer.Farmer.Controller

import android.app.Activity
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.StorageReference
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmers_business_info_card.view.*
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import sheridan.capstone.findmyfarmer.MainActivity
import sheridan.capstone.findmyfarmer.R

class HubListToView (private val activity: Activity, val HubList: List<Farm>, private val listener: HubListToView.OnItemClickListener)
    : RecyclerView.Adapter<HubListToView.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.farmers_business_info_card,
            parent, false
        )
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount() = HubList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = HubList[position]

        holder.imageView.setImageBitmap(currentItem.primaryImage)
        holder.Farmer_Name.text = currentItem.businessName
        holder.Farmers_Desc.text = currentItem.businessDescription
        holder.Rating.rating = currentItem.businessRating
        holder.Farmer_City.text = currentItem.city
        //holder.Farmer_Followers.text = currentItem.Farmer_Followers.toString()
    }





    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val imageView: RoundedImageView = itemView.Farmers_Hub_Image
        val Farmer_Name: TextView = itemView.Farmers_Hub_Name
        val Farmers_Desc: TextView = itemView.Farmers_Hub_Desc
        val Rating: RatingBar = itemView.Farmers_Hub_Rating
        val Farmer_City: TextView = itemView.Farmers_Hub_City
        //val Farmer_Followers : TextView = itemView.Following_Me


        init {
            itemView.setOnClickListener(this)
        }


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