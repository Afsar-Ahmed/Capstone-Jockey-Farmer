package sheridan.capstone.findmyfarmer.Entities;

public class Following {

    private int FollowingID;
    private int CustomerID;
    private int FarmID;

    public Following(){}
    public Following(int FollowingID,int CustomerID,int FarmID){
        this.FarmID = FarmID;
        this.CustomerID = CustomerID;
        this.FollowingID = FollowingID;
    }

    public int getFarmID() {
        return FarmID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getFollowingID() {
        return FollowingID;
    }

    public void setFarmID(int farmID) {
        FarmID = farmID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }
}
