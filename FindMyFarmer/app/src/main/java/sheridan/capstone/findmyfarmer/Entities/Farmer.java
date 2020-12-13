package sheridan.capstone.findmyfarmer.Entities;
/**
 * Author:  Sohaib Hussain
 **/
public class Farmer{
    private int FarmerID;
    private int CustomerID;

    public Farmer(){ }

    public Farmer(int FarmerID,int CustomerID){
        this.FarmerID = FarmerID;
        this.CustomerID = CustomerID;
    }

    //Getters
    public int getFarmerID() { return FarmerID; }
    public int getCustomerID() { return CustomerID; }
}
