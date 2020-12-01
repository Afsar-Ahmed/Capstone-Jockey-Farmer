package sheridan.capstone.findmyfarmer.SessionDataHandler;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import sheridan.capstone.findmyfarmer.Entities.Customer;
import sheridan.capstone.findmyfarmer.Entities.Farmer;

public class SessionData {

    private final String FARMER_REFERENCE = "Farmer";
    private final String CUSTOMER_REFERENCE = "Customer";
    private final String PREFERENCES_REFRENCES = "UserInfo";
    private SharedPreferences sharedPreferences;
    private Activity activity;
    private Gson gson;

    public SessionData(Activity activity){
        sharedPreferences = activity.getSharedPreferences(PREFERENCES_REFRENCES,Context.MODE_PRIVATE);
        this.activity = activity;
        gson = new Gson();
    }

    public void setUserDataForSession(Farmer farmer, Customer customer){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(customer != null){
            String jsonCustomer = gson.toJson(customer);
            editor.putString(CUSTOMER_REFERENCE,jsonCustomer);
        }
        if(farmer != null){
            String jsonFarmer  = gson.toJson(farmer);
            editor.putString(FARMER_REFERENCE,jsonFarmer);
        }

        editor.commit();
    }
    public Farmer getFarmerData(){
        String json = sharedPreferences.getString(FARMER_REFERENCE, null);
        if(json != null){
            return gson.fromJson(json,Farmer.class);
        }
        return null;
    }
    public Customer getCustomerData(){
        String json = sharedPreferences.getString(CUSTOMER_REFERENCE, null);
        if(json != null){
            return gson.fromJson(json,Customer.class);
        }
        return null;
    }

    public void ClearAllData(){
        sharedPreferences.edit().clear().commit();
    }
}
