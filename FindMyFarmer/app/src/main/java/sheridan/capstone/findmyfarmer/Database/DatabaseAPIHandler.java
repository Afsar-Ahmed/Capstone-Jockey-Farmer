package sheridan.capstone.findmyfarmer.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import sheridan.capstone.findmyfarmer.Entities.Customer;
<<<<<<< HEAD
import sheridan.capstone.findmyfarmer.Entities.Farmer;
import sheridan.capstone.findmyfarmer.Entities.Product;

public class DatabaseAPIHandler extends AsyncTask<Object,Object,String> {

    private static final String API_BASE_URL = "http://farmerdb.us-east-2.elasticbeanstalk.com";
    private Context context;

    public DatabaseAPIHandler(Context context){ this.context = context; }

    //Adding Single instance of Customer, Product or Farmer
=======
import sheridan.capstone.findmyfarmer.Entities.Farm;
import sheridan.capstone.findmyfarmer.Entities.FarmProduct;
import sheridan.capstone.findmyfarmer.Entities.Farmer;
import sheridan.capstone.findmyfarmer.Entities.Following;
import sheridan.capstone.findmyfarmer.Entities.Product;
import sheridan.capstone.findmyfarmer.Entities.Rating;

public class DatabaseAPIHandler extends AsyncTask<Object,Object,String> {

    private static final String API_BASE_URL = "http://farmerapi.us-east-2.elasticbeanstalk.com";
    private Context context;
    public AsyncResponse del = null;

    public DatabaseAPIHandler(Context context, AsyncResponse asyncResponse)
    {
        this.context = context;
        del = asyncResponse;
    }

    //Adding Single instance of Farmers,Farms,Products,Customers,FarmProducts
>>>>>>> Sohaib
    private JSONObject AddCustomer(Customer customer){
        Map<String, String> params = new HashMap();

        params.put("CustomerName", customer.getCustomerName());
        params.put("CustomerEmail",customer.getCustomerEmail());
        params.put("CustomerPassword", customer.getCustomerPassword());
<<<<<<< HEAD
=======
        params.put("IsFarmer",String.valueOf(customer.getIsFarmer()));
>>>>>>> Sohaib

        return new JSONObject(params);
    }
    private JSONObject AddFarmer(Farmer farmer){
        Map<String, String> params = new HashMap();

<<<<<<< HEAD
        params.put("BusinessName", farmer.getBusinessName());
        params.put("BusinessDescription", farmer.getBusinessDescription());
        params.put("BusinessRating", String.valueOf(farmer.getBusinessRating()));
        params.put("ProductID", String.valueOf(farmer.getProductID()));
=======
        params.put("CustomerID", String.valueOf(farmer.getCustomerID()));
>>>>>>> Sohaib

        return new JSONObject(params);
    }
    private JSONObject AddProduct(Product product){
        Map<String, String> params = new HashMap();

        params.put("ProductName", product.getProductName());
        params.put("ProductCategory", product.getProductCategory());

        return new JSONObject(params);
    }
<<<<<<< HEAD

    //Updating
    private JSONObject UpdateCustomer(Customer customer){
        Map<String, String> params = new HashMap();

        params.put("CustomerID",String.valueOf(customer.getCustomerID()));
        params.put("CustomerName", customer.getCustomerName());
        params.put("CustomerEmail",customer.getCustomerEmail());
        params.put("CustomerPassword", customer.getCustomerPassword());

        return new JSONObject(params);
    }
    private JSONObject UpdateFarmer(Farmer farmer){
        Map<String, String> params = new HashMap();

        params.put("FarmerID",String.valueOf(farmer.getFarmerID()));
        params.put("BusinessName", farmer.getBusinessName());
        params.put("BusinessDescription", farmer.getBusinessDescription());
        params.put("BusinessRating", String.valueOf(farmer.getBusinessRating()));
        params.put("ProductID", String.valueOf(farmer.getProductID()));

        return new JSONObject(params);
    }
    private JSONObject UpdateProduct(Product product){
        Map<String, String> params = new HashMap();

        params.put("ProductID",String.valueOf(product.getProductID()));
        params.put("ProductName", product.getProductName());
        params.put("ProductCategory", product.getProductCategory());
=======
    private JSONObject AddFarmProduct(FarmProduct farmProduct){
        Map<String, String> params = new HashMap();

        params.put("FarmID", String.valueOf(farmProduct.getFarmerID()));
        params.put("ProductID", String.valueOf(farmProduct.getProductID()));
        params.put("Quantity",String.valueOf(farmProduct.getQuantity()));

        return new JSONObject(params);
    }
    private JSONObject AddFarm(Farm farm){
        Map<String, String> params = new HashMap();

        params.put("Businsess_Name",farm.getBusinessName());
        params.put("Business_Description", farm.getBusinessDescription());
        params.put("Business_Rating",String.valueOf(farm.getBusinessRating()));
        params.put("City",farm.getCity());
        params.put("Street",farm.getStreet());
        params.put("Country",farm.getCountry());
        params.put("PostalCode",farm.getPostalCode());
        params.put("Unit",String.valueOf(farm.getUnit()));
        params.put("FarmerID",String.valueOf(farm.getFarmerID()));

        return new JSONObject(params);
    }
    private JSONObject AddRating(Rating rating){
        Map<String, String> params = new HashMap();

        params.put("FarmID",String.valueOf(rating.getFarmID()));
        params.put("CustomerID", String.valueOf(rating.getCustomerID()));
        params.put("Rating",String.valueOf(rating.getRating()));
        params.put("Feedback",rating.getFeedback());
        return new JSONObject(params);
    }
    private JSONObject AddFollow(Following following){
        Map<String, String> params = new HashMap();

        params.put("CustomerID",String.valueOf(following.getCustomerID()));
        params.put("FarmID", String.valueOf(following.getFarmID()));
>>>>>>> Sohaib

        return new JSONObject(params);
    }

<<<<<<< HEAD
    //Adding List of Farmers, Products or Customers
=======
    //Adding List of Farmers,Farms,Products,Customers,FarmProducts
>>>>>>> Sohaib
    private JSONArray AddCustomers(List<Customer> customers) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Customer customer : customers) {
                JSONObject params = new JSONObject();

                params.put("CustomerName", customer.getCustomerName());
                params.put("CustomerEmail", customer.getCustomerEmail());
                params.put("CustomerPassword", customer.getCustomerPassword());
<<<<<<< HEAD
=======
                params.put("IsFarmer",customer.getIsFarmer());
>>>>>>> Sohaib

                jsonArray.put(params);
            }
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }
    private JSONArray AddFarmers(List<Farmer> farmers) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Farmer farmer : farmers) {
                JSONObject params = new JSONObject();

<<<<<<< HEAD
                params.put("BusinessName", farmer.getBusinessName());
                params.put("BusinessDescription", farmer.getBusinessDescription());
                params.put("BusinessRating", farmer.getBusinessRating());
                params.put("ProductID", farmer.getProductID());
=======
                params.put("CustomerID", String.valueOf(farmer.getCustomerID()));
>>>>>>> Sohaib

                jsonArray.put(params);
            }
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }
    private JSONArray AddProducts(List<Product> products) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Product product : products) {
                JSONObject params = new JSONObject();

                params.put("ProductName", product.getProductName());
                params.put("ProductCategory", product.getProductCategory());

                jsonArray.put(params);
            }
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }
<<<<<<< HEAD

=======
    private JSONArray AddFarmProducts(List<FarmProduct> farmProducts){
        JSONArray jsonArray = new JSONArray();
        try {
            for (FarmProduct farmProduct : farmProducts) {
                JSONObject params = new JSONObject();

                params.put("FarmID", farmProduct.getFarmerID());
                params.put("ProductID", farmProduct.getProductID());

                jsonArray.put(params);
            }
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }
    private JSONArray AddFarms(List<Farm> farms){
        JSONArray jsonArray = new JSONArray();
        try {
            for (Farm farm : farms) {
                JSONObject params = new JSONObject();

                params.put("Businsess_Name",farm.getBusinessName());
                params.put("Business_Description", farm.getBusinessDescription());
                params.put("Business_Rating",String.valueOf(farm.getBusinessRating()));
                params.put("City",farm.getCity());
                params.put("Street",farm.getStreet());
                params.put("Country",farm.getCountry());
                params.put("PostalCode",farm.getPostalCode());
                params.put("Unit",String.valueOf(farm.getUnit()));
                params.put("FarmerID",String.valueOf(farm.getFarmerID()));

                jsonArray.put(params);
            }
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    private JSONArray AddRatings(List<Rating> ratings){
        JSONArray jsonArray = new JSONArray();
        try {
            for (Rating rating : ratings) {
                JSONObject params = new JSONObject();

                params.put("FarmID",String.valueOf(rating.getFarmID()));
                params.put("CustomerID", String.valueOf(rating.getCustomerID()));
                params.put("Rating",String.valueOf(rating.getRating()));
                params.put("Feedback",rating.getFeedback());

                jsonArray.put(params);
            }
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    private JSONArray AddFollows(List<Following> followings){
        JSONArray jsonArray = new JSONArray();
        try {
            for (Following following : followings) {
                JSONObject params = new JSONObject();

                params.put("CustomerID",String.valueOf(following.getCustomerID()));
                params.put("FarmID", String.valueOf(following.getFarmID()));

                jsonArray.put(params);
            }
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }


    //Updating Farmers,Farms,Products,Customers,FarmProducts
    private JSONObject UpdateCustomer(Customer customer){
        Map<String, String> params = new HashMap();

        params.put("CustomerID",String.valueOf(customer.getCustomerID()));
        params.put("CustomerName", customer.getCustomerName());
        params.put("CustomerEmail",customer.getCustomerEmail());
        params.put("CustomerPassword", customer.getCustomerPassword());
        params.put("IsFarmer",String.valueOf(customer.getIsFarmer()));

        return new JSONObject(params);
    }
    private JSONObject UpdateFarm(Farm farm){
        Map<String, String> params = new HashMap();

        params.put("FarmID",String.valueOf(farm.getFarmID()));
        params.put("Businsess_Name",farm.getBusinessName());
        params.put("Business_Description", farm.getBusinessDescription());
        params.put("Business_Rating",String.valueOf(farm.getBusinessRating()));
        params.put("City",farm.getCity());
        params.put("Street",farm.getStreet());
        params.put("Country",farm.getCountry());
        params.put("PostalCode",farm.getPostalCode());
        params.put("Unit",String.valueOf(farm.getUnit()));
        params.put("FarmerID",String.valueOf(farm.getFarmerID()));

        return new JSONObject(params);
    }
    private JSONObject UpdateProduct(Product product){
        Map<String, String> params = new HashMap();

        params.put("ProductID",String.valueOf(product.getProductID()));
        params.put("ProductName", product.getProductName());
        params.put("ProductCategory", product.getProductCategory());

        return new JSONObject(params);
    }
>>>>>>> Sohaib

    //API calls handled for a list of objects
    private String SendListRequest(String url, List<?> objects){
        JSONArray listArray = new JSONArray();
        if(!(objects.isEmpty())){
            if(objects.get(0).getClass() == Customer.class){
                listArray = AddCustomers((List<Customer>) objects);
            }
            else if(objects.get(0).getClass() ==  Farmer.class){
                listArray = AddFarmers((List<Farmer>) objects);
            }
            else if(objects.get(0).getClass() == Product.class){
                listArray = AddProducts((List<Product>) objects);
            }
<<<<<<< HEAD
=======
            else if(objects.get(0).getClass() == FarmProduct.class){
                listArray = AddFarmProducts((List<FarmProduct>) objects);
            }
            else if(objects.get(0).getClass() == Farm.class){
                listArray = AddFarms((List<Farm>)objects);
            }
            else if(objects.get(0).getClass() == Rating.class){
                listArray = AddRatings((List<Rating>)objects);
            }
            else if(objects.get(0).getClass() == Following.class){
                listArray = AddFollows((List<Following>)objects);
            }
>>>>>>> Sohaib
        }
        try {
            RequestQueue rq = Volley.newRequestQueue(context);
            RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, listArray, requestFuture, requestFuture);
            rq.add(request);

<<<<<<< HEAD
            String response = requestFuture.get(5, TimeUnit.SECONDS).toString();
            System.out.println(response);
=======
            String response = requestFuture.get().toString();
            Thread.sleep(1000);
>>>>>>> Sohaib
            return response;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }
    //API calls handled for Single instance of object
    private String SendSingleObjectRequest(int method,String url,Object object){
        JSONObject obj = new JSONObject();
<<<<<<< HEAD
=======
        //updating/deleting objects from database
>>>>>>> Sohaib
        if(method == Request.Method.PUT){
            if(object.getClass() == Customer.class){
                obj = UpdateCustomer((Customer) object);
            }
            else if(object.getClass() == Farmer.class){
<<<<<<< HEAD
                obj = UpdateFarmer((Farmer) object);
=======
                obj = UpdateFarm((Farm) object);
>>>>>>> Sohaib
            }
            else if(object.getClass() == Product.class){
                obj = UpdateProduct((Product) object);
            }
<<<<<<< HEAD
=======
            else if(object.getClass() == Farm.class){
                obj = UpdateFarm((Farm) object);
            }
            else{
                return null;
            }
>>>>>>> Sohaib
        }
        else{
            if(object.getClass() == Customer.class){
                obj = AddCustomer((Customer) object);
            }
            else if(object.getClass() == Farmer.class){
                obj = AddFarmer((Farmer) object);
            }
            else if(object.getClass() == Product.class){
                obj = AddProduct((Product) object);
            }
<<<<<<< HEAD
=======
            else if(object.getClass() == FarmProduct.class){
                obj = AddFarmProduct((FarmProduct) object);
            }
            else if(object.getClass() == Farm.class){
                obj = AddFarm((Farm) object);
            }
            else if(object.getClass() == Rating.class){
                obj = AddRating((Rating) object);
            }
            else if(object.getClass() == Following.class){
                obj = AddFollow((Following) object);
            }
            else{
                return null;
            }
>>>>>>> Sohaib
        }

        try {
            RequestQueue rq = Volley.newRequestQueue(context);
            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(method, url, obj, requestFuture, requestFuture);
            rq.add(request);

<<<<<<< HEAD
            String response = requestFuture.get(5, TimeUnit.SECONDS).toString();
            System.out.println(response);
=======
            String response = requestFuture.get().toString();
>>>>>>> Sohaib
            return response;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
<<<<<<< HEAD

    //API calls for a simgle get request
    private String SendPlainGetRequest(int method,String url){
=======
    //API calls for a simple get request
    private String SendPlainRequest(int method, String url){
>>>>>>> Sohaib
        try {
            RequestQueue rq = Volley.newRequestQueue(context);
            RequestFuture<String> requestFuture = RequestFuture.newFuture();
            StringRequest request = new StringRequest(method, url, requestFuture, requestFuture);
            rq.add(request);

<<<<<<< HEAD
            String response = requestFuture.get(5, TimeUnit.SECONDS);
            System.out.println(response);
=======
            String response = requestFuture.get(5,TimeUnit.SECONDS);
>>>>>>> Sohaib
            return response;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    //Api Call function
    //Params[0] = URL , Params[1] = requirement of the URL or null otherwise
    @Override
    protected String doInBackground(Object... objects) {
        String paramtype = "";
        List<?> list = null;
<<<<<<< HEAD
=======
        String response = "";
>>>>>>> Sohaib
        try{
            String url = API_BASE_URL + objects[0].toString();

            //Checks if the objects passed are null or not
            if(objects.length > 1){
                //checks if the passed object is a list or single object
                try{
                    list = (List<?>) objects[1];
                    paramtype = "list".toLowerCase();
                }catch (Exception ex){ }

                //if it is a list
                if(paramtype.compareToIgnoreCase("list")==0){
<<<<<<< HEAD
                    SendListRequest(url,list);
                }
                else{
                    if(objects[0].toString().contains("update")){
                        SendSingleObjectRequest(Request.Method.PUT,url,objects[1]);
                    }
                    else if(objects[0].toString().contains("add")){
                        SendSingleObjectRequest(Request.Method.POST,url,objects[1]);
=======
                   response = SendListRequest(url,list);
                }
                else{
                    if(objects[0].toString().toLowerCase().contains("update")){
                        response = SendSingleObjectRequest(Request.Method.PUT,url,objects[1]);
                    }
                    else if(objects[0].toString().toLowerCase().contains("add")){
                        response = SendSingleObjectRequest(Request.Method.POST,url,objects[1]);
>>>>>>> Sohaib
                    }
                }
            }
            else{
<<<<<<< HEAD
                //Function for only url calls GET
                if(!(objects[0].toString().contains("delete"))){
                    SendPlainGetRequest(Request.Method.GET,url);
                }
                else{
                    SendPlainGetRequest(Request.Method.PUT,url);
                }

            }

            return "";
=======
                if(!(objects[0].toString().toLowerCase().contains("delete"))){
                    response = SendPlainRequest(Request.Method.GET,url);
                }
                else{
                    //PUT request for deleting
                    response = SendPlainRequest(Request.Method.PUT,url);
                }
            }
            return response;
>>>>>>> Sohaib
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
<<<<<<< HEAD
}
=======

    @Override
    protected void onPostExecute(String s) {
        del.processFinish(s);
    }
}

>>>>>>> Sohaib
