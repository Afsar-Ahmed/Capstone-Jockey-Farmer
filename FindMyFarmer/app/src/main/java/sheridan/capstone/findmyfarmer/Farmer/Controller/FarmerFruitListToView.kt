package sheridan.capstone.findmyfarmer.Farmer.Controller

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.fruit_card.view.*
import kotlinx.android.synthetic.main.fruit_card.view.Fruit_Category
import kotlinx.android.synthetic.main.fruit_card.view.Fruit_img
import kotlinx.android.synthetic.main.fruit_card.view.Fruit_name
import kotlinx.android.synthetic.main.fruit_card_farmers.view.*
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.FarmProduct
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Model.ProductManager

import sheridan.capstone.findmyfarmer.R

class FarmerFruitListToView (private val activity: Activity, var farmid : Int, val FruitList: List<Product>, private val listener: FarmerFruitListToView.OnItemClickListener)
    : RecyclerView.Adapter<FarmerFruitListToView.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fruit_card_farmers,
            parent, false
        )
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount() = FruitList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val currentItem = FruitList[position]
        //holder.Fruit_Image.setImageResource(currentItem.ImageResource)
        holder.Fruit_Name.text = currentItem.productName
        holder.Fruit_Cat.text = currentItem.productCategory
        holder.Fruit_quantity.setText(currentItem.quantity.toString())
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val Fruit_Image : RoundedImageView = itemView.Fruit_img
        val Fruit_Name: TextView = itemView.Fruit_name
        val Fruit_Cat: TextView = itemView.Fruit_Category
        val Fruit_quantity :EditText = itemView.quantity
        val addOne : Button = itemView.AddOne
        val removeOne : Button = itemView.minusOne


        init {
            itemView.setOnClickListener(this)
            var productManager = ProductManager(activity)
            addOne.setOnClickListener {
                var updatedQuantity = (Fruit_quantity.text.toString().toInt()) + 1
                if(updatedQuantity <= 999){
                    productManager.UpdateQuantity(updatedQuantity,FruitList.get(adapterPosition).productID,farmid,Fruit_quantity)
                }
            }

            removeOne.setOnClickListener {
                var updatedQuantity = (Fruit_quantity.text.toString().toInt()) - 1
                if(updatedQuantity >= 0){
                    productManager.UpdateQuantity(updatedQuantity,FruitList.get(adapterPosition).productID,farmid,Fruit_quantity)
                }
            }
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