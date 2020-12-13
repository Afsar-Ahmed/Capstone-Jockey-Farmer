package sheridan.capstone.findmyfarmer.Entities;
/**
 * Author:  Sohaib Hussain
 **/
public class Product {

    private int ProductID;
    private String ProductName;
    private String ProductCategory;
    private int Quantity;
    private String image;
    private String Unit;

    public Product(int ProductID, String ProductName,String ProductCategory,int Quantity){
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductCategory = ProductCategory;
        this.Quantity = Quantity;
    }

    //getters
    public int getProductID() { return ProductID; }
    public String getProductCategory() { return ProductCategory; }
    public String getProductName() { return ProductName; }
    public int getQuantity() { return Quantity; }
    public String getImage() { return image; }
    public String getUnit() { return Unit; }

    //setters
    public void setProductCategory(String productCategory) { ProductCategory = productCategory; }
    public void setProductName(String productName) { ProductName = productName; }
    public void setQuantity(int quantity) { Quantity = quantity; }
    public void setImage(String image) { this.image = image; }
    public void setUnit(String unit) { Unit = unit; }
}
