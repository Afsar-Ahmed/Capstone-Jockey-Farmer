package sheridan.capstone.findmyfarmer.Entities;

<<<<<<< HEAD
public class Farmer extends Customer {
    private int FarmerID;
    private String BusinessName;
    private String BusinessDescription;
    private int BusinessRating;
    private int ProductID;

    public Farmer(){ }

//    public Farmer(Customer customer,int FarmerID,String BusinessName,String BusinessDescription,int BusinessRating,int ProductID){
//        super(customer.getCustomerID(),customer.getCustomerName(),customer.getCustomerEmail(),customer.getCustomerPassword());
//        this.FarmerID = FarmerID;
//        this.BusinessName =  BusinessName;
//        this.BusinessDescription = BusinessDescription;
//        this.BusinessRating = BusinessRating;
//        this.ProductID = ProductID;
//    }

    public Farmer(int FarmerID,String BusinessName,String BusinessDescription,int BusinessRating,int ProductID){
        super();
        this.FarmerID = FarmerID;
        this.BusinessName =  BusinessName;
        this.BusinessDescription = BusinessDescription;
        this.BusinessRating = BusinessRating;
        this.ProductID = ProductID;
    }

    //Setters
    public void setBusinessDescription(String businessDescription) { BusinessDescription = businessDescription; }
    public void setBusinessName(String businessName) { BusinessName = businessName; }
    public void setBusinessRating(int businessRating) { BusinessRating = businessRating; }
    public void setProductID(int productID) { ProductID = productID; }


    //Getters
    public String getBusinessName() { return BusinessName; }
    public int getFarmerID() { return FarmerID; }
    public int getProductID() { return ProductID; }
    public String getBusinessDescription() { return BusinessDescription; }
    public int getBusinessRating() { return BusinessRating; }
=======
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
>>>>>>> Sohaib
}
