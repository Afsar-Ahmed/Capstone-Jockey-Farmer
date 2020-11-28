
package sheridan.capstone.findmyfarmer.Farmer.Controller

import sheridan.capstone.findmyfarmer.Farmer.Model.FarmerHubData
import sheridan.capstone.findmyfarmer.Farmer.Model.FarmersFruitListData
import sheridan.capstone.findmyfarmer.R

class GenerateFruitList {

    var Product : FarmerHubData = FarmerHubData()

    fun GenerateFruit(size:Int) : List<FarmersFruitListData> {

        val list = ArrayList<FarmersFruitListData>()
        for (i in 0 until size) {
            val drawable = when (i) {
                0 -> R.drawable.apples
                1 -> R.drawable.straw
                2 -> R.drawable.banana
                3 -> R.drawable.water_melon

                else -> R.drawable.apples
            }
            if (i == 0) {
                val item =
                    FarmersFruitListData(
                        drawable,
                        Product.getFruitName()[i],
                        Product.getFruitCategory()[i],
                        Product.getFruitInfo()[i]




                    )
                list += item

            } else if (i == 1) {
                val item =
                    FarmersFruitListData(
                        drawable,
                        Product.getFruitName()[i],
                        Product.getFruitCategory()[i],
                        Product.getFruitInfo()[i]




                    )
                list += item
            }
            else if (i == 2) {
                val item =
                    FarmersFruitListData(
                        drawable,
                        Product.getFruitName()[i],
                        Product.getFruitCategory()[i],
                        Product.getFruitInfo()[i]




                    )
                list += item
            }
            else if (i == 3) {
                val item =
                    FarmersFruitListData(
                        drawable,
                        Product.getFruitName()[i],
                        Product.getFruitCategory()[i],
                        Product.getFruitInfo()[i]




                    )
                list += item
            }
        }
        return list
    }
}