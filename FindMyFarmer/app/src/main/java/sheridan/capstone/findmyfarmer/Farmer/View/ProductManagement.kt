package sheridan.capstone.findmyfarmer.Farmer.View

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
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
import java.io.File
import java.nio.file.Files
import java.util.*
import kotlin.collections.ArrayList


class ProductManagement : Fragment() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var fh:FirebaseImagehandler
    private lateinit var file:File
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(R.layout.fragment_product_management, container, false)
        requestQueue= Volley.newRequestQueue(activity?.applicationContext)
        fh = FirebaseImagehandler(DirectoryName.Farm,1,activity?.applicationContext)

        storeAPIDataintoDB(view)


        // Inflate the layout for this fragment
        return view
    }

   @RequiresApi(Build.VERSION_CODES.O)
   private fun storeAPIDataintoDB(view: View){
       lateinit var byteArray:ByteArray

        var randomCategory: String

        val productName = view.findViewById<TextView>(R.id.nME)
        val productCategory= view.findViewById<TextView>(R.id.produce_cate)

        //Lists and objects
       val productList = ArrayList<Product>()
        val categories = listOf<String>("Fruits","Vegetables","Rice","Grain","Meat","Fish","Kosher","Halal","Vegan")

        //api keys & JSON
        val apiKey ="87cbc6eb7d3548bd9b95d1f715621c20"
        val url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"
       var productlist= JSONArray()


        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response -> try{
         val c =  DatabaseAPIHandler(activity?.applicationContext, AsyncResponse {"yay"})
            productlist = response.getJSONArray("results")

            for (i in 0 until productlist.length()){
                val produce = productlist.getJSONObject(i)

                val id = produce.getInt("id")

                val img = produce.get("image")
                val pName = produce.getString("name")

                productName.text=pName
                file = File(img.toString())
                byteArray= Files.readAllBytes(file.toPath())

                randomCategory = categories[Math.random().toInt() * (categories.size - 0) + 1]
                productCategory.text=randomCategory
                //converts image to Bitmap then uploads to view
              /* var convtoBit = Base64.decode(byteArray,1)
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
                })*/



                //uploads certain values to db
                productList += Product(id, pName, randomCategory)

            }
            c.execute("/addProducts",productlist)

        } catch (e: JSONException){
            e.printStackTrace()
        }
            return@Listener
        }, { error -> error.printStackTrace() })

        //after setting up json object, requests call to api
       requestQueue.add(req)
    }


}


