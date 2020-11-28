package sheridan.capstone.findmyfarmer.Farmer.Model

class FarmerHubData {


    private var Fruit_Name = arrayOf("Apples", "Strawberries", "Bananas", "Watermelons","Plantains," +
            "Mushrooms","Tomatoes","Our Signature Salad")
    private var Fruit_Cat = arrayOf("Fruit", "Berry", "Fruit", "Fruit","" +
            "Vegetable","Vegetable","Fruit","General Goods")

    private var Fruit_Info = arrayOf("Selling Fast!", "Our Signiture","Just Harvested!",
    "Fresh!","No Stock - Coming Soon"," "," ")


    private var Farmer_Name = arrayOf(
        "WestSide Farmers Market", "EastSide Cherry Market",
        "Bobs Fresh Vegetables"
    )

    private var Farmer_Desc = arrayOf(
        "WestSide has the best fresh fruits and vegetables." +
                "We grow the freshest vegetables in the season",
        "At EastSide, we have the best cherries and all home grown during the season",
        "Here at Bobs Fresh Vegetable market, we grow the freshest vegetables in the season ",

    )

    private  var Farmer_Rating = arrayOf(5f,4.5f,1.4f)

    private var Farmer_City = arrayOf("Brampton","Mississauga","Toronto")

    private var Farmer_Followers = arrayOf(10,15,10)



    fun getFruitName() : Array<String> {
        return Fruit_Name
    }
    fun getFruitCategory() : Array<String>{
        return Fruit_Cat
    }

    fun getFruitInfo() : Array<String>{
        return Fruit_Info
    }
    fun getFarmerName() : Array<String>{
        return Farmer_Name
    }
    fun getFarmerDesc(): Array<String>{
        return Farmer_Desc
    }
    fun getFarmerRating() : Array<Float>{
        return Farmer_Rating
    }
    fun getFarmerCity() : Array<String>{
        return Farmer_City
    }

 fun getFarmerFollowers() : Array<Int>{
     return Farmer_Followers
 }
}