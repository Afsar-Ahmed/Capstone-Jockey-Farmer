package sheridan.capstone.findmyfarmer.Farmer.Controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmer_listing.view.*
import kotlinx.android.synthetic.main.farmers_business_info_card.view.*
import sheridan.capstone.findmyfarmer.Farmer.Model.HubListData
import sheridan.capstone.findmyfarmer.R

class HubListToView (private val HubList: List<HubListData>, private val listener: HubListToView.OnItemClickListener)
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
        holder.imageView.setImageResource(currentItem.imageResouce)
        holder.Farmer_Name.text = currentItem.Farmer_Name
        holder.Farmers_Desc.text = currentItem.Farmer_Desc
        holder.Rating.rating = currentItem.Farmer_Rating
        holder.Farmer_City.text = currentItem.Farmer_City
        holder.Farmer_Followers.text = currentItem.Farmer_Followers.toString()





    }





    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val imageView: RoundedImageView = itemView.Farmers_Hub_Image
        val Farmer_Name: TextView = itemView.Farmers_Hub_Name
        val Farmers_Desc: TextView = itemView.Farmers_Hub_Desc
        val Rating: RatingBar = itemView.Farmers_Hub_Rating
        val Farmer_City: TextView = itemView.Farmers_Hub_City
        val Farmer_Followers : TextView = itemView.Following_Me




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