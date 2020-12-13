package sheridan.capstone.findmyfarmer.Entities;
/**
 * Author:  Sohaib Hussain
 **/
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    private int FarmID;
    private String Business_Name;
    private String Business_Description;
    private float Business_Rating;
    private String City;
    private String Street;
    private String Country;
    private String Province;
    private String PostalCode;
    private int Unit = 0;
    private int FarmerID;
    private String PrimaryImage;
    private boolean isFollowed = false;
    private boolean alreadyRated = false;
    private int Followers = 0;
    private List<Product> products = new ArrayList<>();

    public Farm(){ }

    public Farm(int FarmID,String Business_Name,String Business_Description,float Business_Rating,String City,String Street,String Country,String PostalCode,String Province,int Unit,int Farmer_ID){
        this.FarmID = FarmID;
        this.Business_Name = Business_Name;
        this.Business_Description = Business_Description;
        this.Business_Rating = Business_Rating;
        this.City = City;
        this.Street = Street;
        this.Country = Country;
        this.PostalCode = PostalCode;
        this.Unit = Unit;
        this.FarmerID = Farmer_ID;
        this.Province = Province;
    }

    //Setters
    public void setStreet(String street) { Street = street; }
    public void setPostalCode(String postalCode) { PostalCode = postalCode; }
    public void setCountry(String country) { Country = country; }
    public void setCity(String city) { City = city; }
    public void setFarmerID(int farmerID) { FarmerID = farmerID; }
    public void setBusinessDescription(String businessDescription) { Business_Description = businessDescription; }
    public void setBusinessName(String businessName) { Business_Name = businessName; }
    public void setBusinessRating(int businessRating) { Business_Rating = businessRating; }
    public void setUnit(int unit) { Unit = unit; }
    public void setProvince(String province) { Province = province; }
    public void setPrimaryImage(String primaryImage) { PrimaryImage = primaryImage; }
    public void setAlreadyRated(boolean alreadyRated) { this.alreadyRated = alreadyRated; }
    public void setIsFollowed(boolean isFollowed) { this.isFollowed = isFollowed; }
    public void setFollowers(int Followers) { this.Followers = Followers; }
    public void setProducts(List<Product> products) { this.products = products; }

    //Getters
    public String getPostalCode() { return PostalCode; }
    public String getCountry() { return Country; }
    public String getCity() { return City; }
    public int getUnit() { return Unit; }
    public int getFarmerID() { return FarmerID; }
    public float getBusinessRating() { return Business_Rating; }
    public int getFarmID() { return FarmID; }
    public String getBusinessDescription() { return Business_Description; }
    public String getBusinessName() { return Business_Name; }
    public String getStreet() { return Street; }
    public String getProvince() { return Province; }
    public String getPrimaryImage() { return PrimaryImage; }
    public boolean getAlreadyRated(){ return alreadyRated; }
    public boolean getIsFollowed(){ return isFollowed; }
    public int getFollowers() { return Followers; }
    public List<Product> getProducts() { return products; }
}
