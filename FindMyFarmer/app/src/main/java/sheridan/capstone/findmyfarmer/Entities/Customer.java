package sheridan.capstone.findmyfarmer.Entities;

public class Customer {

    private int CustomerID;
    private String CustomerName;
    private String CustomerEmail;
    private String CustomerPassword;

    public Customer() { }

    public Customer(int CustomerID,String CustomerName,String CustomerEmail, String CustomerPassword){
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.CustomerEmail = CustomerEmail;
        this.CustomerPassword = CustomerPassword;
    }

    //Setters
    public void setCustomerID(int customerID) { CustomerID = customerID; }
    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }
    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
    public void setCustomerPassword(String customerPassword) {
        CustomerPassword = customerPassword;
    }

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
}
