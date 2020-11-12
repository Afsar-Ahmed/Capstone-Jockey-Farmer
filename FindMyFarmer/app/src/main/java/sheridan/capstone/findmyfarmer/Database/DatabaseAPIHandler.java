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
import sheridan.capstone.findmyfarmer.Entities.Farmer;
import sheridan.capstone.findmyfarmer.Entities.Product;

public class DatabaseAPIHandler extends AsyncTask<Object,Object,String> {

    private static final String API_BASE_URL = "http://farmerdb.us-east-2.elasticbeanstalk.com";
    private Context context;

    public DatabaseAPIHandler(Context context){ this.context = context; }

    //Adding Single instance of Customer, Product or Farmer
    private JSONObject AddCustomer(Customer customer){
        Map<String, String> params = new HashMap();

        params.put("CustomerName", customer.getCustomerName());
        params.put("CustomerEmail",customer.getCustomerEmail());
        params.put("CustomerPassword", customer.getCustomerPassword());

        return new JSONObject(params);
    }
    private JSONObject AddFarmer(Farmer farmer){
        Map<String, String> params = new HashMap();

        params.put("BusinessName", farmer.getBusinessName());
        params.put("BusinessDescription", farmer.getBusinessDescription());
        params.put("BusinessRating", String.valueOf(farmer.getBusinessRating()));
        params.put("ProductID", String.valueOf(farmer.getProductID()));

        return new JSONObject(params);
    }
    private JSONObject AddProduct(Product product){
        Map<String, String> params = new HashMap();

        params.put("ProductName", product.getProductName());
        params.put("ProductCategory", product.getProductCategory());

        return new JSONObject(params);
    }

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

        return new JSONObject(params);
    }

    //Adding List of Farmers, Products or Customers
    private JSONArray AddCustomers(List<Customer> customers) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Customer customer : customers) {
                JSONObject params = new JSONObject();

                params.put("CustomerName", customer.getCustomerName());
                params.put("CustomerEmail", customer.getCustomerEmail());
                params.put("CustomerPassword", customer.getCustomerPassword());

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

                params.put("BusinessName", farmer.getBusinessName());
                params.put("BusinessDescription", farmer.getBusinessDescription());
                params.put("BusinessRating", farmer.getBusinessRating());
                params.put("ProductID", farmer.getProductID());

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
        }
        try {
            RequestQueue rq = Volley.newRequestQueue(context);
            RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, listArray, requestFuture, requestFuture);
            rq.add(request);

            String response = requestFuture.get(5, TimeUnit.SECONDS).toString();
            System.out.println(response);
            return response;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }
    //API calls handled for Single instance of object
    private String SendSingleObjectRequest(int method,String url,Object object){
        JSONObject obj = new JSONObject();
        if(method == Request.Method.PUT){
            if(object.getClass() == Customer.class){
                obj = UpdateCustomer((Customer) object);
            }
            else if(object.getClass() == Farmer.class){
                obj = UpdateFarmer((Farmer) object);
            }
            else if(object.getClass() == Product.class){
                obj = UpdateProduct((Product) object);
            }
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
        }

        try {
            RequestQueue rq = Volley.newRequestQueue(context);
            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(method, url, obj, requestFuture, requestFuture);
            rq.add(request);

            String response = requestFuture.get(5, TimeUnit.SECONDS).toString();
            System.out.println(response);
            return response;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    //API calls for a simgle get request
    private String SendPlainGetRequest(int method,String url){
        try {
            RequestQueue rq = Volley.newRequestQueue(context);
            RequestFuture<String> requestFuture = RequestFuture.newFuture();
            StringRequest request = new StringRequest(method, url, requestFuture, requestFuture);
            rq.add(request);

            String response = requestFuture.get(5, TimeUnit.SECONDS);
            System.out.println(response);
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
                    SendListRequest(url,list);
                }
                else{
                    if(objects[0].toString().contains("update")){
                        SendSingleObjectRequest(Request.Method.PUT,url,objects[1]);
                    }
                    else if(objects[0].toString().contains("add")){
                        SendSingleObjectRequest(Request.Method.POST,url,objects[1]);
                    }
                }
            }
            else{
                //Function for only url calls GET
                if(!(objects[0].toString().contains("delete"))){
                    SendPlainGetRequest(Request.Method.GET,url);
                }
                else{
                    SendPlainGetRequest(Request.Method.PUT,url);
                }

            }

            return "";
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
}
