package sheridan.capstone.findmyfarmer.Customer.Controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmer_listing.view.*
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.R

class FollowingListToView (private val FavoritList: List<Farm>, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<FollowingListToView.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_following,
            parent, false
        )
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount() = FavoritList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = FavoritList.get(position)
        holder.imageView.setImageBitmap(currentItem.primaryImage)
        holder.Farmer_Name.text = currentItem.businessName
        holder.Farmers_Desc.text = currentItem.businessDescription
        holder.Rating.rating = currentItem.businessRating
        holder.Farmer_City.text = currentItem.city

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {


        val imageView: RoundedImageView = itemView.ImageView
        val Farmer_Name: TextView = itemView.Name
        val Farmers_Desc: TextView = itemView.Desc
        val Rating: RatingBar = itemView.rating
        val Farmer_City: TextView = itemView.City


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