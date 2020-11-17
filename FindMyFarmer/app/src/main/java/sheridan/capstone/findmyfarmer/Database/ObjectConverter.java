package sheridan.capstone.findmyfarmer.Database;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sheridan.capstone.findmyfarmer.Entities.Address;
import sheridan.capstone.findmyfarmer.Entities.Customer;
import sheridan.capstone.findmyfarmer.Entities.Farmer;
import sheridan.capstone.findmyfarmer.Entities.FarmerProduct;
import sheridan.capstone.findmyfarmer.Entities.Product;

public class ObjectConverter {
    public static Object convertStringToObject(String response, Type cls, boolean listOrNot){

        if(listOrNot){
            List<Object> objectList = new ArrayList();
            try {
                JSONArray jsonArray = new JSONArray(response);
                for(int i = 0; i < jsonArray.length();i++){
                    String ob = jsonArray.getJSONObject(i).toString();
                    Object listob = new Gson().fromJson(ob,cls);
                    objectList.add(listob);
                }
            }
            catch (Exception ex){
                System.out.println("Converting String to Object Error:" + ex);
            }

            return objectList;
        }
        else {
            Object object = new Object();

            try {
                if (cls == Farmer.class) {
                    Farmer farmer = new Gson().fromJson(response, cls);
                    object = farmer;
                } else if (cls == Customer.class) {
                    Customer customer = new Gson().fromJson(response, cls);
                    object = customer;
                } else if (cls == Product.class) {
                    Product product = new Gson().fromJson(response, cls);
                    object = product;
                } else if (cls == FarmerProduct.class) {
                    FarmerProduct farmerProduct = new Gson().fromJson(response, cls);
                    object = farmerProduct;
                } else if (cls == Address.class) {
                    Address address = new Gson().fromJson(response, cls);
                    object = address;
                }

                return object;
            } catch (Exception ex) {
                System.out.println("Converting String to Object Error: " + ex);
                return null;
            }
        }
    }
}
