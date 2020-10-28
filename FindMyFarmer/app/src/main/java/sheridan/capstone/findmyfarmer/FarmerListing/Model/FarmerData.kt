package sheridan.capstone.findmyfarmer.FarmerListing.Model

class FarmerData {

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


    fun getFarmerName() : Array<String>{
        return Farmer_Name
    }
    fun getFarmerDesc(): Array<String>{
        return Farmer_Desc
    }
    fun getFarmerRating() : Array<String>{
        return Farmer_Rating
    }
    fun getFarmerDist() : Array<String>{
        return Farmer_Dist
    }
}
