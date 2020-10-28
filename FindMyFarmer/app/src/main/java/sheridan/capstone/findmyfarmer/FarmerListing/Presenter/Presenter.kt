package sheridan.capstone.findmyfarmer.FarmerListing.Presenter

import android.content.Context
import android.view.View
import sheridan.capstone.findmyfarmer.FarmerListing.Model.FarmerListing
import sheridan.capstone.findmyfarmer.FarmerListing.View.FarmerPage
import sheridan.capstone.findmyfarmer.R

 class Presenter : Contract.Action {





    private var Farmer_Name = arrayOf(
        "WestSide Farmers Market", "EastSide Cherry Market",
        "Bobs Fresh Vegetables", "Toms Market", "Toronto's Biggest Farmers Market"
    )

    private var Farmer_Desc = arrayOf(
        "WestSide has the best fresh fruits and vegetables",
        "At EastSide, we have the best cherries and all home grown during the season",
        "Here at Bobs Fresh Vegetable market, we grow the freshest vegetables in the season ",
        "We offer some of the best prices for our produce, and our strawberries are our staple",
        "We are Toronto's most popular farmers market"
    )

    private  var Farmer_Rating = arrayOf("3/5", "4/5", "2.4/5", "4/5", "5/5")

    private var Farmer_Dist = arrayOf("3km", "4km", "10km", "50km", "3km")



    override fun GenerateList(size: Int): List<FarmerListing> {
        val list = ArrayList<FarmerListing>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.test1
                1 -> R.drawable.test3
                2 -> R.drawable.test3
                3 -> R.drawable.test3
                4 -> R.drawable.test3

                else -> R.drawable.test1
            }
            if (i == 0) {
                val item =
                    FarmerListing(
                        drawable,
                        Farmer_Name[i],
                        Farmer_Desc[i],
                        Farmer_Rating[i],
                        Farmer_Dist[i]
                    )
                list += item

            } else if (i == 1) {
                val item =
                    FarmerListing(
                        drawable,
                        Farmer_Name[i],
                        Farmer_Desc[i],
                        Farmer_Rating[i],
                        Farmer_Dist[i]
                    )
                list += item

            } else if (i == 2) {
                val item =
                    FarmerListing(
                        drawable,
                        Farmer_Name[i],
                        Farmer_Desc[i],
                        Farmer_Rating[i],
                        Farmer_Dist[i]
                    )
                list += item

            } else if (i == 3) {
                val item =
                    FarmerListing(
                        drawable,
                        Farmer_Name[i],
                        Farmer_Desc[i],
                        Farmer_Rating[i],
                        Farmer_Dist[i]
                    )
                list += item

            } else if (i == 4) {
                val item =
                    FarmerListing(
                        drawable,
                        Farmer_Name[i],
                        Farmer_Desc[i],
                        Farmer_Rating[i],
                        Farmer_Dist[i]
                    )
                list += item

            }
        }
        return list
    }

}


