/**
 * @author: Afsar Ahmed
 */
package sheridan.capstone.findmyfarmer.Customer.View

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.R
import java.util.*
import kotlin.collections.ArrayList

class FarmerProducts : Fragment() {

    private lateinit var name: TextView
    private lateinit var category: TextView
    private lateinit var Pid: TextView
    private lateinit var quantity: TextView
    private lateinit var requestQueue: RequestQueue
    private lateinit var fh: FirebaseImagehandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.product_display_card, container, false)

        requestQueue= Volley.newRequestQueue(activity?.applicationContext)
        name = view.findViewById<TextView>(R.id.productN)
         category = view.findViewById<TextView>(R.id.productC)
        Pid = view.findViewById<TextView>(R.id.productI)
        quantity= view.findViewById<TextView>(R.id.productQ)

        loadData()
        
        // Inflate the layout for this fragment
        return view
    }

    /**
     * loads data into database and outputs json data into layout: fragment_produclist
     */
    private fun loadData(){
        fh = FirebaseImagehandler(DirectoryName.Customer,1,activity?.applicationContext)

        //Lists and objects
        var productlist: JSONArray
        val productList = ArrayList<Product>()
        val categories = listOf<String>("Fruits","Vegetables","Rice","Grain","Meat","Fish","Kosher","Halal","Vegan")

        //api keys & JSON
        val apiKey ="87cbc6eb7d3548bd9b95d1f715621c20"
        val url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"


        var randomCategory: String
        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response -> try{
            productlist = response.getJSONArray("results")

//this is the handler that sends the data to the database
   DatabaseAPIHandler(activity?.applicationContext, AsyncResponse {
    for (i in 0..productlist.length()) {
        //intialiszes json data into serpate variables
        val produce = productlist.getJSONObject(i)
        val id = produce.getInt("id")
        val img = produce.get("image")
        println(img)

        val convtoBit = Base64.decode(img.toString(),0)
        val image = BitmapFactory.decodeByteArray(convtoBit, 0, convtoBit.size)

        val pName = produce.getString("name")
        Pid.append("$id\n")
        name.append("$pName\n")


        //randomly selects a category from the categories list
        randomCategory = categories[Math.random().toInt() * (categories.size - 0) + 1]
        category.append("$randomCategory\n")


        //uploads certain values to db
        productList += Product(id, pName, randomCategory)

    }
}).execute("/addProducts",productlist)

        } catch (e: JSONException){
            e.printStackTrace()
        }
            return@Listener
        }, { error -> error.printStackTrace() })

        //after setting up json object, requests call to api
        requestQueue.add(req)
    }
}