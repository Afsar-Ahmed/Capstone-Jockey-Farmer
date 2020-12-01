package sheridan.capstone.findmyfarmer.Customer.Model

import android.app.Activity
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView

class GetAllProducts(private val activity: Activity,private val adapter: FruitListToView) {

    fun farmProducts(products: ArrayList<Product>,id: Int){
        products.clear()
        DatabaseAPIHandler(activity, AsyncResponse {
            var productlist = ObjectConverter.convertStringToObject(it,Product::class.java,true) as List<*>

            for (product in productlist){
                var prod = product as Product
                products.add(prod)
            }
            adapter.notifyDataSetChanged()
        }).execute("/ProductsByFarmID/${id}")
    }
}