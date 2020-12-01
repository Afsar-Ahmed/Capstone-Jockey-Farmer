package sheridan.capstone.findmyfarmer.Farmer.Controller

<<<<<<< HEAD
=======
import android.app.Activity
import android.graphics.Bitmap
>>>>>>> Sohaib
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmer_listing.view.*
import kotlinx.android.synthetic.main.farmers_business_info_card.view.*
import sheridan.capstone.findmyfarmer.Farmer.Model.HubListData
import sheridan.capstone.findmyfarmer.R

class HubListToView (private val HubList: List<HubListData>, private val listener: HubListToView.OnItemClickListener)
=======
import com.google.firebase.storage.StorageReference
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.farmers_business_info_card.view.*
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import sheridan.capstone.findmyfarmer.MainActivity
import sheridan.capstone.findmyfarmer.R

class HubListToView (private val activity: Activity, val HubList: List<Farm>, private val listener: HubListToView.OnItemClickListener)
>>>>>>> Sohaib
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

<<<<<<< HEAD

        val currentItem = HubList[position]
        holder.imageView.setImageResource(currentItem.imageResouce)
        holder.Farmer_Name.text = currentItem.Farmer_Name
        holder.Farmers_Desc.text = currentItem.Farmer_Desc
        holder.Rating.rating = currentItem.Farmer_Rating
        holder.Farmer_City.text = currentItem.Farmer_City
        holder.Farmer_Followers.text = currentItem.Farmer_Followers.toString()





=======
        val currentItem = HubList[position]
        if(!(currentItem.primaryImage.isNullOrBlank())){
            Picasso.get().load(currentItem.primaryImage).into(holder.imageView)
        }
        holder.Farmer_Name.text = currentItem.businessName
        holder.Farmers_Desc.text = currentItem.businessDescription
        holder.Rating.rating = currentItem.businessRating
        holder.Farmer_City.text = currentItem.city
        //holder.Farmer_Followers.text = currentItem.Farmer_Followers.toString()
>>>>>>> Sohaib
    }





    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val imageView: RoundedImageView = itemView.Farmers_Hub_Image
        val Farmer_Name: TextView = itemView.Farmers_Hub_Name
        val Farmers_Desc: TextView = itemView.Farmers_Hub_Desc
        val Rating: RatingBar = itemView.Farmers_Hub_Rating
        val Farmer_City: TextView = itemView.Farmers_Hub_City
<<<<<<< HEAD
        val Farmer_Followers : TextView = itemView.Following_Me


=======
        //val Farmer_Followers : TextView = itemView.Following_Me
>>>>>>> Sohaib


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