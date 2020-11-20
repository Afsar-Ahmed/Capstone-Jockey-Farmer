package sheridan.capstone.findmyfarmer.Entities;

public class Product {

    private int ProductID;
    private String ProductName;
    private String ProductCategory;

    public Product(int ProductID, String ProductName,String ProductCategory){
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductCategory = ProductCategory;
    }

    //getters
    public int getProductID() { return ProductID; }
    public String getProductCategory() { return ProductCategory; }
    public String getProductName() { return ProductName; }

    //setters
    public void setProductCategory(String productCategory) { ProductCategory = productCategory; }
    public void setProductName(String productName) { ProductName = productName; }
}
