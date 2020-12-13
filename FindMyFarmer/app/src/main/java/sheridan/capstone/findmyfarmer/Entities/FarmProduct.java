package sheridan.capstone.findmyfarmer.Entities;
/**
 * Author:  Sohaib Hussain
 **/
public class FarmProduct {
    private int FarmProductID;
    private int FarmID;
    private int ProductID;
    private int Quantity;
    private String Unit;

    public FarmProduct(){ }

    public FarmProduct(int FarmProductID, int FarmID, int ProductID,int Quantity,String Unit){
        this.FarmProductID = FarmProductID;
        this.FarmID = FarmID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.Unit = Unit;
    }

    //Setters
    public void setFarmerID(int farmerID) { FarmID = farmerID; }
    public void setProductID(int productID) { ProductID = productID; }
    public void setQuantity(int quantity) { Quantity = quantity; }
    public void setUnit(String unit) { Unit = unit; }

    //Getters
    public int getFarmerID() { return FarmID; }
    public int getFarmProductID() { return FarmProductID; }
    public int getProductID() { return ProductID; }
    public int getQuantity() { return Quantity; }
    public String getUnit() { return Unit; }
}


