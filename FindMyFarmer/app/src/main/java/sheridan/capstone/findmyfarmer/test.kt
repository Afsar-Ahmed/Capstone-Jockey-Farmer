package sheridan.capstone.findmyfarmer

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_test.*
import org.w3c.dom.Text
import sheridan.capstone.findmyfarmer.ImageHandler.DirectoryName
import sheridan.capstone.findmyfarmer.ImageHandler.FirebaseImagehandler
import sheridan.capstone.findmyfarmer.ImageHandler.StorageResponse
import java.io.InputStream
import java.lang.StringBuilder

class test : AppCompatActivity() {

    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val constlayou = findViewById<ConstraintLayout>(R.id.layoutID);
        val bar  = findViewById<ProgressBar>(R.id.progressBar4)
        val FIH2 = FirebaseImagehandler(DirectoryName.Farm,12,this)

        FIH2.RefreshLocalStorage(bar)

        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    var stream: InputStream? = null
                    try {
                        // recyle unused bitmaps
                        bitmap?.recycle()
                        if (intent != null) {
                            stream = intent.data?.let { contentResolver.openInputStream(it) }
                        }
                        bitmap = BitmapFactory.decodeStream(stream);

                        FIH2.UploadImageToFirebase(bitmap)
                    } catch (ex: Exception) {
                        System.out.println(ex)
                    } finally {
                        try {
                            stream?.close()
                        } catch (ex2: Exception) {
                            System.out.println(ex2)
                        }
                    }
                }
            }

        val startCameraForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    var stream: InputStream? = null
                    try {
                        // recyle unused bitmaps
                        bitmap?.recycle()
                        if (intent != null) {
                            bitmap = intent.extras?.get("data") as Bitmap
                            FIH2.UploadImageToFirebase(bitmap)
                        }
                    } catch (ex: Exception) {
                        System.out.println(ex)
                    } finally {
                        try {
                            stream?.close()
                        } catch (ex2: Exception) {
                            System.out.println(ex2)
                        }
                    }
                }
            }

        button2.setOnClickListener {
            FIH2.RefreshLocalStorage(bar)
            var intt = Intent()
            intt.setType("image/*")
            intt.setAction(Intent.ACTION_GET_CONTENT)
            intt.addCategory(Intent.CATEGORY_OPENABLE)
            startForResult.launch(intt)
        }

        button3.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startCameraForResult.launch(intent)
            } else {
                val requestPermissionLauncher =
                    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                        if (isGranted) {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startCameraForResult.launch(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Camera Permission is needed to use this functionality",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}