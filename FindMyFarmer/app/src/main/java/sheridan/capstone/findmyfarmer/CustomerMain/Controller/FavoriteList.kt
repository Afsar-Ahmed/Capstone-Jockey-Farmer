package sheridan.capstone.findmyfarmer.CustomerMain.Controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmer_listing.view.*
import sheridan.capstone.findmyfarmer.CustomerMain.Model.ListData
import sheridan.capstone.findmyfarmer.R

class FavoriteList (private val FavoritList: List<ListData>, private val listener: FavoriteList.OnItemClickListener)
    : RecyclerView.Adapter<FavoriteList.MyViewHolder>() {


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
        holder.imageView.setImageResource(currentItem.imageResouce)
        holder.Farmer_Name.text = currentItem.Farmer_Name
        holder.Farmers_Desc.text = currentItem.Farmer_Desc
        holder.Rating.rating = currentItem.Farmer_Rating
        holder.Farmer_City.text = currentItem.Farmer_City





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
