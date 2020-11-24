package sheridan.capstone.findmyfarmer.ImageHandler;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FirebaseImagehandler {

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private String FolderName;
    private Context context;


    public FirebaseImagehandler(DirectoryName directoryName, int id, Context context){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FolderName = directoryName.name() + id; //defines the folder related to the user
        generateFileName();
        this.context = context;
    }

    //generates a unique filename
    private String generateFileName(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        return FolderName + "_" + date + ".jpg";
    }
    //saves the image to internal storage, using the given filename
    private String saveToInternalStorage(Bitmap bitmapImage,String fileName){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(FolderName, Context.MODE_PRIVATE);
        File mypath = new File(directory,fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();


    }
    //gets the list of images from firebase based on directoryname and id given
    public List<String> GetAllFirebaseImageNames(StorageResponse storageResponse) {
        List<String> files = new  ArrayList();
        StorageReference listRef = storage.getReference().child(FolderName);
        listRef.listAll().addOnSuccessListener(listResult -> {
                storageResponse.processFinish(listResult.getItems());
        }).addOnFailureListener(e ->
                System.out.println(e)
        );
        return files;
    }
    //downloads all images from firebase to internal storage
    private void DownloadImagesFromFirebaseToLocalStorage(List<String> filenames, int index, ProgressBar progressBar){
        final long ONE_MEGABYTE = 1024 * 1024 * 10;

        if(index < filenames.size()){
            String endpoint = FolderName +"/"+filenames.get(index);
            StorageReference downloadRef = storageReference.child(endpoint);

            downloadRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                saveToInternalStorage(bitmap,filenames.get(index));
                //Toast.makeText(context,"Downloaded " + filenames.get(index) ,Toast.LENGTH_SHORT).show();
                DownloadImagesFromFirebaseToLocalStorage(filenames,index+1,progressBar);

            }).addOnFailureListener(e ->
                    System.out.println(e)
            );
        }
        else{
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
    }
    //Gets the Names of Images in Local Storage
    public List<String> GetNamesOfImagesInLocalStorage(){

        List<String> files = new ArrayList();

        try{
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir(FolderName, Context.MODE_PRIVATE);

            if(directory.exists()){
                files.addAll(Arrays.asList(directory.list()));
                return files;
            }
            else{
                System.out.println("Directory " + directory.getName() + " Doesnt exist");
                return null;
            }
        }catch (Exception ex){
            System.out.println(ex);
        }

        return files;
    }
    //Deletes the File from local Storage
    private void DeleteImageFromlocalStorage(String fileName){
        try{
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir(FolderName, Context.MODE_PRIVATE);
            File f = new File(directory,fileName);
            System.out.println(f.exists());
            System.out.println(f.delete());
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    //Checks if the images in cloud have been downloaded, if there are any new files from cloud
    //then they are downloaded into local storage
    private void InitializeImages(ProgressBar progressBar){
        GetAllFirebaseImageNames(response -> {
            List<String> localStorageImages = GetNamesOfImagesInLocalStorage();
            List<String> ImagesToDownloadFromCloud = new ArrayList();

            for(StorageReference ref: response){
                if(!(localStorageImages.contains(ref.getName()))){
                    ImagesToDownloadFromCloud.add(ref.getName());
                }
            }

            DownloadImagesFromFirebaseToLocalStorage(ImagesToDownloadFromCloud,0,progressBar);

        });
    }

    //loads the image from local storage based on filename
    public Bitmap loadImageFromStorage(String fileName) {
        try {
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir(FolderName, Context.MODE_PRIVATE);
            File f = new File(directory,fileName);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

    }
    //uploads the image to Firebase
    public void UploadImageToFirebase(Bitmap bitmap){
        try {
            StorageReference ref = storageReference.child(FolderName + "/" + generateFileName());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = ref.putBytes(data);

            uploadTask.addOnFailureListener(e -> {
                System.out.println("Failed upload image");
            }).addOnSuccessListener(taskSnapshot -> {
                System.out.println("Successfully upload image");
            });
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void DeleteImageFromFirebase(String fileName){
        StorageReference ref = storageReference.child(FolderName + "/" + fileName);
        ref.delete().addOnSuccessListener(aVoid ->
                System.out.println("Deleted " + fileName + "Successfully!")
        ).addOnFailureListener(e ->
                System.out.println(e)
        );
    }
    //checks if there are any extra images that have been deleted from the cloud,
    // and are then removed from the local storage as well
    public void RefreshLocalStorage(ProgressBar progressBar){
        List<String> localStorageImages = GetNamesOfImagesInLocalStorage();
        if(!(localStorageImages.isEmpty())){
            GetAllFirebaseImageNames(response -> {
                List<String> cloudImages = new ArrayList();
                for(StorageReference ref: response){
                    cloudImages.add(ref.getName());
                }

                for (String localStorageImage: localStorageImages){
                    if(!(cloudImages.contains(localStorageImage))){
                        DeleteImageFromlocalStorage(localStorageImage);
                    }
                }
            });
            InitializeImages(progressBar);
        }
        else{
            InitializeImages(progressBar);
        }
    }
}

