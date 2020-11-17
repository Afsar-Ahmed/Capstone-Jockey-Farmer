package sheridan.capstone.findmyfarmer.Entities;

public class Address {

    private int AddressID;
    private int unit;
    private String Street;
    private String City;
    private String Country;
    private String PostalCode;

    public Address(){}

    public Address(int AddressID,int unit,String Street,String city,String Country,String PostalCode){
        this.AddressID = AddressID;
        this.unit = unit;
        this.Street = Street;
        this.City = city;
        this.Country = Country;
        this.PostalCode = PostalCode;
    }
    //Setters
    public void setCity(String city) { this.City = city; }
    public void setCountry(String country) { Country = country; }
    public void setPostalCode(String postalCode) { PostalCode = postalCode; }
    public void setStreet(String street) { Street = street; }
    public void setUnit(int unit) { this.unit = unit; }

    //Getters
    public int getAddressID() { return AddressID; }
    public int getUnit() { return unit; }
    public String getCity() { return City; }
    public String getCountry() { return Country; }
    public String getPostalCode() { return PostalCode; }
    public String getStreet() { return Street; }
}

