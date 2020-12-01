package sheridan.capstone.findmyfarmer.Farmer.Model

import android.content.Context
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView

class GetFarmProducts (val context: Context, val adapter: FruitListToView){

    public fun GetProducts(FarmProducts: ArrayList<Product> ,id: Int){
        DatabaseAPIHandler(context, AsyncResponse{
            FarmProducts.clear()
            var AllProducts = ObjectConverter.convertStringToObject(it, Product::class.java,true) as List<*>
            for (product in AllProducts){
                var Productlistdata = product as Product
                FarmProducts.add(Productlistdata)
                //notifying change on list
                adapter.notifyDataSetChanged()
            }
        }).execute("/ProductsByFarmID/${id}")
    }

}