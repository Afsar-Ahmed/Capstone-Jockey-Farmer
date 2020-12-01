package sheridan.capstone.findmyfarmer.Farmer.View

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
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
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.fragment_product_management.*
import org.json.JSONArray
import org.json.JSONException
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.R
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_account_settings.*
import kotlinx.android.synthetic.main.farmer_info_card.*
import sheridan.capstone.findmyfarmer.Database.AsyncResponse
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import java.util.*
import kotlin.collections.ArrayList


class ProductManagement : Fragment() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var fh:FirebaseImagehandler
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        requestQueue= Volley.newRequestQueue(activity?.applicationContext)
        val view: View = inflater.inflate(R.layout.fragment_product_management, container, false)

        storeAPIDataintoDB(view)



        // Inflate the layout for this fragment
        return view
    }

   private fun storeAPIDataintoDB(view: View){
       fh = FirebaseImagehandler(DirectoryName.Farm,1,activity?.applicationContext)


        var randomCategory: String

        val productName = view.findViewById<TextView>(R.id.nME)
        val productCategory= view.findViewById<TextView>(R.id.produce_cate)

        //Lists and objects
       val productList = ArrayList<Product>()
        val categories = listOf<String>("Fruits","Vegetables","Rice","Grain","Meat","Fish","Kosher","Halal","Vegan")

        //api keys & JSON
        val apiKey ="87cbc6eb7d3548bd9b95d1f715621c20"
        val url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"
        lateinit var productlist: JSONArray


        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response -> try{
           DatabaseAPIHandler(activity?.applicationContext, AsyncResponse {
            productlist = response.getJSONArray("results")

            for (i in 0..productlist.length()){
                val produce = productlist.getJSONObject(i)

                val id = produce.getInt("id")

                val img = produce.get("image")
                //converts image to Bitmap then uploads to view
                val convtoBit = Base64.decode(img.toString(),0)
                val image = BitmapFactory.decodeByteArray(convtoBit, 0, convtoBit.size)

                fh.UploadImageToFirebase(image,object:StorageResponse{

                    override fun processFinish(
                        response: MutableList<StorageReference>?,
                        bitmap: Optional<Bitmap>?,
                        Url: Optional<String>?
                    ) {
                    }

                    override fun OnErrorListener(error: String?) {
                        print(error)
                    }
                })

                val pName = produce.getString("name")

                productName.text=pName

                //productImage.setImageBitmap(image)

                randomCategory = categories[Math.random().toInt() * (categories.size - 0) + 1]
                productCategory.text=randomCategory

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


