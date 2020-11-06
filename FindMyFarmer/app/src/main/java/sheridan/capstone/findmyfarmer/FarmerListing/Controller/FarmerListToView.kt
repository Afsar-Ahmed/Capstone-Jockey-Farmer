package sheridan.capstone.findmyfarmer.FarmerListing.Controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmer_listing.view.*
import sheridan.capstone.findmyfarmer.FarmerListing.Model.ListData
import sheridan.capstone.findmyfarmer.R


class FarmerListToView (private val FarmerList: List<ListData>, private val listener:OnItemClickListener)
    : RecyclerView.Adapter<FarmerListToView.MyViewHolder>() {

    var Name : String = String()
    var selection : Boolean = false


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
        holder.Rating.rating = currentItem.Farmer_Rating
        holder.Farmer_City.text = currentItem.Farmer_City





    }

 fun getSelections() : Boolean {
     return selection
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

