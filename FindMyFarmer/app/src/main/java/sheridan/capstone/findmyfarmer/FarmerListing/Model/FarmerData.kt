package sheridan.capstone.findmyfarmer.FarmerListing.Model

import org.json.JSONArray
import sheridan.capstone.findmyfarmer.Database.ProductAPI
class FarmerData {

    var productList:ProductAPI=ProductAPI()

    private var Fruit_List= productList.apiLOAD()
    private var Fruit_Cat = arrayOf("Fruit","Berry","Fruit","Berry")


    private var Farmer_Name = arrayOf(
        "WestSide Farmers Market", "EastSide Cherry Market",
        "Bobs Fresh Vegetables", "Toms Market", "Toronto's Biggest Farmers Market"
    )

    private var Farmer_Desc = arrayOf(
        "WestSide has the best fresh fruits and vegetables." +
                "We grow the freshest vegetables in the season",
        "At EastSide, we have the best cherries and all home grown during the season",
        "Here at Bobs Fresh Vegetable market, we grow the freshest vegetables in the season ",
        "We offer some of the best prices for our produce, and our strawberries are our staple",
        "We are Toronto's most popular farmers market"
    )

    private  var Farmer_Rating = arrayOf(5f,4.5f,1.4f,4f,3f)

    private var Farmer_City = arrayOf("Brampton","Mississauga","Toronto","Ajax","Brampton")

    private var isSelected =  arrayOf(true,true,true,true,true)


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
    fun getisSelected() : Array<Boolean>{
        return isSelected
    }
    fun getFruitList(): JSONArray {
        return Fruit_List
    }
    fun getFruitCat(): Array<String>{
        return Fruit_Cat
    }

}
