package sheridan.capstone.findmyfarmer.ImageHandler;

import android.os.Environment;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseImagehandler {

    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseImagehandler(){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    private String GetDirectory(int id,directoryName directoryName,String FileName){
        String FolderName = directoryName.name() + id;
        String localDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FindMyFarmer/" + FolderName + "/" + FileName;
        return localDirectory;
    }
}

enum directoryName{
    Farm,
    Customer
}
