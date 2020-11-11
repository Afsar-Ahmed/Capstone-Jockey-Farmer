package sheridan.capstone.findmyfarmer.Entities;

public class Farmer extends Customer {
    private int FarmerID;
    private String BusinessName;
    private String BusinessDescription;
    private int BusinessRating;

    public Farmer(){ }

//    public Farmer(Customer customer,int FarmerID,String BusinessName,String BusinessDescription,int BusinessRating,int ProductID){
//        super(customer.getCustomerID(),customer.getCustomerName(),customer.getCustomerEmail(),customer.getCustomerPassword());
//        this.FarmerID = FarmerID;
//        this.BusinessName =  BusinessName;
//        this.BusinessDescription = BusinessDescription;
//        this.BusinessRating = BusinessRating;
//        this.ProductID = ProductID;
//    }

    public Farmer(int FarmerID,String BusinessName,String BusinessDescription,int BusinessRating){
        super();
        this.FarmerID = FarmerID;
        this.BusinessName =  BusinessName;
        this.BusinessDescription = BusinessDescription;
        this.BusinessRating = BusinessRating;
    }

    //Setters
    public void setBusinessDescription(String businessDescription) { BusinessDescription = businessDescription; }
    public void setBusinessName(String businessName) { BusinessName = businessName; }
    public void setBusinessRating(int businessRating) { BusinessRating = businessRating; }


    //Getters
    public String getBusinessName() { return BusinessName; }
    public int getFarmerID() { return FarmerID; }
    public String getBusinessDescription() { return BusinessDescription; }
    public int getBusinessRating() { return BusinessRating; }
}
