package sheridan.capstone.findmyfarmer.Users

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.StorageReference
import sheridan.capstone.findmyfarmer.Customer.Controller.ImageListToView
import sheridan.capstone.findmyfarmer.Customer.Model.SharedViewModel
import sheridan.capstone.findmyfarmer.Farmer.View.FarmManager
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import sheridan.capstone.findmyfarmer.R

class PhotoPicker() : Fragment(), ImageListToView.OnItemClickListener  {
    private lateinit var viewModel: SharedViewModel
    private var imagelist = ArrayList<Bitmap>()
    private var references = HashMap<Bitmap,String>()
    private lateinit var FIH2 : FirebaseImagehandler
    private lateinit var progressbar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo_picker,container,false)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        FIH2 = viewModel.getImageHandler().value!!
        progressbar = view.findViewById(R.id.progbar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.imageList)

        val adapter = ImageListToView(imagelist,this)
        recyclerView.layoutManager = GridLayoutManager(context,4)
        recyclerView.adapter = adapter

        refreshList(adapter)

        return view
    }

    override fun onItemClick(position: Int) {
        var f = viewModel.getFarmData().value
        if (f != null) {
            f.primaryImage=imagelist[position]
            viewModel.setFarmData(f)
        }
        progressbar.visibility = View.VISIBLE
        FIH2.MakeImagePrimary(references[imagelist[position]],object : StorageResponse{
            override fun processFinish(response: MutableList<StorageReference>?, bitmap: Bitmap?) {
                progressbar.visibility = View.INVISIBLE
                val FragmentManager : FragmentManager? = activity?.supportFragmentManager
                val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, FarmManager())?.commit()
            }
            override fun OnErrorListener(error: String?) {
                progressbar.visibility = View.INVISIBLE
                val FragmentManager : FragmentManager? = activity?.supportFragmentManager
                val fragmentTransaction : FragmentTransaction? = FragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, FarmManager())?.commit()
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun refreshList(imageListToView: ImageListToView){
        FIH2.RefreshLocalStorage(object : StorageResponse {
            override fun processFinish(response: MutableList<StorageReference>?,bitmap: Bitmap?) {
                var imagelistnames = ArrayList<String>()
                var imagelistBitmap = ArrayList<Bitmap>()
                imagelistnames.addAll(FIH2.GetNamesOfImagesInLocalStorage())
                for(imagename: String in imagelistnames){
                    var bm = FIH2.loadImageFromStorage(imagename)
                    imagelistBitmap.add(bm)
                    references.put(bm,imagename);
                }
                imagelist.clear()
                imagelist.addAll(imagelistBitmap)
                imageListToView.notifyDataSetChanged()
            }
            override fun OnErrorListener(error: String?) { System.out.println(error) }
        })
    }
}