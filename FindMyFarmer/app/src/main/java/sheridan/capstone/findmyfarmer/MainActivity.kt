package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.farmer_info_card.*
import kotlinx.android.synthetic.main.fragment_product_management.*
import org.json.JSONArray
import org.json.JSONException
import sheridan.capstone.findmyfarmer.Database.DatabaseAPIHandler
import sheridan.capstone.findmyfarmer.Entities.Customer
import sheridan.capstone.findmyfarmer.Entities.Farmer
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationController
import sheridan.capstone.findmyfarmer.Users.CustomerActivity


class MainActivity : AppCompatActivity() {
    var requestQueue: RequestQueue? = null
    private lateinit var pName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestQueue = Volley.newRequestQueue(this)

        //startActivity(Intent(this, LoginRegistrationController::class.java))
        //Log.d("Switched Activity", "Switched to the LoginRegistrationController")
        storeAPIDataintoDB()
        checkIfSignedInAccount()
        storeAPIDataintoDB()
    }

    //Checks if the user in signed in the account
    //if not - go to the sign in page. if yes - go to a different activity
    private fun checkIfSignedInAccount() {

        val user = Firebase.auth.currentUser
        if (user != null) {
            startActivity(Intent(this,
              CustomerActivity::class.java))

        } else {
            startActivity(Intent(this, LoginRegistrationController::class.java))
        }

        val c = DatabaseAPIHandler(this)
        val d = Customer(1,"Sobi5180","sobi@hotmail.ca","5180")
        val d1 = Customer(1,"Sobi","sobi5180@hotmail.ca","1234")
        val d2 = Customer(1,"Sobi","sobi5180@hotmail.ca","1234")

        val f = Farmer(1,"TestBus","Testsestes",10,14)
        val f1 = Farmer(1,"TestBus","Testsestes",10,16)
        val f2 = Farmer(1,"TestBus","Testsestes",10,12)

        val p = Product(1,"Rice","Grain")
        val p1 = Product(1,"Quinoa","Grain")
        val p2 = Product(1,"Carrot","Vegetable")

        val custlists = listOf<Customer>(d,d1,d2)
        val flist = listOf<Farmer>(f,f1,f2)
        val plist = listOf<Product>(p1,p2)

        //This how to call the API
       //c.execute("/addProducts",plist)
    }

    //requests access to api
    private fun storeAPIDataintoDB(){
        val c = DatabaseAPIHandler(this)
        val apiKey ="87cbc6eb7d3548bd9b95d1f715621c20"
        val url = "https://api.spoonacular.com/food/ingredients/search?apiKey=$apiKey&query=apple"

        pName = findViewById(R.id.product_name)

        var productlist: JSONArray
        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response -> try{
            productlist = response.getJSONArray("results")

            for (i in 0..productlist.length()){
                val produce = productlist.getJSONObject(i)

                val id = produce.getInt("id")
               // val img = produce.getString("image")
                val productName = produce.getString("name")

                pName.append(productName)
                produce_catergory.append("")

         //   convertStringIntoLoad(img)
        //Fruit_Image.getso
                //uploads certain values to db
                c.execute("/addProduct",Product(id,productName,""))

            }

        } catch (e: JSONException){
            e.printStackTrace()
        }
            return@Listener
        }, { error -> error.printStackTrace() })

        //after setting up json object, requests call to api
        requestQueue?.add(req)
    }


}