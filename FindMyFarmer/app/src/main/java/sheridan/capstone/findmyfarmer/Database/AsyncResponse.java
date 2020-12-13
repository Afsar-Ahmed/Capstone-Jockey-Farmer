package sheridan.capstone.findmyfarmer.Database;

/**
 * Author:  Sohaib Hussain
 **/

public interface AsyncResponse {
    //Responds when there is a call back from the API Request
    void processFinish(String response);
}
