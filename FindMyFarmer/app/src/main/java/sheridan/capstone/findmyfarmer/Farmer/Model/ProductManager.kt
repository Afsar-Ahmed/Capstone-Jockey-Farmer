package sheridan.capstone.findmyfarmer.Farmer.Model

import android.app.Activity
import android.graphics.Bitmap
import android.os.Build
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.storage.StorageReference
import org.json.JSONArray
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Database.ObjectConverter
import sheridan.capstone.findmyfarmer.Entities.FarmProduct
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FarmerFruitListToView
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import java.util.*
import kotlin.collections.ArrayList

class ProductManager(val activity: Activity) {

    fun GetProductCategoryList(categoryList: ArrayList<String>, adapter: ArrayAdapter<String>) {
        DatabaseAPIHandler(activity, AsyncResponse {
            if(!(it.isNullOrBlank())){
                categoryList.clear()
                var jsonArray = JSONArray(it)
                if(jsonArray != null){
                    for(x in 0..(jsonArray.length()-1)){
                        categoryList.add(jsonArray.getString(x))
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            else{
                Toast.makeText(activity,"No products",Toast.LENGTH_SHORT).show()
            }
        }).execute("/getProductList")
    }

    fun GetEditProducts(FarmProducts: ArrayList<Product> ,id: Int,Fruitadapter: FarmerFruitListToView){
        DatabaseAPIHandler(activity, AsyncResponse{
            FarmProducts.clear()
            var AllProducts = ObjectConverter.convertStringToObject(it, Product::class.java,true) as List<*>
            for (product in AllProducts){
                var Productlistdata = product as Product
                var FIH = FirebaseImagehandler(DirectoryName.Product,5180,activity)
                FIH.GetImageURLFromFirebase(product.productName,object : StorageResponse {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun processFinish(response: MutableList<StorageReference>?, bitmap: Optional<Bitmap>?, Url: Optional<String>?) {
                        if(Url != null && !(Url.get().isNullOrBlank())){
                            Productlistdata.image = Url.get()
                            DatabaseAPIHandler(activity, AsyncResponse {resp ->
                                if(!(resp.isNullOrBlank())){
                                    var farmProduct = ObjectConverter.convertStringToObject(resp,FarmProduct::class.java,false) as FarmProduct
                                    if (farmProduct != null){
                                        Productlistdata.quantity = farmProduct.quantity
                                        FarmProducts.add(Productlistdata)
                                        //notifying change on list
                                        Fruitadapter.notifyDataSetChanged()
                                    }
                                }
                                else{
                                    Toast.makeText(activity,"No Farm Product found!",Toast.LENGTH_SHORT).show()
                                }
                            }).execute("/FarmProductByFarmIDAndProductID/${id}/${product.productID}")
                        }
                        else{
                            DatabaseAPIHandler(activity, AsyncResponse {resp ->
                                if(!(resp.isNullOrBlank())){
                                    var farmProduct = ObjectConverter.convertStringToObject(resp,FarmProduct::class.java,false) as FarmProduct
                                    if (farmProduct != null){
                                        Productlistdata.quantity = farmProduct.quantity
                                        FarmProducts.add(Productlistdata)
                                        //notifying change on list
                                        Fruitadapter.notifyDataSetChanged()
                                    }
                                }
                                else{
                                    Toast.makeText(activity,"No Farm Product found!",Toast.LENGTH_SHORT).show()
                                }
                            }).execute("/FarmProductByFarmIDAndProductID/${id}/${product.productID}")
                        }
                    }
                    override fun OnErrorListener(error: String?) {
                        DatabaseAPIHandler(activity, AsyncResponse {resp ->
                            if(!(resp.isNullOrBlank())){
                                var farmProduct = ObjectConverter.convertStringToObject(resp,FarmProduct::class.java,false) as FarmProduct
                                if (farmProduct != null){
                                    Productlistdata.quantity = farmProduct.quantity
                                    FarmProducts.add(Productlistdata)
                                    //notifying change on list
                                    Fruitadapter.notifyDataSetChanged()
                                }
                            }
                            else{
                                Toast.makeText(activity,"No Farm Product found!",Toast.LENGTH_SHORT).show()
                            }
                        }).execute("/FarmProductByFarmIDAndProductID/${id}/${product.productID}")                    }
                })
            }
        }).execute("/ProductsByFarmID/${id}")
    }

    fun GetTypeOfCategory(TypeList: ArrayList<String>, category: String,adapter: ArrayAdapter<String>){
        DatabaseAPIHandler(activity, AsyncResponse {
            if(!(it.isNullOrBlank())){
                var productlist = ObjectConverter.convertStringToObject(it,Product::class.java,true) as List<*>
                if(productlist != null){
                    TypeList.clear()
                    for (product in productlist){
                        var prod = product as Product
                        if(prod.productCategory.compareTo(category,true) == 0){
                            TypeList.add(prod.productName)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }).execute("/Products")

    }

    fun AddFarmProduct(name: String,category: String,Farmid: Int,FarmProducts: ArrayList<Product>,Fruitadapter: FarmerFruitListToView){
        DatabaseAPIHandler(activity, AsyncResponse {
            if(!(it.isNullOrBlank())){
                var farmProduct = FarmProduct(1,Farmid,it.toInt(),0)
                DatabaseAPIHandler(activity, AsyncResponse {resp ->
                    if(!(resp.isNullOrBlank())){
                        GetEditProducts(FarmProducts,Farmid,Fruitadapter)
                    }
                    else{
                        Toast.makeText(activity,"Unable to add to List, this item might already exists in your inventory", Toast.LENGTH_SHORT).show()
                    }
                }).execute("/AddFarmProduct",farmProduct)
            }
            else{
                Toast.makeText(activity,"This Product does not exist", Toast.LENGTH_SHORT).show()
            }
        }).execute("/ProductIDByNameAndCategory/${name}/${category}")
    }

    fun UpdateQuantity(quantity: Int,productId: Int,farmid: Int,editText: EditText){
        DatabaseAPIHandler(activity, AsyncResponse {
            if(!(it.isNullOrBlank())){
                var farmProduct = ObjectConverter.convertStringToObject(it,FarmProduct::class.java,false) as FarmProduct
                if(farmProduct != null){
                    farmProduct.quantity = quantity
                    DatabaseAPIHandler(activity, AsyncResponse {resp ->
                        if(!(resp.isNullOrBlank())){
                            editText.setText("${quantity}")
                        }
                    }).execute("/UpdateFarmProductQuantity",farmProduct)
                }
            }else{
                Toast.makeText(activity,"Couldnt find this product in this farm's Inventory",Toast.LENGTH_SHORT).show()
            }
        }).execute("/FarmProductByFarmIDAndProductID/${farmid}/${productId}")
    }

    fun DeleteFarmProduct(productId: Int,farmid: Int,adapter: FarmerFruitListToView){
        DatabaseAPIHandler(activity, AsyncResponse {
            adapter.notifyDataSetChanged()
        }).execute("/deleteFarmProductByFarmIDAndProductID/${farmid}/${productId}")
    }
}