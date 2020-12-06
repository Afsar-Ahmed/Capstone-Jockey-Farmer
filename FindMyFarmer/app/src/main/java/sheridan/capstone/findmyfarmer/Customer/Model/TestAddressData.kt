package sheridan.capstone.findmyfarmer.Customer.Model

import com.google.android.gms.maps.GoogleMap

class TestAddressData {

    private val Farm_Address = arrayOf(
        "11742 10 Line, Georgetown, ON L7G 4S7",
        "10100 Heart Lake Rd, Brampton, ON L6Z 0B4",
        "199 Advance Blvd Unit 13, Brampton, ON L6T 4N2",
        "103 Richvale Dr S\n" +
                "Brampton, ON L6Z 4G6",
        "31 David St\n" +
                "Brampton, ON L6X 1J3","Mono Road\n" +
                "Ontario L7C 1E6"

    )

    private val Farm_Etablishment_Name = arrayOf("Add1","Add2","Add3","Add4","Add5","Add6")

    fun getFarmData() : Array<String>{


        return Farm_Address

    }

    fun getFarm_Establishment_Name () : Array<String>{


        return Farm_Etablishment_Name
    }
}
