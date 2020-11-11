package sheridan.capstone.findmyfarmer.FarmerListing.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.activity_farmer_info.*
import kotlinx.android.synthetic.main.activity_farmer_page.*
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerGenerateList
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FarmerListToView
import sheridan.capstone.findmyfarmer.FarmerListing.Controller.FruitListToView
import sheridan.capstone.findmyfarmer.R



class FarmerInfo : AppCompatActivity() {
    var FarmerController : FarmerGenerateList = FarmerGenerateList()

    val List = FarmerController.GenerateFruit(4)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_info)

        backButton.setOnClickListener{
            onBackPressed()
        }
        val Rating :RatingBar = findViewById(R.id.Ratings)

        val NameS : TextView = findViewById(R.id.Name)

        val Description : TextView = findViewById(R.id.Desc)

        val Images : ImageView = findViewById(R.id.icon)

        val Address : TextView = findViewById(R.id.Address)

        val Name = intent.getStringExtra("Name")
        val Desc = intent.getStringExtra("Des")
        val Ratings = intent.getFloatExtra("Rating",1f)
        val Img = intent.getIntExtra("Image",1)
        val Add = intent.getStringExtra("City")





        recyclerView.adapter =
            FruitListToView(
                List
            )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)



        Rating.rating = Ratings
        NameS.text = Name.toString()
        Description.text = Desc.toString()
        Images.setImageResource(Img)
        Address.text = Add.toString()


    }
}