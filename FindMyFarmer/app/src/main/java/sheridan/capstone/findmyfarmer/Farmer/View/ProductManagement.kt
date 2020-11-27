package sheridan.capstone.findmyfarmer.Farmer.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.fragment_product_management.*
import org.json.JSONArray
import org.json.JSONException
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.R
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class ProductManagement : Fragment() {
    private lateinit var productName: TextView
    private lateinit var productCategory: TextView
    private lateinit var requestQueue: RequestQueue



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lateinit var context: Context

        requestQueue= Volley.newRequestQueue(context)
        val view: View = inflater.inflate(R.layout.fragment_product_management, container, false)
        productName = view.findViewById(R.id.player)
        storeAPIDataintoDB()


        // Inflate the layout for this fragment
        return view
    }

    private fun storeAPIDataintoDB(){
        val c= DatabaseAPIHandler(context)
       val productList = ArrayList<Product>()
        val categories = listOf<String>("Fruits","Vegetables","Rice","Grain","Meat","Fish","Kosher","Halal","Vegan")

        //api keys
        val apiKey ="87cbc6eb7d3548bd9b95d1f715621c20"
        val url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"
        var productlist: JSONArray

        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response -> try{
            productlist = response.getJSONArray("results")

            for (i in 0..productlist.length()){
                val produce = productlist.getJSONObject(i)

                val id = produce.getInt("id")
                // val img = produce.getString("image")
                val pName = produce.getString("name")

                productName.append(pName)
                println(productName)
                productCategory.append("")

                //   convertStringIntoLoad(img)
                //Fruit_Image.getso
                //uploads certain values to db
                productList += Product(id, pName, categories[Math.random().toInt() * (categories.size - 0) + 1])

            }
            c.execute("/addProducts",productlist)

        } catch (e: JSONException){
            e.printStackTrace()
        }
            return@Listener
        }, { error -> error.printStackTrace() })

        //after setting up json object, requests call to api
        requestQueue?.add(req)
    }
}