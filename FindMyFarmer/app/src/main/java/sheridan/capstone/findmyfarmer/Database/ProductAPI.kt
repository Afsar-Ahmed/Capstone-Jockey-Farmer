package sheridan.capstone.findmyfarmer.Database

import android.app.DownloadManager
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import sheridan.capstone.findmyfarmer.Entities.Product

class ProductAPI {

    val apiKey:String="87cbc6eb7d3548bd9b95d1f715621c20"
    var url:String=""
    var product:Product?=null
    private var requestQueue: RequestQueue? = null

    fun ProductAPI(){

    }

     fun apiLOAD(): JSONArray {
         url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"
         var productlist = JSONArray()
        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
            response -> try{
            productlist = response.getJSONArray("results")

//            for (i in 1..productlist.length()){
//                var produce = productlist.getJSONObject(i)
//
//                var img = produce.getString("image")
//                var productName = produce.getString("name")
//
//            }

        return@Listener
        } catch (e: JSONException){
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })

         return productlist
    }


    fun apiSearch(){
        url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"

      var req = JsonObjectRequest(Request.Method.GET,url,null, {
              response -> try{
          var searchProducts = response.getJSONArray("results")

//          for (i in 1..searchProducts.length()){
//              var searchProduce = searchProducts.getJSONObject(i)
//
//              var img = searchProduce.getString("image")
//              var productName = searchProduce.getString("name")
//          }

      }catch (e: JSONException)
      {e.printStackTrace()}  },
          {  })
    }
}