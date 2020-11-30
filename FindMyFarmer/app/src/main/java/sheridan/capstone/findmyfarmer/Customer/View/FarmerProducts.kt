package sheridan.capstone.findmyfarmer.Customer.View

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.w3c.dom.Text
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import sheridan.capstone.findmyfarmer.R

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
    private fun loadData(){
        fh = FirebaseImagehandler(DirectoryName.Farm,1,activity?.applicationContext)

        //Lists and objects
        val productList = ArrayList<Product>()
        val categories = listOf<String>("Fruits","Vegetables","Rice","Grain","Meat","Fish","Kosher","Halal","Vegan")

        //api keys & JSON
        val apiKey ="87cbc6eb7d3548bd9b95d1f715621c20"
        val url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"
        var productlist: JSONArray

        var randomCategory: String
        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response -> try{
            productlist = response.getJSONArray("results")
    DatabaseAPIHandler(activity?.applicationContext, AsyncResponse {
    for (i in 0..productlist.length()) {
        val produce = productlist.getJSONObject(i)

        val id = produce.getInt("id")

        val img = produce.get("image")
        println(img)

        var convtoBit = Base64.decode(img.toString(),0)
        var image = BitmapFactory.decodeByteArray(convtoBit, 0, convtoBit.size)

        val pName = produce.getString("name")
        Pid.append("$id\n")
        name.append("$pName\n")

       // fh.UploadImageToFirebase(image,str))
        //productImage.setImageBitmap(image)

        randomCategory = categories[Math.random().toInt() * (categories.size - 0) + 1]
        category.append("$randomCategory\n")

        //   convertStringIntoLoad(img)

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