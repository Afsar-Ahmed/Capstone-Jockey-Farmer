package sheridan.capstone.findmyfarmer.Entities;

public class Farmer extends Customer{
    private int FarmerID;
    private String BusinessName;
    private String BusinessDescription;
    private int BusinessRating;

    public Farmer(){ }

    public Farmer(Customer customer,int FarmerID,String BusinessName,String BusinessDescription,int BusinessRating){
        super(customer.getCustomerID(),customer.getCustomerName(),customer.getCustomerEmail(),customer.getCustomerPassword());
        this.FarmerID = FarmerID;
        this.BusinessName =  BusinessName;
        this.BusinessDescription = BusinessDescription;
        this.BusinessRating = BusinessRating;
    }

    public Farmer(int FarmerID,String BusinessName,String BusinessDescription,int BusinessRating){
        this.FarmerID = FarmerID;
        this.BusinessName =  BusinessName;
        this.BusinessDescription = BusinessDescription;
        this.BusinessRating = BusinessRating;
    }

    //Setters
    public void setBusinessDescription(String businessDescription) { BusinessDescription = businessDescription; }
    public void setBusinessName(String businessName) { BusinessName = businessName; }
    public void setBusinessRating(int businessRating) { BusinessRating = businessRating; }
    public void setCustomer(Customer customer){
        this.setCustomerID(customer.getCustomerID());
        this.setCustomerName(customer.getCustomerName());
        this.setCustomerEmail(customer.getCustomerEmail());
        this.setCustomerPassword(customer.getCustomerPassword());
    }


    //Getters
    public String getBusinessName() { return BusinessName; }
    public int getFarmerID() { return FarmerID; }
    public String getBusinessDescription() { return BusinessDescription; }
    public int getBusinessRating() { return BusinessRating; }
}
