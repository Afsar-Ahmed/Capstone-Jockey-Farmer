package sheridan.capstone.findmyfarmer.Entities;

public class Farm {
    private int Farm_ID;
    private String Businsess_Name;
    private String Business_Description;
    private int Business_Rating;
    private String City;
    private String Street;
    private String Country;
    private String PostalCode;
    private int Unit = 0;
    private int Farmer_ID;

    public Farm(){ }

    public Farm(String Business_Name,String Business_Description,int Business_Rating,String City,String Street,String Country,String PostalCode,int Unit,int Farmer_ID){
        this.Businsess_Name = Business_Name;
        this.Business_Description = Business_Description;
        this.Business_Rating = Business_Rating;
        this.City = City;
        this.Street = Street;
        this.Country = Country;
        this.PostalCode = PostalCode;
        this.Unit = Unit;
        this.Farmer_ID = Farmer_ID;
    }

    //Setters
    public void setStreet(String street) { Street = street; }
    public void setPostalCode(String postalCode) { PostalCode = postalCode; }
    public void setCountry(String country) { Country = country; }
    public void setCity(String city) { City = city; }
    public void setFarmerID(int farmerID) { Farmer_ID = farmerID; }
    public void setBusinessDescription(String businessDescription) { Business_Description = businessDescription; }
    public void setBusinessName(String businessName) { Businsess_Name = businessName; }
    public void setBusinessRating(int businessRating) { Business_Rating = businessRating; }
    public void setUnit(int unit) { Unit = unit; }

    //Getters
    public String getPostalCode() { return PostalCode; }
    public String getCountry() { return Country; }
    public String getCity() { return City; }
    public int getUnit() { return Unit; }
    public int getFarmerID() { return Farmer_ID; }
    public int getBusinessRating() { return Business_Rating; }
    public int getFarmID() { return Farm_ID; }
    public String getBusinessDescription() { return Business_Description; }
    public String getBusinessName() { return Businsess_Name; }
    public String getStreet() { return Street; }
}
