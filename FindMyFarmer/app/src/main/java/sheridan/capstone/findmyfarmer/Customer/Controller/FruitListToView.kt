package sheridan.capstone.findmyfarmer.Customer.Controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.farmer_listing.view.*
import sheridan.capstone.findmyfarmer.Customer.Model.FruitData
import sheridan.capstone.findmyfarmer.R

class FruitListToView(val FruitList: ArrayList<FruitData>, private val listener:OnItemClickListener)
    : RecyclerView.Adapter<FruitListToView.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitListToView.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_display_card, parent, false)
        return MyViewHolder(
            itemView
            )
    }



    override fun getItemCount() = FruitList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentITem = FruitList[position]

        if(!(currentITem.imageResouce.isNullOrBlank())){
            Picasso.get().load(currentITem.imageResouce).into(holder.imageView)
        }
        holder.Fruit_Name.text = currentITem.Fruit_Name
        holder.Fruit_Cat.text = currentITem.Fruit_Cat

    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{


        val imageView: RoundedImageView = itemView.ImageView
        var Fruit_Name: TextView = itemView.Name
        var Fruit_Cat: TextView = itemView.Desc

        init{itemView.setOnClickListener(this)}


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