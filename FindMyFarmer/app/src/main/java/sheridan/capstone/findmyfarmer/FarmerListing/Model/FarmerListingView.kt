package sheridan.capstone.findmyfarmer.FarmerListing.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.farmer_listing.view.*
import sheridan.capstone.findmyfarmer.R

class FarmerListingView (private val FarmerList: List<FarmerListing>): RecyclerView.Adapter<FarmerListingView.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.farmer_listing,
            parent, false
        )
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount() = FarmerList.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = FarmerList[position]
        holder.imageView.setImageResource(currentItem.imageResouce)
        holder.Farmer_Name.text = currentItem.Farmer_Name
        holder.Farmers_Desc.text = currentItem.Farmer_Desc
        holder.Rating.text = currentItem.Farmer_Rating
        holder.Distance_Away.text = currentItem.Farmer_Distance

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.imageView
        val Farmer_Name: TextView = itemView.Farmer_Name
        val Farmers_Desc: TextView = itemView.Farmers_Desc
        val Rating: TextView = itemView.Rating
        val Distance_Away: TextView = itemView.Distance_Away


    }
}
