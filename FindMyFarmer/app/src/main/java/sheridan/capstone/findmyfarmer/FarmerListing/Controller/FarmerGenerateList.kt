package sheridan.capstone.findmyfarmer.FarmerListing.Controller

import sheridan.capstone.findmyfarmer.FarmerListing.Model.FarmerData
import sheridan.capstone.findmyfarmer.FarmerListing.Model.FruitData
import sheridan.capstone.findmyfarmer.FarmerListing.Model.ListData
import sheridan.capstone.findmyfarmer.R

class FarmerGenerateList {

    val FarmerData : FarmerData = FarmerData()

    fun GenerateFruit(size:Int) : List<FruitData> {

        val list = ArrayList<FruitData>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.apples
                1 -> R.drawable.straw


                else -> R.drawable.apples
            }
            if (i == 0) {
                val item =
                    FruitData(
                        drawable,
                        FarmerData.getFruitName()[i],
                        FarmerData.getFruitCat()[i],

                        )
                list += item

            } else if (i == 1) {
                val item =
                    FruitData(
                        drawable,
                        FarmerData.getFruitName()[i],
                        FarmerData.getFruitCat()[i]

                    )
                list += item
            }
        }
        return list
    }

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
                        FarmerData.getisSelected()[i]
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
                        FarmerData.getisSelected()[i]
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
                        FarmerData.getisSelected()[i]
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
                        FarmerData.getisSelected()[i]
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
                        FarmerData.getisSelected()[i]

                    )
                list += item

            }
        }
        return list
    }
}