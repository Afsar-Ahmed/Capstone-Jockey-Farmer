package sheridan.capstone.findmyfarmer.Entities;

public class FarmProduct {
    private int FarmProductID;
    private int FarmID;
    private int ProductID;
    private int Quantity;

    public FarmProduct(){ }

    public FarmProduct(int FarmProductID, int FarmID, int ProductID,int Quantity){
        this.FarmProductID = FarmProductID;
        this.FarmID = FarmID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }

    //Setters
    public void setFarmerID(int farmerID) { FarmID = farmerID; }
    public void setProductID(int productID) { ProductID = productID; }
    public void setQuantity(int quantity) { Quantity = quantity; }

    //Getters
    public int getFarmerID() { return FarmID; }
    public int getFarmProductID() { return FarmProductID; }
    public int getProductID() { return ProductID; }
    public int getQuantity() { return Quantity; }
}


