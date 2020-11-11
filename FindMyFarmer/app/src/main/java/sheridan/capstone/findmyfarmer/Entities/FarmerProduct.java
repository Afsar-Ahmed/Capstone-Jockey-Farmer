package sheridan.capstone.findmyfarmer.Entities;

public class FarmerProduct {
    private int FarmerProductID;
    private int FarmerID;
    private int ProductID;

    public FarmerProduct(){ }

    public FarmerProduct(int FamerProductID,int FarmerID, int ProductID){
        this.FarmerProductID = FamerProductID;
        this.FarmerID = FarmerID;
        this.ProductID = ProductID;
    }

    //Setters
    public void setFarmerID(int farmerID) { FarmerID = farmerID; }
    public void setProductID(int productID) { ProductID = productID; }

    //Getters
    public int getFarmerID() { return FarmerID; }
    public int getFarmerProductID() { return FarmerProductID; }
    public int getProductID() { return ProductID; }
}


