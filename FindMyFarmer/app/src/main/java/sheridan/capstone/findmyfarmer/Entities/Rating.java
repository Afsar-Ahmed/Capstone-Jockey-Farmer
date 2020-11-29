package sheridan.capstone.findmyfarmer.Entities;

public class Rating {
    private int RatingID;
    private int FarmID;
    private int CustomerID;
    private float Rating;
    private String Feedback;

    public Rating(){ }
    public Rating(int RatingID, int FarmID,int CustomerID,String Feedback){
        this.RatingID = RatingID;
        this.FarmID = FarmID;
        this.CustomerID = CustomerID;
        this.Feedback = Feedback;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getFarmID() {
        return FarmID;
    }

    public float getRating() {
        return Rating;
    }

    public int getRatingID() {
        return RatingID;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public void setFarmID(int farmID) {
        FarmID = farmID;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public void setRatingID(int ratingID) {
        RatingID = ratingID;
    }

}
