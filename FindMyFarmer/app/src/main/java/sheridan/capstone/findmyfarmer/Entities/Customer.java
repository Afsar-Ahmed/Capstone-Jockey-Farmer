package sheridan.capstone.findmyfarmer.Entities;

public class Customer {

    private int CustomerID;
    private String CustomerName;
    private String CustomerEmail;
    private String CustomerPassword;
<<<<<<< HEAD

    public Customer() { }

    public Customer(int CustomerID,String CustomerName,String CustomerEmail, String CustomerPassword){
=======
    private boolean IsFarmer;

    public Customer() { }

    public Customer(int CustomerID,String CustomerName,String CustomerEmail, String CustomerPassword,Boolean IsFarmer){
>>>>>>> Sohaib
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.CustomerEmail = CustomerEmail;
        this.CustomerPassword = CustomerPassword;
<<<<<<< HEAD
    }

    //Setters
=======
        this.IsFarmer = IsFarmer;
    }

    //Setters
    public void setCustomerID(int customerID) { CustomerID = customerID; }
>>>>>>> Sohaib
    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }
    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
<<<<<<< HEAD
    public void setCustomerPassword(String customerPassword) {
        CustomerPassword = customerPassword;
    }
=======
    public void setCustomerPassword(String customerPassword) { CustomerPassword = customerPassword; }
    public void setIsFarmer(Boolean IsFarmer){this.IsFarmer = IsFarmer;}
>>>>>>> Sohaib

    //Getters
    public int getCustomerID() {
        return CustomerID;
    }
    public String getCustomerEmail() {
        return CustomerEmail;
    }
    public String getCustomerName() {
        return CustomerName;
    }
    public String getCustomerPassword() {
        return CustomerPassword;
    }
<<<<<<< HEAD
=======
    public boolean getIsFarmer() { return IsFarmer; }
>>>>>>> Sohaib
}
