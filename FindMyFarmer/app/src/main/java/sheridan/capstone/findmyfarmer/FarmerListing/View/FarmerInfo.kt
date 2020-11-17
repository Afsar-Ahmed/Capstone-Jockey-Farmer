package sheridan.capstone.findmyfarmer.FarmerListing.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farmer_info.*
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.R



class FarmerInfo : AppCompatActivity() {
    val ProductList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_info)

        backButton.setOnClickListener{
            onBackPressed()
        }
        val Rating = findViewById<RatingBar>(R.id.Ratings)
        val NameS = findViewById<TextView>(R.id.Name)
        val Description = findViewById<TextView>(R.id.Desc)
        val Images = findViewById<ImageView>(R.id.icon)
        val Address = findViewById<TextView>(R.id.Address)

        val Name = intent.getStringExtra("Name")
        val Desc = intent.getStringExtra("Des")
        val Ratings = intent.getFloatExtra("Rating",1f)
        //val Img = intent.getIntExtra("Image",1)
        val Add = intent.getStringExtra("City")
        val farmerid = intent.getIntExtra("FarmerID",-1);

        //setting adapter to fruit list
        val adap = FruitListToView(ProductList)
        recyclerView.adapter = adap
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        //setting values for Farmer info
        Rating.rating = Ratings
        NameS.text = Name.toString()
        Description.text = Desc.toString()
        //Images.setImageResource(Img)
        Address.text = Add.toString()




        val c = DatabaseAPIHandler(this, AsyncResponse {
            var products =  ObjectConverter.convertStringToObject(it,Product::class.java,true) as List<Object>
            if(products.size > 0){
                ProductList.addAll(products as List<Product>)
                adap.notifyDataSetChanged()
            }
        }).execute("/ProductsByFarmerID/${farmerid}")

    }
}