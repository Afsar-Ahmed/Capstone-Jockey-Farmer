package sheridan.capstone.findmyfarmer.Farmer.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Entities.Product
import sheridan.capstone.findmyfarmer.Farmer.Controller.FarmerFruitListToView
import sheridan.capstone.findmyfarmer.Farmer.Model.ProductManager
import sheridan.capstone.findmyfarmer.R


class ProductManagement : Fragment(),FarmerFruitListToView.OnItemClickListener{

    private lateinit var adapter: FarmerFruitListToView
    private var productlist = ArrayList<Product>()
    private var CategoryList = ArrayList<String>()
    private var TypeList = ArrayList<String>()
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_management,container,false)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val CategorySpinner = view.findViewById<Spinner>(R.id.ProductCategorySpinner)
        val TypeSpinner = view.findViewById<Spinner>(R.id.productTypeSpinner)
        val addproduct = view.findViewById<Button>(R.id.add_product)
        val productRecyclerView = view.findViewById<RecyclerView>(R.id.productRecyclerView)

        adapter = FarmerFruitListToView(requireActivity(),viewModel.getFarmData().value!!.farmID,productlist, this)
        var categoryAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_item,CategoryList)
        var TypeAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_item,TypeList)

        productRecyclerView.adapter = adapter
        productRecyclerView.layoutManager = LinearLayoutManager(context)
        productRecyclerView.setHasFixedSize(true)

        var productManager = ProductManager(requireActivity())

        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        TypeSpinner.adapter = TypeAdapter

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        CategorySpinner.adapter = categoryAdapter


        if (productManager != null) {
            productManager.GetProductCategoryList(CategoryList,categoryAdapter)
            productManager.GetEditProducts(productlist,viewModel.getFarmData().value!!.farmID,adapter)
        }

        CategorySpinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemselected = CategorySpinner.selectedItem.toString()
                if (productManager != null) {
                    productManager.GetTypeOfCategory(TypeList,itemselected,TypeAdapter)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                System.out.println("")
            }
        }

        addproduct.setOnClickListener {
            if(CategorySpinner.selectedItem != null && TypeSpinner.selectedItem != null){
                var cat = CategorySpinner.selectedItem.toString()
                var type = TypeSpinner.selectedItem.toString()

                productManager.AddFarmProduct(type,cat,viewModel.getFarmData().value!!.farmID,productlist,adapter)
            }
            else{
                Toast.makeText(activity,"Unable to add",Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    override fun onItemClick(position: Int) {

    }

}
