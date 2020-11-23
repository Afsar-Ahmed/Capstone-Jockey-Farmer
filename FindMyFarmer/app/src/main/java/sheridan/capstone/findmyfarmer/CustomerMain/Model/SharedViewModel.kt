package sheridan.capstone.findmyfarmer.CustomerMain.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
// this SharedViewModel class stores data for the clicked recycleview element. This is passed and shared by the activity.
    private val Image : MutableLiveData<Int> = MutableLiveData()
    private val Farmer_Name:MutableLiveData<String> = MutableLiveData()
    private val Farmer_Desc: MutableLiveData<String> = MutableLiveData()
    private val Farmer_Rat : MutableLiveData<Float> = MutableLiveData()
    private val Farmer_City: MutableLiveData<String> = MutableLiveData()


    fun setImage(input: Int){
        Image.value = input
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

}