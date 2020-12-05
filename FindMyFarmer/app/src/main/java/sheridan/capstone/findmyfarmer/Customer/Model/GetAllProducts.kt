package sheridan.capstone.findmyfarmer.Customer.Model

import android.app.Activity
import android.widget.Toast
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.FarmProduct
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FruitListToView

class GetAllProducts(private val activity: Activity,private val adapter: FruitListToView) {

    fun farmProducts(products: ArrayList<Product>,id: Int)
    {
        products.clear()
        DatabaseAPIHandler(activity, AsyncResponse{
            var AllProducts = ObjectConverter.convertStringToObject(it, Product::class.java,true) as List<*>
            for (product in AllProducts){
                var Productlistdata = product as Product
                DatabaseAPIHandler(activity, AsyncResponse {resp ->
                    if(!(resp.isNullOrBlank())){
                        var farmProduct = ObjectConverter.convertStringToObject(resp,
                            FarmProduct::class.java,false) as FarmProduct
                        if (farmProduct != null){
                            Productlistdata.quantity = farmProduct.quantity
                            products.add(Productlistdata)
                            //notifying change on list
                            adapter.notifyDataSetChanged()
                        }
                    }
                    else{
                        Toast.makeText(activity,"No Farm Product found!", Toast.LENGTH_SHORT).show()
                    }
                }).execute("/FarmProductByFarmIDAndProductID/${id}/${product.productID}")
            }
        }).execute("/ProductsByFarmID/${id}")
    }
}
