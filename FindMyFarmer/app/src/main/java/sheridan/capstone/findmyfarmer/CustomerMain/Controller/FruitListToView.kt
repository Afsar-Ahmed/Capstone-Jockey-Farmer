package sheridan.capstone.findmyfarmer.CustomerMain.Controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.farmer_info_card.view.*

import kotlinx.android.synthetic.main.farmer_listing.view.Name
import sheridan.capstone.findmyfarmer.CustomerMain.Model.FruitData

import sheridan.capstone.findmyfarmer.R

class FruitListToView (private val FruitList: List<FruitData>)
    : RecyclerView.Adapter<FruitListToView.MyViewHolder>()  {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
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
        holder.Image.setImageResource(currentItem.imageResouce)
        holder.Farmer_Name.text = currentItem.Fruit_Name
        holder.Farmer_Cat.text = currentItem.Fruit_Cat






    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val Image : RoundedImageView = itemView.ImageView
        val Farmer_Name: TextView = itemView.Name
        val Farmer_Cat : TextView = itemView.Category

    }
}