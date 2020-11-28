package sheridan.capstone.findmyfarmer.Farmer.Controller

import sheridan.capstone.findmyfarmer.Farmer.Model.FarmerHubData

import sheridan.capstone.findmyfarmer.Farmer.Model.HubListData
import sheridan.capstone.findmyfarmer.R

class GenerateHubList (){
    val HubData : FarmerHubData = FarmerHubData()



    fun GenerateListHub(size: Int): List<HubListData> {
        val list = ArrayList<HubListData>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.test1
                1 -> R.drawable.test3




                else -> R.drawable.test1
            }
            if (i == 0) {
                val item =
                    HubListData(
                        drawable,
                        HubData.getFarmerName()[i],
                        HubData.getFarmerDesc()[i],
                        HubData.getFarmerRating()[i],
                        HubData.getFarmerCity()[i],
                        HubData.getFarmerFollowers()[i]

                    )
                list += item

            } else if (i == 1) {
                val item =
                    HubListData(
                        drawable,
                        HubData.getFarmerName()[i],
                        HubData.getFarmerDesc()[i],
                        HubData.getFarmerRating()[i],
                        HubData.getFarmerCity()[i],
                        HubData.getFarmerFollowers()[i]

                    )
                list += item

            } else if (i == 2) {
                val item =
                    HubListData(
                        drawable,
                        HubData.getFarmerName()[i],
                        HubData.getFarmerDesc()[i],
                        HubData.getFarmerRating()[i],
                        HubData.getFarmerCity()[i],
                        HubData.getFarmerFollowers()[i]

                    )
                list += item

            } else if (i == 3) {
                val item =
                    HubListData(
                        drawable,
                        HubData.getFarmerName()[i],
                        HubData.getFarmerDesc()[i],
                        HubData.getFarmerRating()[i],
                        HubData.getFarmerCity()[i],
                        HubData.getFarmerFollowers()[i]

                    )
                list += item

            }
        }
        return list
    }

}