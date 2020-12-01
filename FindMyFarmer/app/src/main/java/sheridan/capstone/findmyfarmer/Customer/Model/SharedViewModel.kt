package sheridan.capstone.findmyfarmer.Customer.Model

<<<<<<< HEAD
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
=======
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sheridan.capstone.findmyfarmer.Entities.Farm
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
>>>>>>> Sohaib


class SharedViewModel : ViewModel() {
// this SharedViewModel class stores data for the clicked recycleview element. This is passed and shared by the activity.

<<<<<<< HEAD
    private val Image : MutableLiveData<Int> = MutableLiveData()
    private val Farmer_Name:MutableLiveData<String> = MutableLiveData()
    private val Farmer_Desc: MutableLiveData<String> = MutableLiveData()
    private val Farmer_Rat : MutableLiveData<Float> = MutableLiveData()
    private val Farmer_City: MutableLiveData<String> = MutableLiveData()
    private val SavedData : MutableLiveData<ListData> = MutableLiveData()
    private val Farmers_Followers : MutableLiveData<Int> = MutableLiveData()



    fun setImage(input: Int){
        Image.value = input
    }


    fun setFarmers_Followers(input:Int){
        Farmers_Followers.value = input
    }


    fun setFarmer_Name(input: String){
        Farmer_Name.value = input
    }
    fun setFarmer_Desc(input: String){
        Farmer_Desc.value = input
    }

    fun setFarmer_Rat(input: Float){
        Farmer_Rat.value = input
    }

    fun setFarmer_City(input: String){
        Farmer_City.value = input
    }

    fun setSavedData(List : ListData){
        SavedData.value = List
    }

    fun getSavedData() : LiveData<ListData> {
        return SavedData
    }


    fun getFarmers_Followers() : LiveData<Int>{
        return Farmers_Followers
    }

    fun getImage() : LiveData<Int>{
        return Image
    }
    fun getFarmer_Name(): LiveData<String>{
        return Farmer_Name
    }
    fun getFarmer_Desc() : LiveData<String>{
        return Farmer_Desc
    }
    fun getFarmer_Rating() : LiveData<Float>{
        return Farmer_Rat
    }
    fun getFarmer_City() : LiveData<String> {
        return Farmer_City
    }

=======
    private val FarmData : MutableLiveData<Farm> = MutableLiveData()
    private val Farmers_Followers : MutableLiveData<Int> = MutableLiveData()
    private val firebaseImagehandler : MutableLiveData<FirebaseImagehandler> = MutableLiveData()


    //Setters
    fun setFarmers_Followers(input:Int){
        Farmers_Followers.value = input
    }
    fun setFarmData(List : Farm){
        FarmData.value = List
    }
    fun setFirebaseImageHandler(firebaseImagehandler: FirebaseImagehandler){
        this.firebaseImagehandler.value = firebaseImagehandler
    }


    //Getters
    fun getFarmers_Followers() : LiveData<Int>{
        return Farmers_Followers
    }
    fun getFarmData() : LiveData<Farm> {
        return FarmData
    }
    fun getImageHandler(): LiveData<FirebaseImagehandler>{
        return firebaseImagehandler
    }



>>>>>>> Sohaib
}