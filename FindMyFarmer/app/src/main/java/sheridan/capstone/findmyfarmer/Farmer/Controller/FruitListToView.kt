package sheridan.capstone.findmyfarmer.Farmer.Controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.ImageView
import android.widget.RatingBar
=======
>>>>>>> Sohaib
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmer_info_card.view.*
<<<<<<< HEAD
import kotlinx.android.synthetic.main.farmers_business_info_card.view.*
import sheridan.capstone.findmyfarmer.Farmer.Model.FarmersFruitListData

import sheridan.capstone.findmyfarmer.R

class FruitListToView (private val FruitList: List<FarmersFruitListData>, private val listener: FruitListToView.OnItemClickListener)
=======
import sheridan.capstone.findmyfarmer.Entities.Product

import sheridan.capstone.findmyfarmer.R

class FruitListToView (private val FruitList: List<Product>, private val listener: FruitListToView.OnItemClickListener)
>>>>>>> Sohaib
    : RecyclerView.Adapter<FruitListToView.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.farmer_info_card,
            parent, false
        )
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount() = FruitList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val currentItem = FruitList[position]
<<<<<<< HEAD
        holder.Fruit_Image.setImageResource(currentItem.ImageResource)
        holder.Fruit_Name.text = currentItem.Fruit_Name
        holder.Fruit_Cat.text = currentItem.Fruit_Category
        holder.Fruit_Info.text = currentItem.Info





=======
        //holder.Fruit_Image.setImageResource(currentItem.ImageResource)
        holder.Fruit_Name.text = currentItem.productName
        holder.Fruit_Cat.text = currentItem.productCategory
>>>>>>> Sohaib
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val Fruit_Image : RoundedImageView = itemView.Fruit_Image
        val Fruit_Name: TextView = itemView.Fruit_Name
        val Fruit_Cat: TextView = itemView.Fruit_Cat
<<<<<<< HEAD
        val Fruit_Info : TextView = itemView.Fruit_Info



=======
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