package sheridan.capstone.findmyfarmer.Customer.Controller

import sheridan.capstone.findmyfarmer.R
import sheridan.capstone.findmyfarmer.Customer.Model.FarmerData
import sheridan.capstone.findmyfarmer.Customer.Model.ListData

class FarmerGenerateList {

    val FarmerData : FarmerData =
        FarmerData()






    fun GenerateList(size: Int): List<ListData> {
        val list = ArrayList<ListData>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.test1
                1 -> R.drawable.test3
                2 -> R.drawable.test3
                3 -> R.drawable.test3
                4 -> R.drawable.test3

                else -> R.drawable.test1
            }
            if (i == 0) {
                val item =
                    ListData(
                        drawable,
                        FarmerData.getFarmerName()[i],
                        FarmerData.getFarmerDesc()[i],
                        FarmerData.getFarmerRating()[i],
                        FarmerData.getFarmerCity()[i],

                        )
                list += item

            } else if (i == 1) {
                val item =
                    ListData(
                        drawable,
                        FarmerData.getFarmerName()[i],
                        FarmerData.getFarmerDesc()[i],
                        FarmerData.getFarmerRating()[i],
                        FarmerData.getFarmerCity()[i],

                        )
                list += item

            } else if (i == 2) {
                val item =
                    ListData(
                        drawable,
                        FarmerData.getFarmerName()[i],
                        FarmerData.getFarmerDesc()[i],
                        FarmerData.getFarmerRating()[i],
                        FarmerData.getFarmerCity()[i],

                        )
                list += item

            } else if (i == 3) {
                val item =
                    ListData(
                        drawable,
                        FarmerData.getFarmerName()[i],
                        FarmerData.getFarmerDesc()[i],
                        FarmerData.getFarmerRating()[i],
                        FarmerData.getFarmerCity()[i],

                        )
                list += item

            } else if (i == 4) {
                val item =
                    ListData(
                        drawable,
                        FarmerData.getFarmerName()[i],
                        FarmerData.getFarmerDesc()[i],
                        FarmerData.getFarmerRating()[i],
                        FarmerData.getFarmerCity()[i],


                        )
                list += item

            }
        }
        return list
    }
}