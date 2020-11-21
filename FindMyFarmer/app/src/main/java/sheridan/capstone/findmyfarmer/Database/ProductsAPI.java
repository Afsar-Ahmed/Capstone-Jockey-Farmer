package sheridan.capstone.findmyfarmer.Database;

import org.json.JSONObject;

import java.util.ArrayList;

import sheridan.capstone.findmyfarmer.Entities.Product;

public interface ProductsAPI {
//spoonacular
    ArrayList<Product> productList = new ArrayList<Product>();

    final String APIKEY= "87cbc6eb7d3548bd9b95d1f715621c20";
    String API_BASE_URL = "https://api.spoonacular.com/food/ingredients/search?apiKey="+ APIKEY;




}
